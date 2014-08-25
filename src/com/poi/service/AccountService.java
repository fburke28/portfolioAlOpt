/* File: AccountService.java
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;

import com.po.dao.AccountDao;
import com.po.domain.Account;

/**
 * Service class used to retrieve user account data.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Jan 23, 2011   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
@RemotingDestination(channels = {"my-amf"})
public class AccountService implements IAccountService {

	private static final Log log = LogFactory.getLog(AccountService.class);

	private AccountDao accountDao;

	/**
	 * @param accountDao
	 */
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@RemotingInclude
	public boolean checkUsernameAvailability(String username) {
		return accountDao.checkUsernameAvailability(username);
	}

	/**
	 * {@inheritDoc}
	 */
	@RemotingInclude
	public boolean registerUserDetails(Account account) {
		boolean retVal;
		try {
			accountDao.insertAccount(account);
			retVal = true;
		}
		catch(Exception e) {
			log.error("Error occurred inserting user account.", e);
			retVal = false;
		}
		return retVal;
	}

	/**
	 * {@inheritDoc}
	 */
	@RemotingInclude
	public Account retrieveAccount(String username) {
		return accountDao.retrieveAccount(username);
	}

	/**
	 * {@inheritDoc}
	 */
	@RemotingInclude
	public void updateUserDetails(Account account) {
		accountDao.updateAccount(account);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean portfolioExists(String username) {
		return accountDao.portfolioExists(username);
	}

	/**
	 * {@inheritDoc}
	 */
	public String retrieveAccountNumber(String username) {
		return accountDao.retrieveAccountNumber(username);
	}

}