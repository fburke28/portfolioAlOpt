/* File: ICompanyService.java
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

import java.math.BigDecimal;
import java.util.List;

import com.po.domain.Company;
import com.po.domain.CompanyClassification;
import com.po.domain.CompanyIdentifier;
import com.po.domain.StockRating;

/**
 * Service interface used for company data.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Mar 26, 2011   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
public interface ICompanyService {

	/**
	 * Method used to retrieve the number of loaded companies
	 * @return Count of loaded companies
	 */
	int retrieveCompanyCount();

	/**
	 * Method used to retrieve all company sectors
	 * @return List of company sectors
	 */
	List<String> retrieveCompanySectors();

	/**
	 * Method used to retrieve all company symbols
	 * @return List of symbols
	 */
	List<CompanyIdentifier> retrieveCompanySymbols();

	/**
	 * Method used to retrieve all companies
	 * @return List of companies
	 */
	List<Company> retrieveCompanies();

	/**
	 * Method used to retrieve a list of companies for a given sector
	 * @param sector The specified sector
	 * @return List of companies
	 */
	List<Company> retrieveCompaniesForSector(String sector);

	/**
	 * Method used to retrieve a company by its symbol
	 * @param symbol The specified symbol
	 * @return company
	 */
	Company retrieveCompanyForSymbol(String symbol);

	/**
	 * Method used to insert a company
	 * @param Company The company to insert
	 */
	void insertCompany(Company company);

	/**
	 * Method used to update a company
	 * @param Company The company to update
	 */
	void updateCompany(Company company);

	/**
	 * Method to return StockRating for a symbol.
	 * @return StockRating
	 */
	StockRating retrieveStockRating(String userName, String symbol);

	/**
	 * Upsert of stock rating.
	 * @param stockRating
	 * @return
	 */
	void persistStockRating(StockRating stockRating);

	/**
	 * Method used to update a companies collaborative rating
	 * @param symbol The companies symbol
	 * @param collabRating The companies collaborative rating
	 */
	void updateCompanyRating(String symbol, BigDecimal collabRating);

	/**
	 * Method used to check if a company classification exists
	 * @param symbol The companies symbol
	 */
	boolean existsCompanyClassification(String symbol);

	/**
	 * Method used to insert a company classification
	 * @param companyClassification The company classification
	 */
	void insertCompanyClassification(CompanyClassification companyClassification);

	/**
	 * Method used to update a company classification
	 * @param companyClassification The company classification
	 */
	void updateCompanyClassification(CompanyClassification companyClassification);

}