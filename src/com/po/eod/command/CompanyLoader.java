/* File: CompanyLoader.java
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
package com.po.eod.command;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.po.dao.CompanyDao;
import com.po.domain.Company;

/**
 * Command class used to load company data.
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
public class CompanyLoader implements Command {

	private static final Log log = LogFactory.getLog(CompanyLoader.class);

	private CompanyDao companyDao;

	/**
	 * Setter for Spring initialization
	 * @param companyDao
	 */
	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	/**
	 * Method used to check/load company data on initial end of day cycle run.
	 */
	public void execute() {
		log.info("Executing company loader command.");

		// Check if the company table is populated
		int companyCount = companyDao.retrieveCompanyCount();

		if(companyCount > 0) {
			log.info(companyCount + " companies are already loaded.");
			return;
		}

		// Load company data from locally stored finviz.csv datasource
		//CSVReader reader = null;
		try {
			Resource resource = new ClassPathResource("data/finviz.csv");
			InputStream stream = resource.getInputStream();
			InputStreamReader insReader = new InputStreamReader(stream);
			//reader = new CSVReader(insReader, ',', '\"', 1);
			String [] nextLine = null;
			int loadedCompanies = 0;
			Map<String, List<Company>> sector2Companies = new HashMap<String, List<Company>>();
			List<Company> companiesToAdd;
		    while (nextLine  /*(nextLine = reader.readNext())*/ != null) {
		    	Company company = new Company();
		    	this.readCompanyLine(company, nextLine);

		    	companiesToAdd = sector2Companies.get(company.getSector());
		    	if(companiesToAdd == null) {
		    		companiesToAdd = new ArrayList<Company>();
		    	}
		    	companiesToAdd.add(company);
		    	sector2Companies.put(company.getSector(), companiesToAdd);
		    	loadedCompanies++;
		    }
		    log.info(loadedCompanies + " companies to load.");
		    companyDao.insertCompany(sector2Companies);
		} catch(Exception e) {
			log.error("Error encountered loading company data.", e);
		} /*finally {
			try {
				reader.close();
			} catch (IOException e) {
				log.warn("IO error closing CSV reader.");
			}
		}*/
	}

	/**
	 * Read the row from the CSV file and set into the Company object.
	 */
	private void readCompanyLine(Company company, String [] nextLine) {
		company.setSymbol(nextLine[1]);
    	company.setCompanyDesc(nextLine[2]);
    	company.setSector(nextLine[3]);
    	company.setIndustry(nextLine[4]);
    	company.setCountry(nextLine[5]);

    	if(nextLine[7].length() > 0) {
    		Double pricePerEarnings = new Double(nextLine[7]);
    		company.setPricePerEarnings(pricePerEarnings);
    	}
    	if(nextLine[8].length() > 0) {
    		Double price = new Double(nextLine[8]);
    		company.setPrice(price);
    	}
    	if(nextLine[9].length() > 0) {
    		company.setChange(new Double(nextLine[9].substring(0, nextLine[9].length() - 1)));
    	}
	}

}