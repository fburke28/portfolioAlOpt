/* File: ConstraintOptimizer.java
* 
* Copyright 2011, Finbarr Burke
* All Rights Reserved
*
* This software and all information contained herein is the property
* of Finbarr Burke.
*
*			  Restricted Rights Legend
*			  ------------------------
* Use, duplication, or disclosure by the Government is subject to
* restrictions as set forth in paragraph (b)(3)(B) of the Rights in
* Technical Data and Computer Software clause in DAR 7-104.9(a).
*/
package com.po.eod.command;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.po.dao.CompanyDao;
import com.po.dao.PortfolioDao;
import com.po.domain.Company;
import com.po.domain.Holding;
import com.po.domain.OptClassifierDetails;
import com.po.domain.Portfolio;
import com.po.domain.PortfolioConstraint;

/**
 * Command class used to run the security classifier.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Mar 1, 2011   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
public class ConstraintOptimizer implements Command {

	private static final Log log = LogFactory.getLog(ConstraintOptimizer.class);

	private static final String BUY_CLASSIFICATION = "BUY";
	private static final String HOLD_CLASSIFICATION = "HOLD";
	private static final String SELL_CLASSIFICATION = "SELL";

	private PortfolioDao portfolioDao;
	private CompanyDao companyDao;

	private Map<String, OptClassifierDetails> symbolToClassification;

	public void setPortfolioDao(PortfolioDao portfolioDao) {
		this.portfolioDao = portfolioDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	/**
	 * Method used to classify securities into BUY/HOLD/SELL types
	 */
	public void execute() {
		// Truncate the optimized_holdings table
		portfolioDao.truncateOptHoldings();

		// Retrieve portfolio constraints
		List<PortfolioConstraint> constraints = portfolioDao.retrievePortfolioConstraints();

		// Retrieve all companies and place in map of symbol to classification details
		List<Company> companies = companyDao.retrieveCompanies();
		symbolToClassification = new HashMap<String, OptClassifierDetails>();
		//for(Company company : companies) {
		//	symbolToClassification.put(company.getSymbol(),
		//		new OptClassifierDetails(company.getClassification(), company.getSector()));
		//}

		log.info("Retrieved constraints " + constraints.size());
		// Apply constraints to each account and compile optimzation holdings
		for(PortfolioConstraint constraint : constraints) {
			List<Holding> optimizedHoldings = new ArrayList<Holding>();
			Portfolio portfolio = portfolioDao.retrievePortfolio(constraint.getAccountNumber());
			log.info("Applying constraints to user's portfolio " + portfolio.getUserName());

			// Keep BUY and HOLD classified holdings that should stay
			List<Holding> currentHoldings = portfolioDao.retrieveHoldings(constraint.getAccountNumber());
			List<Holding> sellHoldings = new ArrayList<Holding>();
			BigDecimal liquidityValue = portfolioDao.retrieveCashHolding(constraint.getAccountNumber());
			BigDecimal retainedValue = new BigDecimal(0);
			BigDecimal sellValue = new BigDecimal(0);
			// Check each current holding - if it is a new account without holdings this will get skipped
			log.info("Holdings size " + currentHoldings.size());
			for(Holding holding : currentHoldings) {
				OptClassifierDetails classification = symbolToClassification.get(holding.getSymbol());
				if(BUY_CLASSIFICATION.equals(classification.getClassification())) {
					optimizedHoldings.add(holding);
					retainedValue = retainedValue.add(holding.getPrice().multiply(new BigDecimal(holding.getQuantity())));
				}
				else if(SELL_CLASSIFICATION.equals(classification.getClassification())) {
					sellHoldings.add(holding);
					sellValue = sellValue.add(holding.getPrice().multiply(new BigDecimal(holding.getQuantity())));
				}
				// Take care of null classification case if it occurs - just retain the original holding
				else {
					optimizedHoldings.add(holding);
					retainedValue = retainedValue.add(holding.getPrice().multiply(new BigDecimal(holding.getQuantity())));
				}
			}

			// Adjust for daily volatility setting - cash holding is part of retained value
			log.info("Cash holding amount " + liquidityValue);
			retainedValue = retainedValue.add(liquidityValue);
			log.info("Retained value from initial portfolio scan " + retainedValue);
			BigDecimal percentageNotRetained = portfolio.getTotalValue().subtract(retainedValue).setScale(2);
			percentageNotRetained = percentageNotRetained.divide(retainedValue, 2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100));
			log.info("Percentage of sells that need to be retained for account " + constraint.getAccountNumber() +
				" is " + percentageNotRetained);

			// order sell holdings by price change desc
			Comparator comp = new BeanComparator("priceChange");
			Collections.sort(sellHoldings, comp);
			Collections.reverse(sellHoldings);
			log.info("Number of intial sell holdings " + sellHoldings.size());

			// Need to add back in some SELL classified stocks - start with least price drop
			if(percentageNotRetained.compareTo(constraint.getDailyVolatility()) > 0) {
				BigDecimal percentToAddBack = percentageNotRetained.subtract(constraint.getDailyVolatility());
				BigDecimal amountToAddBack =
					portfolio.getTotalValue().divide(new BigDecimal(100), 2, BigDecimal.ROUND_UP).multiply(percentToAddBack);
				log.info("Amount to add back " + amountToAddBack);
				for(Holding holding : sellHoldings) {
					if(holding.getPrice().multiply(new BigDecimal(holding.getQuantity())).compareTo(amountToAddBack) <= 0) {
						amountToAddBack = amountToAddBack.subtract(holding.getPrice().multiply(new BigDecimal(holding.getQuantity())));
						sellValue = sellValue.subtract(holding.getPrice().multiply(new BigDecimal(holding.getQuantity())));
						optimizedHoldings.add(holding);
					}
					// Try to sell just a few of the shares
					else {
						for(int i=1; i<=holding.getQuantity(); i++) {
							amountToAddBack = amountToAddBack.subtract(holding.getPrice());
							sellValue = sellValue.subtract(holding.getPrice());
							// Check if we need to sell more individual shares
							if(amountToAddBack.compareTo(new BigDecimal(0)) <= 0) {
								holding.setQuantity(new Long(i));
								optimizedHoldings.add(holding);
								log.info("Reducing final quantity of holding " + holding.getSymbol()  + " to " + i);
								break;
							}
						}
						log.info("Added back sell holdings due to daily volatility constraint.");
						break;
					}
				}
			}
			else {
				log.info("Percentage not retained of " + percentageNotRetained + " is within constraint amount "
					+ constraint.getDailyVolatility());
			}

			// Add in the best new holdings for the remainder of the portfolio
			log.info("Sell value calculated at " + sellValue);
			BigDecimal buyAmount = liquidityValue.add(sellValue);
			log.info("Buy value calculated at " + buyAmount);
			optimizedHoldings = returnOptHoldings(optimizedHoldings, buyAmount, constraint, portfolio.getTotalValue());
			log.info("Final optimized holdings size for " + constraint.getAccountNumber() + " is "
				+ optimizedHoldings.size());

			// Insert optimized holdings
			portfolioDao.insertOptHoldings(optimizedHoldings);
		}
	}

	/**
	 * Helper method to return max percent in a given stock.
	 */
	private Map<String, BigDecimal> returnMaxPercentInStock(List<Holding> optHoldings) {
		Map<String, BigDecimal> maxInStock = new HashMap<String, BigDecimal>();
		for(Holding optHolding : optHoldings) {
			if(maxInStock.containsKey(optHolding.getSymbol())) {
				BigDecimal updatedValue = maxInStock.get(
					optHolding.getSymbol()).add(optHolding.getPrice().multiply(new BigDecimal(optHolding.getQuantity()))
				);
				maxInStock.put(optHolding.getSymbol(), updatedValue);
			}
			else {
				maxInStock.put(optHolding.getSymbol(),
					optHolding.getPrice().multiply(new BigDecimal(optHolding.getQuantity())));
			}
		}
		log.info("Returning max in stock map of size " + maxInStock.size());
		return maxInStock;
	}

	/**
	 * Helper method to return max percent in a given sector.
	 */
	private Map<String, BigDecimal> returnMaxPercentInSector(List<Holding> optHoldings) {
		Map<String, BigDecimal> maxInSector = new HashMap<String, BigDecimal>();
		for(Holding optHolding : optHoldings) {
			String sector = symbolToClassification.get(optHolding.getSymbol()).getSector();
			if(maxInSector.containsKey(sector)) {
				BigDecimal updatedValue = 
					maxInSector.get(sector).add(optHolding.getPrice().multiply(new BigDecimal(optHolding.getQuantity())));
				maxInSector.put(sector, updatedValue);
				log.info(sector + " value " + maxInSector.get(sector));
			}
			else {
				maxInSector.put(sector,
					optHolding.getPrice().multiply(new BigDecimal(optHolding.getQuantity())));
			}
		}
		log.info("Returning max in sector map of size " + maxInSector.size());
		return maxInSector;
	}

	/**
	 * Method used to calculate optimized holdings given allowable buyAmount value.
	 */
	private List<Holding> returnOptHoldings(List<Holding> optHoldings, BigDecimal buyAmount, PortfolioConstraint constraint,
		BigDecimal totalValue) {
		// If you cannot but any new stocks return
		if(buyAmount.compareTo(new BigDecimal(0)) <= 0 ) {
			return optHoldings;
		}

		// Compile maps of symbols and sectors to amount held for existing optimized holdings
		/*Map<String, BigDecimal> maxInStock = returnMaxPercentInStock(optHoldings);
		Map<String, BigDecimal> maxInSector = returnMaxPercentInSector(optHoldings);

		// Amount in value of max stock and sector
		BigDecimal maxStockValue = totalValue.divide(new BigDecimal(100), 2, BigDecimal.ROUND_DOWN)
			.multiply(constraint.getMaxPercentSingleStock()).setScale(2);
		BigDecimal maxSectorValue = totalValue.divide(new BigDecimal(100),  2, BigDecimal.ROUND_DOWN)
			.multiply(constraint.getMaxPercentSector()).setScale(2);
		log.info("Total allowable amount of individual stock " + maxStockValue);
		log.info("Total allowable amount of individual sector " + maxSectorValue);

		// Sort companies by price change as of trading today
		List<Company> companies = companyDao.retrieveCompanies();
		Comparator comp = new BeanComparator("change");
		Collections.sort(companies, comp);
		Collections.reverse(companies);
		log.info("Best performing company as of last trading day " + companies.get(0).getChange());
		for(Company company : companies) {
			// Check if the stock is classified as a buy and we can buy at least one and 
			// a valid price exists for the stock
			if(BUY_CLASSIFICATION.equals(company.getClassification()) && 
				company.getPrice().compareTo(buyAmount) <= 0 &&
				company.getPrice().compareTo(new BigDecimal(0)) > 0) {
				log.info(company.getSymbol() + " selected for " + company.getClassification() +
					" with price change of " + company.getChange());
				// Check the max percent that can be bought
				BigDecimal currentAmount = maxInStock.get(company.getSymbol());
				if(null == currentAmount) {
					currentAmount = new BigDecimal(0);
				}
				BigDecimal currentSectorAmt = maxInSector.get(company.getSector());
				if(null == currentSectorAmt) {
					currentSectorAmt = new BigDecimal(0);
				}

				// Decide on how much of the stock should be bought
				BigDecimal maxByStock = maxStockValue.subtract(currentAmount);
				BigDecimal maxBySector = maxSectorValue.subtract(currentSectorAmt);
				BigDecimal buyTypeAmt = maxByStock.compareTo(maxBySector) <= 0 ? maxByStock : maxBySector;
				BigDecimal buyFinalAmt = buyAmount.compareTo(buyTypeAmt) >= 0 ? buyTypeAmt : buyAmount;
				log.info("Final max selected buy amount is " + buyTypeAmt);
				// Setting the quantity value will dictate the amount to buy
				Long quantity = null;
				if(company.getCollabRating().compareTo(new BigDecimal(0.82)) >= 0 ||
					(company.getSector().equals(constraint.getPreferredSector()) && 
					 company.getChange().compareTo(new BigDecimal(0)) >= 0) ) {
					log.info("Classified as buy up to available limit with collab rating of " + company.getCollabRating());
					// Buy as much as you can -- Calculate the quantity of shares to buy
					quantity = buyFinalAmt.divide(company.getPrice(), 0, BigDecimal.ROUND_DOWN).longValue();
				}
				else if(company.getChange().compareTo(new BigDecimal(1.5)) >= 0) {
					log.info("Classified as half the limit with price change of " + company.getChange());
					if(company.getPrice().multiply(new BigDecimal(2)).compareTo(buyAmount) > 0) {
						// If we can only buy less then 2 set to buy 1
						quantity = new Long(1);
					}
					else {
						quantity = buyFinalAmt.divide(company.getPrice(), 0, BigDecimal.ROUND_DOWN)
							.divide(new BigDecimal(2), 0, BigDecimal.ROUND_DOWN).longValue();
					}
				}
				else {
					if(company.getPrice().multiply(new BigDecimal(4)).compareTo(buyAmount) > 0) {
						// If we can only buy less then 4 set to buy 1
						quantity = new Long(1);
					}
					else {
						quantity = buyFinalAmt.divide(company.getPrice(), 0, BigDecimal.ROUND_DOWN)
							.divide(new BigDecimal(4), 0, BigDecimal.ROUND_DOWN).longValue();
					}
				}
				log.info("Quantity of shares to buy " + quantity);
				if(quantity.compareTo(new Long(0)) > 0) {
					// Create holding and add the quantity to buy
					Holding optHolding = new Holding();
					optHolding.setAccountNumber(constraint.getAccountNumber());
					optHolding.setQuantity(quantity);
					optHolding.setSymbol(company.getSymbol());
					optHolding.setTradePrice(company.getPrice());
					optHolding.setUpdateTmstp(new Date());
					optHoldings.add(optHolding);

					// Update details - current amount in stock, sector
					buyAmount = buyAmount.subtract(company.getPrice().multiply(new BigDecimal(quantity)));
					// Update stock map
					if(currentAmount.compareTo(new BigDecimal(0)) == 0) {
						maxInStock.put(company.getSymbol(), company.getPrice().multiply(new BigDecimal(quantity)));
					}
					else {
						BigDecimal updatedValue = maxInStock.get(company.getSymbol()).add(
							company.getPrice().multiply(new BigDecimal(quantity))
						);
						maxInStock.put(company.getSymbol(), updatedValue);
					}
					// Update sector map
					if(currentSectorAmt.compareTo(new BigDecimal(0)) == 0) {
						maxInSector.put(company.getSector(), company.getPrice().multiply(new BigDecimal(quantity)));
					}
					else {
						BigDecimal updatedValue =
							maxInSector.get(company.getSector()).add(company.getPrice().multiply(new BigDecimal(quantity)));
						maxInSector.put(company.getSector(), updatedValue);
					}
				}
			}
			// Check if the buyAmount is reduced to 5 - if so break out of buy loop
			if(buyAmount.compareTo(new BigDecimal(5)) <= 0) {
				log.info("Buy amount for " + constraint.getAccountNumber() + " is " + buyAmount);
				break;
			}
		}

		// Update liquidity holding
		Holding cashHolding = new Holding();
		cashHolding.setSymbol("LIQUIDITY");
		cashHolding.setAccountNumber(constraint.getAccountNumber());
		cashHolding.setQuantity(buyAmount.longValue());
		cashHolding.setTradePrice(new BigDecimal(1));
		portfolioDao.insertOptCashHolding(cashHolding);
		log.info("Optimized liquidity holding set to " + buyAmount.longValue());*/

		return optHoldings;
	}

}