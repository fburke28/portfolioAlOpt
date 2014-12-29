/* File: AccountDaoImpl.java
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
package com.po.dao.gae;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.Query;
import com.po.dao.AccountDao;
import com.po.domain.Account;
import com.po.domain.Portfolio;

/**
 * Data access class used to retrieve account data.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Jan 23, 2010   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
public class AccountDaoImpl implements AccountDao {

	private static final Log log = LogFactory.getLog(AccountDaoImpl.class);

	private ObjectifyFactory objectifyFactory;

	/**
	 * @param objectifyFactory
	 */
	public void setObjectifyFactory(ObjectifyFactory objectifyFactory) {
		this.objectifyFactory = objectifyFactory;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean checkUsernameAvailability(String username) {
		log.info("Checking availability of username: " + username);

		Objectify ofy = objectifyFactory.begin();
		try {
			Account account = ofy.get(Account.class, username);
			log.info("User name is not available.");
			return false;
		}
		catch(NotFoundException nfe) {
			return true;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void insertAccount(Account account) {
		Objectify ofy = objectifyFactory.begin();
		ofy.put(account);

		log.info("Successfully inserted account data for: " + account.getUserName());
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateAccount(Account account) {
		Objectify ofy = objectifyFactory.begin();
		ofy.put(account);

		log.info("Successfully updated account data for: " + account.getUserName());
	}

	/**
	 * {@inheritDoc}
	 */
	public Account retrieveAccount(String username) {
		log.info("Retrieving account information for " + username);

		Objectify ofy = objectifyFactory.begin();
		Account account = ofy.get(Account.class, username);
		log.info("User retrieved: " + account.getFirstName() + " " + account.getLastName());
		return account;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean portfolioExists(String username) {
		log.info("Checking if portfolio exists for: " + username);
		Objectify ofy = objectifyFactory.begin();
		Query<Portfolio> querySet = ofy.query(Portfolio.class).filter("userName =", username);
		if(querySet.count() > 0) {
			log.info("Portfolio exists for user " + username);
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public String retrieveAccountNumber(String username) {
		Objectify ofy = objectifyFactory.begin();
		Query<Portfolio> querySet = ofy.query(Portfolio.class).filter("userName =", username);
		if(querySet.count() > 0) {
			log.info("Portfolio exists for user " + username);
			Portfolio portfolio = querySet.get();
			return portfolio.getAccountNumber();
		}
		else {
			return null;
		}
	}

}