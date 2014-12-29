/* File: CompanyDaoImpl.java
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.po.dao.CompanyDao;
import com.po.domain.Companies;
import com.po.domain.Company;
import com.po.domain.CompanyDetails;
import com.po.domain.CompanyIdentifier;
import com.po.domain.CompanyClassification;
import com.po.domain.StockRating;

/**
 * Data access layer class for Company related data.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Oct 30, 2010   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
public class CompanyDaoImpl implements CompanyDao {

	private static final Log log = LogFactory.getLog(CompanyDaoImpl.class);

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
	public int retrieveCompanyCount() {
		Objectify ofy = objectifyFactory.begin();
		try {
			Companies companies = ofy.get(Companies.class, 1);
			List<CompanyIdentifier> symbols = companies.getIdentifiers();
			return symbols.size();
		}
		catch(NotFoundException nfe) {
			log.warn("No data found for companies.", nfe);
			return 0;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<String> retrieveCompanySectors() {
		Objectify ofy = objectifyFactory.begin();
		Companies companies = ofy.get(Companies.class, 1);
		List<CompanyIdentifier> symbols = companies.getIdentifiers();
		Set<String> sectors = new HashSet<String>();
		for(CompanyIdentifier indentifier : symbols) {
			sectors.add(indentifier.getSector());
		}
		return new ArrayList<String>(sectors);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CompanyIdentifier> retrieveCompanySymbols() {
		Objectify ofy = objectifyFactory.begin();
		Companies companies = ofy.get(Companies.class, 1);
		return companies.getIdentifiers();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Company> retrieveCompanies() {
		Objectify ofy = objectifyFactory.begin();

		List<String> sectors = this.retrieveCompanySectors();
		List<Company> companies = new ArrayList<Company>();
		for(String sector : sectors) {
			CompanyDetails companiesBySector = ofy.get(CompanyDetails.class, sector);
			companies.addAll(companiesBySector.getCompanies());
		}

		return companies;
	}

	/**
	 * {@inheritDoc}
	 */
	public Company retrieveCompanyForSymbol(String symbol) {
		Objectify ofy = objectifyFactory.begin();
		List<CompanyDetails> allCompanies = ofy.query(CompanyDetails.class).list();
		for(CompanyDetails companies : allCompanies) {
			log.debug("Getting companies for " + companies.getSector());
			List<Company> secCompanies = companies.getCompanies();
			for(Company company : secCompanies) {
				if(symbol.equalsIgnoreCase(company.getSymbol())) {
					return company;
				}
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Company> retrieveCompaniesForSector(String sector) {
		Objectify ofy = objectifyFactory.begin();

		List<Company> companies = new ArrayList<Company>();
		CompanyDetails companiesBySector = ofy.get(CompanyDetails.class, sector);
		companies.addAll(companiesBySector.getCompanies());

		return companies;
	}

	/**
	 * {@inheritDoc}
	 */
	public void insertCompany(Map<String, List<Company>> sector2Company) {
		// Company metaData
		List<CompanyIdentifier> companyMD = new ArrayList<CompanyIdentifier>();
		// Company details
		Objectify ofy = objectifyFactory.begin();
		Set<Entry<String, List<Company>>> entries = sector2Company.entrySet();
		for(Entry<String, List<Company>> entry : entries) {
			List<Company> companies = entry.getValue();
			CompanyDetails companyDetails = new CompanyDetails();
			companyDetails.setSector(entry.getKey());
			companyDetails.setCompanies(companies);
			ofy.put(companyDetails);
			log.info("Added companies for sector " + entry.getKey());
			for(Company company : companies) {
				CompanyIdentifier identifiers = new CompanyIdentifier();
				identifiers.setSector(entry.getKey());
				identifiers.setSymbol(company.getSymbol());
				companyMD.add(identifiers);
			}
		}
		log.info("Company metadata size " + companyMD.size());
		Companies metaData = new Companies();
		metaData.setId(1);
		metaData.setIdentifiers(companyMD);
		ofy.put(metaData);
		log.info("Successfully inserted company data for num sectors: " + sector2Company.size());
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateCompany(List<Company> companies) {
		log.info("Updating companies");
		Objectify ofy = objectifyFactory.begin();
		Map<String, List<Company>> sector2Companies = new HashMap<String, List<Company>>();
		List<Company> companiesToAdd;
		for(Company company : companies) {
			companiesToAdd = sector2Companies.get(company.getSector());
			if(companiesToAdd == null) {
	    		companiesToAdd = new ArrayList<Company>();
	    	}
	    	companiesToAdd.add(company);
	    	sector2Companies.put(company.getSector(), companiesToAdd);
		}
		Set<Entry<String, List<Company>>> entries = sector2Companies.entrySet();
		for(Entry<String, List<Company>> entry : entries) {
			List<Company> comps = entry.getValue();
			CompanyDetails companyDetails = new CompanyDetails();
			companyDetails.setSector(entry.getKey());
			companyDetails.setCompanies(comps);
			log.info("Updating number of companies " + comps.size() + " for sector " + entry.getKey());
			ofy.put(companyDetails);
			log.info("Updated companies for sector " + entry.getKey());
		}

		log.info("Successfully updated company data for number of sectors: " + sector2Companies.size());
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public StockRating retrieveStockRating(String userName, String symbol) {
		Objectify ofy = objectifyFactory.begin();
		try {
			StockRating rating = ofy.query(StockRating.class).filter("userName =", userName).filter("symbol =", symbol).get();
			return rating;
		} catch(NotFoundException nfe) {
			log.warn("No rating found for symbol.", nfe);
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void persistStockRating(StockRating stockRating) {
		Objectify ofy = objectifyFactory.begin();
		ofy.put(stockRating);

		log.info("Successfully inserted account data for: " + stockRating.getUserName());
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void updateCompanyRating(String symbol, BigDecimal collabRating) {
		Map map = new HashMap();
		map.put("collabRating", collabRating);
		map.put("updateTmstp", new Date());
		map.put("symbol", symbol);

		//getSqlMapClientTemplate().update("Company.updateRating", map);
		log.info("Successfully updated collaborative company rating: " + symbol);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public boolean existsCompanyClassification(String symbol) {
		Map map = new HashMap();
		map.put("symbol", symbol);
		String retVal = ""; // ((String) getSqlMapClientTemplate().queryForObject("Company.companyClassification", map));
		return StringUtils.isEmpty(retVal) ? false : true;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void insertCompanyClassification(CompanyClassification companyClassification) {
		Map map = new HashMap();
		map.put("symbol", companyClassification.getSymbol());
		map.put("output1", companyClassification.getOutput1());
		map.put("output2", companyClassification.getOutput2());
		map.put("outlook", companyClassification.getOutlook());
		map.put("updateTmstp", new Date());

		//getSqlMapClientTemplate().insert("Company.insertCompanyClassifier", map);
		log.info("Successfully inserted company classification data: " + companyClassification.getSymbol());
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void updateCompanyClassification(CompanyClassification companyClassification) {
		Map map = new HashMap();
		map.put("symbol", companyClassification.getSymbol());
		map.put("output1", companyClassification.getOutput1());
		map.put("output2", companyClassification.getOutput2());
		map.put("outlook", companyClassification.getOutlook());
		map.put("updateTmstp", new Date());

		//getSqlMapClientTemplate().update("Company.updateCompanyClassifier", map);
		log.info("Successfully updated company classification data: " + companyClassification.getSymbol());
	}

}