/* File: IRatingsService.java
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

import java.util.List;

import com.po.domain.HoldingUpdate;
import com.po.domain.SecurityRating;
import com.po.domain.UserRating;

/**
 * Service interface used to retrieve ratings data.
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
public interface IRatingsService {

	/**
	 * Method used to retrieve security ratings for given user
	 * @param userName The user name
	 */
	List<SecurityRating> retrieveRatingsForUser(String userName);

	/**
	 * Method used to retrieve user details
	 * @param userName The user name
	 * @param accountNumber The account number
	 * @return userRating
	 */
	UserRating retrieveUserDetails(String userName, String accountNumber);

	/**
	 * Method used to retrieve top rated users
	 * @return List of top rated users
	 */
	List<UserRating> retrieveTopUsers();

	/**
	 * Method used to insert a user supplied rating
	 * @param holdingUpdate The rating associated to the holding
	 */
	void insertRating(HoldingUpdate holdingUpdate);

	/**
	 * Method used to update a user supplied rating
	 * @param holdingUpdate The rating associated to the holding
	 */
	void updateRating(HoldingUpdate holdingUpdate);

}