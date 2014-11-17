/* File: IAccountService.java
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

import com.po.domain.Account;

/**
 * Service interface used to retrieve user account data.
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
public interface IAccountService {

	/**
	 * Method used to check if the username already exists in the database
	 * @param username The username to check on
	 * @return boolean
	 */
	boolean checkUsernameAvailability(String username);

	/**
	 * Method used to insert user account registration data.
	 * @param account The user account details
	 * @return boolean
	 */
	boolean registerUserDetails(Account account);

	/**
	 * Method used 
	 * @param account The user account details
	 */
	void updateUserDetails(Account account);

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

	/**
	 * Method used to retrieve an account by username
	 * @param username The account username
	 * @return account The user account
	 */
	Account retrieveAccount(String username);

}