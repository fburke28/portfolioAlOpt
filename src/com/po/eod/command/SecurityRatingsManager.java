/* File: RatingsManager.java
* 
* Copyright 2010, Finbarr Burke
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.po.dao.AccountDao;
import com.po.dao.CompanyDao;
import com.po.dao.RatingsDao;
import com.po.domain.Account;
import com.po.domain.SecurityRating;

/**
 * Command class used to manage system level ratings.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Feb 16, 2010   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
public class SecurityRatingsManager implements Command {

	private static final Log log = LogFactory.getLog(SecurityRatingsManager.class);

	private RatingsDao ratingsDao;
	private AccountDao accountDao;
	private CompanyDao companyDao;

	public void setRatingsDao(RatingsDao ratingsDao) {
		this.ratingsDao = ratingsDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	/**
	 * Method used to manage security ratings after EOD reference data load
	 */
	public void execute() {
		log.info("Executing ratings calculation and update.");

		// Retrieve list of rated symbols
		List<String> ratedSymbols = ratingsDao.retrieveRatedSymbols();
		for(String symbol : ratedSymbols) {
			List<SecurityRating> securityRatings = ratingsDao.retrieveSecurityRatings(symbol);
			// Ascertain number of user ratings for security and weight distribution
			Map<String, SecurityRating> userRatings = new HashMap<String, SecurityRating>();
			BigDecimal totalRatings = BigDecimal.ZERO;
			for(SecurityRating secRating : securityRatings) {
				Account userDetails = accountDao.retrieveAccount(secRating.getUserName());
				//secRating.setUserRating(userDetails.getUserRating());
				userRatings.put(secRating.getUserName(), secRating);
				//totalRatings = totalRatings.add(userDetails.getUserRating());
			}

			// Calculate collaborative security rating
			int ratingsPool = securityRatings.size();
			Map<String, SecurityRating> userTotalRtPercents = calculateUserRatingPercent(userRatings, totalRatings);
			Set<String> userTotalRtPercentsKeys = userTotalRtPercents.keySet();
			BigDecimal collabRatingValue = new BigDecimal(0);
			for(String userName : userTotalRtPercentsKeys) {
				SecurityRating rating = userTotalRtPercents.get(userName);
				BigDecimal finalUserWeightedRt = 
					rating.getOverallRatingPercent().multiply(new BigDecimal(ratingsPool))
					.divide(new BigDecimal(100), 2, BigDecimal.ROUND_UP);
				collabRatingValue = collabRatingValue.add(
					finalUserWeightedRt.multiply(rating.getRating()).setScale(2, BigDecimal.ROUND_UP)
				);
			}
			collabRatingValue = collabRatingValue.divide(new BigDecimal(ratingsPool), 2, BigDecimal.ROUND_UP);
			log.info("Collaborative security rating value for " + symbol + " is " + collabRatingValue);
			// Update company table with collaborative rating
			//poCompanyDao.updateCompanyRating(symbol, collabRatingValue);
		}
	}

	/**
	 * Utility method used to calculate each user's percentage of the overall user rating
	 * for a given security
	 * @param userRatings A map of each user to their user rating value
	 * @param totalRatings The total value of all user ratings
	 * @return map of username to overall percentage of rating
	 */
	private Map<String, SecurityRating> calculateUserRatingPercent(Map<String, SecurityRating> userRatings, BigDecimal totalRatings) {
		Set<String> userRatingsKeys = userRatings.keySet();

		// If only one rating exists assign 100%
		if(userRatingsKeys.size() == 1) {
			for(String userName : userRatingsKeys) {
				SecurityRating rating = userRatings.get(userName);
				rating.setOverallRatingPercent(new BigDecimal(100));
				return userRatings;
			}
		}

		for(String userName : userRatingsKeys) {
			SecurityRating rating = userRatings.get(userName);
			BigDecimal overallRtPercent = rating.getUserRating().multiply(new BigDecimal(100)).setScale(2)
				.divide(totalRatings, BigDecimal.ROUND_UP);
			
			log.info("Overall rating for " + userName + " and " + rating.getSymbol() 
				+ " = " + overallRtPercent);
			rating.setOverallRatingPercent(overallRtPercent);
		}
		return userRatings;
	}

}