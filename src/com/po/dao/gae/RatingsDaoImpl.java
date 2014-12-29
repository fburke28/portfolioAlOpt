/* File: RatingsDaoImpl.java
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

package com.po.dao.gae;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.po.dao.RatingsDao;
import com.po.domain.SecurityRating;
import com.po.domain.Account;
import com.po.domain.HoldingUpdate;
import com.po.domain.UserRating;

/**
 * Data access class used to retrieve ratings data.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Feb 26, 2010   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
public class RatingsDaoImpl implements RatingsDao {

	private static final Log log = LogFactory.getLog(RatingsDaoImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<String> retrieveRatedSymbols() {
		List<String> symbols = null; // (List<String>)getSqlMapClientTemplate().queryForList("Ratings.symbols");
		return symbols;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<SecurityRating> retrieveSecurityRatings(String symbol) {
		log.info("Retrieving security ratings for symbol: " + symbol);

		Map map = new HashMap();
		map.put("symbol", symbol);
		List<SecurityRating> ratedSecurities = null;
		//	(List<SecurityRating>)getSqlMapClientTemplate().queryForList("Ratings.ratedSecurities", map);
		return ratedSecurities;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<SecurityRating> retrieveRatingsForUser(String userName) {
		log.info("Retrieving security ratings for user: " + userName);

		Map map = new HashMap();
		map.put("userName", userName);
		List<SecurityRating> ratedSecurities = null;
		//	(List<SecurityRating>)getSqlMapClientTemplate().queryForList("Ratings.ratingsForUser", map);
		return ratedSecurities;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public UserRating retrieveUserDetails(String userName, String accountNumber) {
		log.info("Retrieving user details for " + userName + ", " + accountNumber);

		/*Map map = new HashMap();
		map.put("userName", userName);
		map.put("accountNumber", accountNumber);

		UserRating userRating = (UserRating)getSqlMapClientTemplate().queryForObject("Ratings.userDetails", map);

		Map maxSymbolRating = new HashMap();
		maxSymbolRating.put("userName", userName);
		SecurityRating maxRating = (SecurityRating)getSqlMapClientTemplate().queryForObject("Ratings.maxRatingForUser", maxSymbolRating);
		if(null != maxRating) {
			log.info("Max rated stock " + maxRating.getSymbol());
			userRating.setHighestRatedStock(maxRating.getSymbol() + ", " + maxRating.getRating());
		}

		return userRating;*/
		
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Account> retrieveTopUsers() {
		List<Account> topUsers = null; // = (List<Account>)getSqlMapClientTemplate().queryForList("Ratings.topUsers");
		log.info("Retrieved list of top users " + topUsers.size());
		return topUsers;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void insertRating(HoldingUpdate holdingUpdate) {
		Map map = new HashMap();
		map.put("userName", holdingUpdate.getUserName());
		map.put("symbol", holdingUpdate.getSymbol());
		map.put("rating", holdingUpdate.getUserRating());
		map.put("description", holdingUpdate.getRatingDesc());
		map.put("update_Tmstp", new Date());

		//getSqlMapClientTemplate().insert("Ratings.insertRating", map);
		log.info("Successfully inserted rating for: " + holdingUpdate.getSymbol());
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void updateRating(HoldingUpdate holdingUpdate) {
		Map map = new HashMap();
		map.put("rating", holdingUpdate.getUserRating());
		map.put("description", holdingUpdate.getRatingDesc());
		map.put("update_Tmstp", new Date());
		map.put("userName", holdingUpdate.getUserName());
		map.put("symbol", holdingUpdate.getSymbol());

		//getSqlMapClientTemplate().update("Ratings.updateRating", map);
		log.info("Successfully updated rating for: " + holdingUpdate.getSymbol());
	}

}