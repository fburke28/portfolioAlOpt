/* File: RatingsService.java
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

package com.po.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.flex.remoting.RemotingInclude;

import com.po.dao.AccountDao;
import com.po.dao.RatingsDao;
import com.po.domain.Account;
import com.po.domain.HoldingUpdate;
import com.po.domain.SecurityRating;
import com.po.domain.UserRating;

/**
 * Service class used to retrieve ratings data.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Apr 23, 2011   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
public class RatingsService implements IRatingsService {

	private static final Log log = LogFactory.getLog(RatingsService.class);

	private RatingsDao ratingsDao;
	private AccountDao accountDao;

	public void setRatingsDao(RatingsDao ratingsDao) {
		this.ratingsDao = ratingsDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@RemotingInclude
	public List<SecurityRating> retrieveRatingsForUser(String userName) {
		return ratingsDao.retrieveRatingsForUser(userName);
	}

	/**
	 * {@inheritDoc}
	 */
	@RemotingInclude
	public UserRating retrieveUserDetails(String userName, String accountNumber) {
		return ratingsDao.retrieveUserDetails(userName, accountNumber);
	}

	/**
	 * {@inheritDoc}
	 */
	@RemotingInclude
	public List<UserRating> retrieveTopUsers() {
		// Retrieve list of top userName, accountNumber combinations
		List<Account> topUsers = ratingsDao.retrieveTopUsers();
		List<UserRating> userRatings = new ArrayList<UserRating>();
		// Retrieve each user details
		//int rank = 1;
		for(Account account : topUsers) {
			if(accountDao.portfolioExists(account.getUserName())) {
				String accountNumber = accountDao.retrieveAccountNumber(account.getUserName());
				UserRating userRating = ratingsDao.retrieveUserDetails(account.getUserName(),
					accountNumber);
				//userRating.setRank(new Long(rank));
				userRatings.add(userRating);
				//rank++;
			}
			
		}
		return userRatings;
	}

	/**
	 * {@inheritDoc}
	 */
	@RemotingInclude
	public void insertRating(HoldingUpdate holdingUpdate) {
		ratingsDao.insertRating(holdingUpdate);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@RemotingInclude
	public void updateRating(HoldingUpdate holdingUpdate) {
		ratingsDao.updateRating(holdingUpdate);
	}

}