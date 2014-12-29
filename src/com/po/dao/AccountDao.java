/* File: AccountDao.java
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
package com.po.dao;

import com.po.domain.Account;

/**
 * Interface used to define methods for Account data access layer.
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
public interface AccountDao {

	/**
	 * Method used to check if the username already exists in the database
	 * @param username The username to check on
	 * @return boolean
	 */
	boolean checkUsernameAvailability(String username);

	/**
	 * Method used to insert an account
	 * @param Company The company to insert
	 */
	void insertAccount(Account account);

	/**
	 * Method used to update an account
	 * @param account The user account details
	 */
	void updateAccount(Account account);

	/**
	 * Method used to retrieve an account by username
	 * @param username The account username
	 * @return account The user account
	 */
	Account retrieveAccount(String username);

	/**
	 * Method used to check if a portfolio exists for the given user
	 * @param username The username to check on
	 * @return boolean 
	 */
	boolean portfolioExists(String username);

	/**
	 * Method used to retrieve account number for a given user
	 * @param username The user name
	 * @return The account number
	 */
	String retrieveAccountNumber(String username);

}