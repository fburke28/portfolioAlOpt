/* File: CompanyService.java
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.flex.remoting.RemotingInclude;

import com.po.dao.CompanyDao;
import com.po.dao.MarketDataDao;
import com.po.domain.Company;
import com.po.domain.CompanyClassification;
import com.po.domain.CompanyIdentifier;
import com.po.domain.Property;
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
public class CompanyService implements ICompanyService {

	private static final Log log = LogFactory.getLog(CompanyService.class);

	private CompanyDao companyDao;
	private MarketDataDao marketDataDao;

	/**
	 * @param companyDao
	 */
	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	/**
	 * @param marketDataDao
	 */
	public void setMarketDataDao(MarketDataDao marketDataDao) {
		this.marketDataDao = marketDataDao;
	}

	/**
	 * {@inheritDoc}
	 */
	public int retrieveCompanyCount() {
		return companyDao.retrieveCompanyCount();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<String> retrieveCompanySectors() {
		return companyDao.retrieveCompanySectors();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<String> retrieveCompanySymbols(String sector) {
		List<CompanyIdentifier> identifiers = companyDao.retrieveCompanySymbols();
		List<String> symbols = new ArrayList<String>();
		for(CompanyIdentifier identifier : identifiers) {
			if(sector == null || identifier.getSector().equalsIgnoreCase(sector)) {
				symbols.add(identifier.getSymbol());
			}
		}
		return symbols;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Company> retrieveCompanies() {
		return companyDao.retrieveCompanies();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Company> retrieveCompaniesForSector(String sector) {
		return companyDao.retrieveCompaniesForSector(sector);
	}

	/**
	 * {@inheritDoc}
	 */
	public Company retrieveCompanyForSymbol(String symbol) {
		log.info("Retrieving company data for " + symbol);

		if("LIQUIDITY".equals(symbol)) {
			return new Company();
		}
		Company company = companyDao.retrieveCompanyForSymbol(symbol);
		BigDecimal latestPrice = marketDataDao.retrieveLatestPriceForSymbol(symbol);
		if(null != latestPrice) {
			company.setPrice(latestPrice.doubleValue());
			log.info("Latest price set as " + latestPrice.doubleValue());
		}

		// Add properties
		List<Property> properties = new ArrayList<Property>();
		Property prop1 = new Property("Market Capitalization", company.getMarketCap());
		Property prop2 = new Property("Price Per Earnings", "" + company.getPricePerEarnings());
		Property prop3 = new Property("Price Per Sales", "" + company.getPricePerSales());
		Property prop4 = new Property("Price Per Book", "" + company.getPricePerBook());
		Property prop5 = new Property("Price Per Earnings Growth", "" + company.getPricePerEarningsGrowth());
		Property prop6 = new Property("EPS Current Year Growth", "" + company.getEpsCurrentYearGrowth());
		properties.add(prop1);
		properties.add(prop2);
		properties.add(prop3);
		properties.add(prop4);
		properties.add(prop5);
		properties.add(prop6);
		company.setProperties(properties);

		return company;
	}

	/**
	 * {@inheritDoc}
	 */
	public void insertCompany(Company company) {
		Map<String, List<Company>> companyMap = new HashMap<String, List<Company>>();
		List<Company> companies = new ArrayList<Company>();
		companies.add(company);
		companyMap.put(company.getSector(), companies);
		companyDao.insertCompany(companyMap);
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateCompany(Company company) {
		List<Company> companies = new ArrayList<Company>();
		companies.add(company);
		companyDao.updateCompany(companies);
	}

	/**
	 * {@inheritDoc}
	 */
	public StockRating retrieveStockRating(String userName, String symbol) {
		return companyDao.retrieveStockRating(userName, symbol);
	}

	/**
	 * {@inheritDoc}
	 */
	public void persistStockRating(StockRating stockRating) {
		companyDao.persistStockRating(stockRating);
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateCompanyRating(String symbol, BigDecimal collabRating) {
		companyDao.updateCompanyRating(symbol, collabRating);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean existsCompanyClassification(String symbol) {
		return companyDao.existsCompanyClassification(symbol);
	}

	/**
	 * {@inheritDoc}
	 */
	public void insertCompanyClassification(CompanyClassification companyClassification) {
		companyDao.insertCompanyClassification(companyClassification);
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateCompanyClassification(CompanyClassification companyClassification) {
		companyDao.updateCompanyClassification(companyClassification);
	}

}