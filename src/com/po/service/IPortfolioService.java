/* File: IPortfolioService.java
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

import java.math.BigDecimal;
import java.util.List;

import com.poi.domain.CompanyHolding;
import com.po.domain.Holding;
import com.po.domain.HoldingUpdate;
import com.po.domain.Portfolio;

/**
 * Service interface used to portfolio data.
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
public interface IPortfolioService {

	/**
	 * Method used to retrieve all available portfolios
	 */
	List<Portfolio> retrievePortfolios();

	/**
	 * Method used to retrieve portfolio constraints
	 * @param accountNumber The account number
	 */
	Portfolio retrievePortfolioConstraints(String accountNumber);

	/**
	 * Method used to retrieve all holdings for the specified account
	 * @param accountNumber The specified account
	 */
	List<Holding> retrieveHoldings(String accountNumber);

	/**
	 * Method used to retrieve the cash holdings for an account
	 * @param accountNumber The specified account
	 * @return The cash holding amount
	 */
	BigDecimal retrieveCashHolding(String accountNumber);

	/**
	 * Method used to retrieve all optimized holdings for the specified account
	 * @param accountNumber The specified account
	 */
	List<Holding> retrieveOptHoldings(String accountNumber);

	/**
	 * Method used to retrieve combined company and holding data
	 * @param accountNumber The specified account
	 * @param userName The user name
	 * @return List of company holding data
	 */
	List<CompanyHolding> retrieveCompanyHoldingData(String accountNumber, String userName);

	/**
	 * Method used to retrieve company data for a given symbol and user
	 * @param accountNumber The specified account
	 * @param userName The user name
	 * @return Company data
	 */
	CompanyHolding retrieveCompanyData(String accountNumber, String userName, String symbol);

	/**
	 * Method used to create a portfolio
	 * @param portfolio The portfolio to create
	 * @return The accountNumber associated with the new portfolio
	 */
	String createPortfolio(Portfolio portfolio);

	/**
	 * Method used to update portfolio details
	 * @param portfolio The portfolio to update
	 */
	void updatePortfolioDetails(Portfolio portfolio);

	/**
	 * Method used to update portfolio constraints
	 * @param portfolio The portfolio to update
	 */
	void updatePortfolioConstraints(Portfolio portfolio);

	/**
	 * Method used to create a user supplied holding
	 * @param holdingUpdate The holding information
	 */
	void createHolding(HoldingUpdate holdingUpdate);

	/**
	 * Method used to edit a user supplied holding
	 * @param holdingUpdate The holding information
	 */
	void editHolding(HoldingUpdate holdingUpdate);

	/**
	 * Method used to accept optimized holdings results.
	 * @param accountNumber The user's account number
	 */
	void acceptOptHoldings(String accountNumber);

}