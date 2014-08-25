/* File: PortfolioService.java
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

package com.poi.service;

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
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;

import com.po.dao.AccountDao;
import com.po.dao.CompanyDao;
import com.po.dao.PortfolioDao;
import com.po.dao.RatingsDao;
import com.po.dao.MarketDataDao;
import com.po.domain.Account;
import com.po.domain.Company;
import com.poi.domain.CompanyHolding;
import com.po.domain.Holding;
import com.po.domain.HoldingUpdate;
import com.poi.domain.IndividualHolding;
import com.po.domain.Portfolio;
import com.po.domain.SecurityRating;

/**
 * Service class used to portfolio data.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Mar 23, 2011   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
@RemotingDestination(channels = {"my-amf"})
public class PortfolioService implements IPortfolioService {

	private static final Log log = LogFactory.getLog(PortfolioService.class);

	private PortfolioDao portfolioDao;
	private AccountDao accountDao;
	private CompanyDao companyDao;
	private RatingsDao ratingsDao;
	private MarketDataDao marketDataDao;

	/**
	 * @param portfolioDao
	 */
	public void setPortfolioDao(PortfolioDao portfolioDao) {
		this.portfolioDao = portfolioDao;
	}

	/**
	 * @param accountDao
	 */
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	/**
	 * @param companyDao
	 */
	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	/**
	 * @param ratingsDao
	 */
	public void setRatingsDao(RatingsDao ratingsDao) {
		this.ratingsDao = ratingsDao;
	}

	/**
	 * @param marketDataDao
	 */
	public void setMarketDataDao(MarketDataDao marketDataDao) {
		this.marketDataDao = marketDataDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@RemotingInclude
	public List<Portfolio> retrievePortfolios() {
		return portfolioDao.retrievePortfolios();
	}

	/**
	 * {@inheritDoc}
	 */
	@RemotingInclude
	public Portfolio retrievePortfolioConstraints(String accountNumber) {
		return portfolioDao.retrievePortfolioConstraints(accountNumber);
	}

	/**
	 * {@inheritDoc}
	 */
	@RemotingInclude
	public List<Holding> retrieveHoldings(String accountNumber) {
		List<Holding> holdings = portfolioDao.retrieveHoldings(accountNumber);
		for(Holding securityHolding : holdings) {
			BigDecimal latestPrice;
			if(securityHolding.getSymbol().equalsIgnoreCase("LIQUIDITY")) {
				latestPrice = BigDecimal.ONE;
			}
			else {
				latestPrice = marketDataDao.retrieveLatestPriceForSymbol(securityHolding.getSymbol());
			}
			if(null != latestPrice && securityHolding.getTradePrice().compareTo(new BigDecimal(0)) > 0) {
				BigDecimal priceChange = latestPrice.setScale(2, BigDecimal.ROUND_UP).subtract(securityHolding.getTradePrice());
				priceChange = priceChange.divide(securityHolding.getTradePrice(), BigDecimal.ROUND_UP).multiply(new BigDecimal(100));
				log.info("Daily change: " + priceChange);
				securityHolding.setPrice(latestPrice);
				securityHolding.setPriceChange(priceChange);
				log.info("Latest price set as " + latestPrice);
			}
			securityHolding.setShare(
				securityHolding.getPrice().multiply(new BigDecimal(securityHolding.getQuantity()))
			);
		}

		// Add cash holding
		/*BigDecimal cashHolding = portfolioDao.retrieveCashHolding(accountNumber);
		if(cashHolding.compareTo(new BigDecimal(0)) > 0) {
			log.info("Cash holding found for account " + accountNumber);
			Holding cash = new Holding();
			cash.setQuantity(cashHolding.longValue());
			cash.setShare(cashHolding);
			cash.setSymbol("LIQUIDITY");
			cash.setTradePrice(new BigDecimal(1.00));
			cash.setAccountNumber(accountNumber);
			holdings.add(cash);
		}*/
		Comparator comp = new BeanComparator("symbol");
		Collections.sort(holdings, comp);
		return holdings;
	}

	/**
	 * {@inheritDoc}
	 */
	@RemotingInclude
	public BigDecimal retrieveCashHolding(String accountNumber) {
		return portfolioDao.retrieveCashHolding(accountNumber);
	}

	/**
	 * {@inheritDoc}
	 */
	@RemotingInclude
	public List<Holding> retrieveOptHoldings(String accountNumber) {
		return portfolioDao.retrieveOptHoldings(accountNumber);
	}

	/**
	 * {@inheritDoc}
	 */
	@RemotingInclude
	public List<CompanyHolding> retrieveCompanyHoldingData(String accountNumber, String userName) {
		List<Holding> holdings = retrieveHoldings(accountNumber);
		BigDecimal liquidityHolding = retrieveCashHolding(accountNumber);
		log.info(holdings.size() + " holdings retrieved for " + accountNumber);

		// Retrieve user ratings
		List<SecurityRating> ratings = ratingsDao.retrieveRatingsForUser(userName);

		List<Company> companies = companyDao.retrieveCompanies();
		// Construct company holdings data
		List<CompanyHolding> companyHoldingList = new ArrayList<CompanyHolding>();
		/*for(Company company : companies) {
			CompanyHolding companyHolding = new CompanyHolding();
			List<IndividualHolding> individualHoldings = new ArrayList<IndividualHolding>();
			companyHolding.setSymbol(company.getSymbol());
			companyHolding.setCompanyDesc(company.getCompanyDesc());
			companyHolding.setSector(company.getSector());
			companyHolding.setIndustry(company.getIndustry());
			companyHolding.setMarketCap(company.getMarketCap());
			// Need to call yahoo finance for latest price
			companyHolding.setCollabRating(company.getCollabRating());
			companyHolding.setClassification(company.getClassification());
			companyHolding.setLiquidity(liquidityHolding);
			// Add holding data
			boolean containsHolding = false;
			for(Holding holding : holdings) {
				if(holding.getSymbol().equals(company.getSymbol())) {
					log.info("Individual holding exists for " + holding.getSymbol());
					IndividualHolding individualHolding = new IndividualHolding();
					individualHolding.setHoldingKey(holding.getHoldingKey());
					individualHolding.setQuantity(holding.getQuantity());
					individualHolding.setTradePrice(holding.getTradePrice());
					individualHoldings.add(individualHolding);
					containsHolding = true;
				}
			}
			if(!containsHolding) {
				companyHolding.setHeld("No");
				companyHolding.setQuantity(new Long(0));
			}
			else {
				companyHolding.setHeld("Yes");
				companyHolding.setIndividualHoldings(individualHoldings);
			}
			// Add rating data
			boolean containsRating = false;
			for(SecurityRating rating : ratings) {
				if(rating.getSymbol().equals(company.getSymbol())) {
					log.info("Rating " + rating.getRating() + " exists for symbol " + rating.getSymbol());
					companyHolding.setUserRating(rating.getRating());
					companyHolding.setRatingDesc(rating.getDescription());
					containsRating = true;
					break;
				}
			}
			if(!containsRating) {
				companyHolding.setUserRating(new BigDecimal(0));
				companyHolding.setRatingDesc("");
			}
	
			companyHoldingList.add(companyHolding);
		}*/
		return companyHoldingList;
	}

	/**
	 * {@inheritDoc}
	 */
	@RemotingInclude
	public CompanyHolding retrieveCompanyData(String accountNumber, String userName, String symbol) {
		CompanyHolding companyHolding = new CompanyHolding();
		/*Company company = companyDao.retrieveCompanyForSymbol(symbol);
		companyHolding.setSymbol(company.getSymbol());
		companyHolding.setCompanyDesc(company.getCompanyDesc());
		companyHolding.setSector(company.getSector());
		companyHolding.setIndustry(company.getIndustry());
		companyHolding.setMarketCap(company.getMarketCap());
		companyHolding.setPrice(company.getPrice());
		BigDecimal latestPrice = marketDataDao.retrieveLatestPriceForSymbol(company.getSymbol());
		if(null != latestPrice && company.getPrice().compareTo(new BigDecimal(0)) > 0) {
			BigDecimal priceChange = latestPrice.setScale(2, BigDecimal.ROUND_UP).subtract(company.getPrice());
			priceChange = priceChange.divide(company.getPrice(), BigDecimal.ROUND_UP).multiply(new BigDecimal(100));
			log.info("Daily change: " + priceChange);
			companyHolding.setPrice(latestPrice);
			log.info("Latest price set as " + latestPrice);
		}
		companyHolding.setCollabRating(company.getCollabRating());
		companyHolding.setClassification(company.getClassification());

		// Retrieve user ratings
		List<SecurityRating> ratings = ratingsDao.retrieveRatingsForUser(userName);
		boolean containsRating = false;
		for(SecurityRating rating : ratings) {
			if(rating.getSymbol().equals(company.getSymbol())) {
				log.info("Rating " + rating.getRating() + " exists for symbol " + rating.getSymbol());
				companyHolding.setUserRating(rating.getRating());
				companyHolding.setRatingDesc(rating.getDescription());
				containsRating = true;
				break;
			}
		}
		if(!containsRating) {
			companyHolding.setUserRating(new BigDecimal(0));
			companyHolding.setRatingDesc("");
		}

		List<Holding> holdings = retrieveHoldings(accountNumber);
		// Add holding data
		boolean containsHolding = false;
		for(Holding holding : holdings) {
			if(holding.getSymbol().equals(company.getSymbol())) {
				log.info("Individual holding exists for " + holding.getSymbol());
				containsHolding = true;
			}
		}
		if(!containsHolding) {
			companyHolding.setHeld("No");
		}
		else {
			companyHolding.setHeld("Yes");
		}*/

		return companyHolding;
	}

	/**
	 * {@inheritDoc}
	 */
	@RemotingInclude
	public String createPortfolio(Portfolio portfolio) {
		portfolio.setObjectifyValues();
		log.info("Creating portfolio for user " + portfolio.getUserName());

		// Create an account number using the first two letters of the users firstname, lastname
		// and six numbers to differentiate portfolios
		Account account = accountDao.retrieveAccount(portfolio.getUserName());
		StringBuilder builder = new StringBuilder();
		builder.append(account.getFirstName().charAt(0));
		builder.append(account.getLastName().charAt(0));

		int count = 0;
		String numberSet = "000000";
		String accountNumber = null;
		while(true) {
			count++;
			numberSet = numberSet + count;
			numberSet = numberSet.substring(numberSet.length() - 6, numberSet.length());
			accountNumber = builder.append(numberSet).toString();
			log.debug("Checking account number " + accountNumber);
			// Check if accountNumber is not taken
			if(portfolioDao.checkAccountNumberAvailability(accountNumber)) {
				log.info("Available account number found: " + accountNumber);
				break;
			}
			else {
				builder.delete(2, builder.length());
				numberSet = "000000";
			}
		}
		// Insert portfolio details - set accountNumber
		portfolio.setAccountNumber(accountNumber);
		portfolioDao.insertPortfolio(portfolio);
		//portfolioDao.insertPortfolioConstraints(portfolio);
		// Insert the initial holding
		HoldingUpdate initialHolding = new HoldingUpdate();
		initialHolding.setSymbol("LIQUIDITY");
		initialHolding.setAccountNumber(accountNumber);
		initialHolding.setUpdatedQuantity(portfolio.getInvestment().longValue());
		initialHolding.setTradePrice(new BigDecimal(1));
		portfolioDao.insertHolding(initialHolding);
		return accountNumber;
	}

	/**
	 * {@inheritDoc}
	 */
	@RemotingInclude
	public void updatePortfolioDetails(Portfolio portfolio) {
		portfolioDao.updatePortfolioDetails(portfolio);
	}

	/**
	 * {@inheritDoc}
	 */
	@RemotingInclude
	public void updatePortfolioConstraints(Portfolio portfolio) {
		// Update cash investment
		Portfolio portfolioToUpdate = portfolioDao.retrievePortfolio(portfolio.getAccountNumber());
		BigDecimal investment = portfolioToUpdate.getInvestment();
		if(portfolio.getInvestment().compareTo(investment) > 0) {
			BigDecimal investmentIncrease = portfolio.getInvestment().subtract(portfolioToUpdate.getInvestment());
			log.info("Invest amount has changed by " + investmentIncrease);
			portfolioToUpdate.setInvestment(portfolio.getInvestment());
			portfolioToUpdate.setCashTotal(portfolioToUpdate.getCashTotal().add(investmentIncrease));
			portfolioToUpdate.setTotalValue(portfolioToUpdate.getTotalValue().add(investmentIncrease));
			portfolioToUpdate.setUpdateTmstp(new Date());
			portfolioDao.updatePortfolioDetails(portfolioToUpdate);

			// Update cash holding
			HoldingUpdate liquidityUpdate = new HoldingUpdate();
			liquidityUpdate.setAccountNumber(portfolio.getAccountNumber());
			liquidityUpdate.setSymbol("LIQUIDITY");
			liquidityUpdate.setUpdatedQuantity(portfolioToUpdate.getCashTotal().longValue());
			portfolioDao.updateCashHolding(liquidityUpdate);
		}

		portfolioDao.updatePortfolioConstraints(portfolio);
	}

	/**
	 * {@inheritDoc}
	 */
	@RemotingInclude
	public void createHolding(HoldingUpdate holdingUpdate) {
		log.info("Creating holding for " + holdingUpdate.getSymbol());

		// insert holding
		portfolioDao.insertHolding(holdingUpdate);

		// Update security rating
		List<SecurityRating> ratings = ratingsDao.retrieveRatingsForUser(holdingUpdate.getUserName());
		boolean updateRating = false;
		for(SecurityRating rating : ratings) {
			if(rating.getSymbol().equals(holdingUpdate.getSymbol())) {
				updateRating = true;
				break;
			}
		}
		if(updateRating) {
			ratingsDao.updateRating(holdingUpdate);
		}
		else {
			ratingsDao.insertRating(holdingUpdate);
		}

		// Update portfolio details - liquidity amount, totalEquity
		Portfolio portfolio = portfolioDao.retrievePortfolio(holdingUpdate.getAccountNumber());
		portfolio.setCashTotal(portfolio.getCashTotal().subtract(holdingUpdate.getBuyAmount()));
		portfolio.setEquityTotal(portfolio.getEquityTotal().add(holdingUpdate.getBuyAmount()));
		portfolio.setUpdateTmstp(new Date());
		portfolioDao.updatePortfolioDetails(portfolio);

		// Update liquidity holding
		HoldingUpdate liquidityUpdate = new HoldingUpdate();
		liquidityUpdate.setAccountNumber(holdingUpdate.getAccountNumber());
		liquidityUpdate.setSymbol("LIQUIDITY");
		liquidityUpdate.setUpdatedQuantity(portfolio.getCashTotal().longValue());
		portfolioDao.updateCashHolding(liquidityUpdate);
	}

	/**
	 * {@inheritDoc}
	 */
	@RemotingInclude
	public void editHolding(HoldingUpdate holdingUpdate) {
		log.info("Editing holding for " + holdingUpdate.getSymbol() + " with update quantity of "
			+ holdingUpdate.getUpdatedQuantity() + " and a holding key of "
			+ holdingUpdate.getHoldingKey());

		if(holdingUpdate.getUpdatedQuantity().compareTo(new Long(0)) == 0) {
			// delete holding
			portfolioDao.deleteHolding(holdingUpdate);
		}
		else {
			// update holding
			portfolioDao.updateHolding(holdingUpdate);
		}

		// Update portfolio details - liquidity amount, totalEquity
		Portfolio portfolio = portfolioDao.retrievePortfolio(holdingUpdate.getAccountNumber());
		portfolio.setCashTotal(portfolio.getCashTotal().add(holdingUpdate.getBuyAmount()));
		portfolio.setEquityTotal(portfolio.getEquityTotal().subtract(holdingUpdate.getBuyAmount()));
		portfolio.setUpdateTmstp(new Date());
		portfolioDao.updatePortfolioDetails(portfolio);

		// Update liquidity holding
		HoldingUpdate liquidityUpdate = new HoldingUpdate();
		liquidityUpdate.setAccountNumber(holdingUpdate.getAccountNumber());
		liquidityUpdate.setSymbol("LIQUIDITY");
		liquidityUpdate.setUpdatedQuantity(portfolio.getCashTotal().longValue());
		portfolioDao.updateCashHolding(liquidityUpdate);
	}

	/**
	 * {@inheritDoc}
	 */
	@RemotingInclude
	public void acceptOptHoldings(String accountNumber) {
		log.info("Accepting optimized holdings for " + accountNumber);

		// Retrieve optimized holdings results
		List<Holding> optHoldings = portfolioDao.retrieveOptHoldings(accountNumber);

		// Delete current holdings
		portfolioDao.deleteHoldings(accountNumber);

		// Insert optimized holdings
		BigDecimal cashHolding = new BigDecimal("0");
		BigDecimal equityTotal = new BigDecimal("0");
		for(Holding optHolding : optHoldings) {
			HoldingUpdate toUpdate = new HoldingUpdate();
			toUpdate.setAccountNumber(accountNumber);
			toUpdate.setSymbol(optHolding.getSymbol());
			toUpdate.setUpdatedQuantity(optHolding.getQuantity());
			toUpdate.setTradePrice(optHolding.getTradePrice());
			portfolioDao.insertHolding(toUpdate);
			if("LIQUIDITY".equals(optHolding.getSymbol())) {
				cashHolding = new BigDecimal(optHolding.getQuantity());
			}
			else {
				BigDecimal value = optHolding.getTradePrice().multiply(new BigDecimal(optHolding.getQuantity().toString()));
				equityTotal = equityTotal.add(value);
			}
		}
		log.info("Equity total value: " + equityTotal);

		// Delete optimized holdings
		portfolioDao.deleteOptHoldings(accountNumber);
		
		// Update portfolio details - liquidity amount, totalEquity
		Portfolio portfolio = portfolioDao.retrievePortfolio(accountNumber);
		portfolio.setCashTotal(cashHolding);
		portfolio.setEquityTotal(equityTotal);
		portfolio.setTotalValue(equityTotal.add(cashHolding));
		portfolio.setUpdateTmstp(new Date());
		portfolioDao.updatePortfolioDetails(portfolio);
	}

}