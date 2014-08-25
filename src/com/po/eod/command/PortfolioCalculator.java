/* File: PortfolioCalculator.java
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.po.dao.AccountDao;
import com.po.dao.CompanyDao;
import com.po.dao.PortfolioDao;
import com.po.domain.Account;
import com.po.domain.Company;
import com.po.domain.Holding;
import com.po.domain.Portfolio;
import com.po.domain.UserRatingWeight;

/**
 * Command class used to perform portfolio related calculations.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Feb 16, 2011   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
public class PortfolioCalculator implements Command {

	private static final Log log = LogFactory.getLog(PortfolioCalculator.class);

	private PortfolioDao portfolioDao;
	private AccountDao accountDao;
	private CompanyDao companyDao;

	private List<UserRatingWeight> userRatingWeights;

	public void setPortfolioDao(PortfolioDao portfolioDao) {
		this.portfolioDao = portfolioDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	public void setUserRatingWeights(List<UserRatingWeight> userRatingWeights) {
		this.userRatingWeights = userRatingWeights;
	}

	/**
	 * Method used to calculate each portfolio's gain/loss and update to the database
	 */
	public void execute() {
		log.info("Executing portfolio calculation.");

		// Retrieve companies for latest prices
		List<Company> companies = companyDao.retrieveCompanies();
		Map<String, Double> symbol2Price = new HashMap<String, Double>();
		for(Company company : companies) {
			symbol2Price.put(company.getSymbol(), company.getPrice());
		}

		List<Portfolio> portfolios = portfolioDao.retrievePortfolios();
		/*for(Portfolio portfolio : portfolios) {
			log.info("Portfolio details: " + portfolio);
			
			List<Holding> holdings = portfolioDao.retrieveHoldings(portfolio.getAccountNumber());

			// Calculate trading day portfolio changes
			BigDecimal totalValue = BigDecimal.ZERO;
			for(Holding holding : holdings) {
				Double todaysPrice = symbol2Price.get(holding.getSymbol());
				Double todaysValue = todaysPrice.multiply(BigDecimal.ONE);
				totalValue = totalValue.add(todaysValue);
			}

			if(portfolio.getTotalValue() == null) {
				portfolio.setTotalValue(portfolio.getInvestment());
			}

			BigDecimal dailyChange = totalValue.subtract(portfolio.getTotalValue()).setScale(4);
			dailyChange = dailyChange.divide(portfolio.getTotalValue(), BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).setScale(2);
			log.info("Daily change: " + dailyChange);

			BigDecimal overallChange = totalValue.subtract(portfolio.getInvestment()).setScale(4);
			overallChange = overallChange.divide(portfolio.getInvestment(), BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).setScale(2);
			log.info("Overall change: " + overallChange);

			// Update user rating dependent on daily portfolio performance
			this.updateUserAccount(portfolio, dailyChange);

			// Update portfolio table details
			portfolio.setTotalValue(totalValue);
			portfolio.setDailyPercentChange(dailyChange);
			portfolio.setPercentChanged(overallChange);
			portfolio.setUpdateTmstp(new Date());
			portfolioDao.updatePortfolio(portfolio);
		}*/
	}

	/**
	 * Utility method to update the user's account details.
	 */
	private void updateUserAccount(Portfolio portfolio, BigDecimal dailyChange) {
		Account userAccount = accountDao.retrieveAccount(portfolio.getUserName());
		/*for(UserRatingWeight ratingWeight : userRatingWeights) {
			if(userAccount.getUserRating().compareTo(new BigDecimal(ratingWeight.getLowLimit())) >= 0 &&
				userAccount.getUserRating().compareTo(new BigDecimal(ratingWeight.getHightLimit())) <= 0) {
				// Gain
				if(dailyChange.compareTo(BigDecimal.ZERO) > 0) {
					BigDecimal updateUserRating = dailyChange.multiply(ratingWeight.getGainRatingWeight()).setScale(2, BigDecimal.ROUND_UP);
					log.info("Gain to user rating value: " + updateUserRating);
					if(userAccount.getUserRating().add(updateUserRating).compareTo(new BigDecimal(100)) <= 0) {
						userAccount.setUserRating(userAccount.getUserRating().add(updateUserRating));
						accountDao.updateAccount(userAccount);
					}
					else {
						userAccount.setUserRating(new BigDecimal(100));
						accountDao.updateAccount(userAccount);
					}
				}
				// Loss
				else if(dailyChange.compareTo(BigDecimal.ZERO) < 0) {
					BigDecimal updateUserRating = dailyChange.multiply(ratingWeight.getLossRatingWeight()).setScale(2, BigDecimal.ROUND_UP);
					log.info("Loss to user rating value: " + updateUserRating);
					if(userAccount.getUserRating().add(updateUserRating).compareTo(BigDecimal.ZERO) >= 0) {
						userAccount.setUserRating(userAccount.getUserRating().add(updateUserRating));
						accountDao.updateAccount(userAccount);
					}
					else {
						userAccount.setUserRating(new BigDecimal(0));
						accountDao.updateAccount(userAccount);
					}
				}
				else {
					log.info("Portfolio value did not change so no user rating changes occurred.");
				}
				break;
			}
		}*/
	}

}