/* File: SecurityClassifier.java
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
package com.po.eod.command;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neuroph.core.NeuralNetwork;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.po.dao.CompanyDao;
import com.po.domain.Company;
import com.po.domain.CompanyClassification;

/**
 * Command class used to run the security classifier.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Mar 1, 2011   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
public class SecurityClassifier implements Command {

	private static final Log log = LogFactory.getLog(SecurityClassifier.class);

	private CompanyDao companyDao;

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	/**
	 * Method used to classify securities into BUY/HOLD/SELL types
	 */
	public void execute() {
		// load the saved network
		Resource resource = new ClassPathResource("neuralNets/technology.nnet");
		NeuralNetwork neuralNetwork = null;
		InputStream stream = null;
		try {
			stream = resource.getInputStream();
			neuralNetwork = NeuralNetwork.load(stream);
		}
		catch (IOException e) {
			log.error("Error occurred setting up neural network.", e);
			return;
		}
		finally {
			IOUtils.closeQuietly(stream);
		}
		// Retrieve all sectors
		List<String> sectors = companyDao.retrieveCompanySectors();
		log.info("Sector count: " + sectors.size());

		// Retrieve all company data
		//List<Company> companies = companyDao.retrieveCompaniesForSector("Technology");
		List<Company> companies = companyDao.retrieveCompanies();
		log.info("Company count: " + companies.size());

		// For each company perform classification
		for(Company company : companies) {
			log.info(company.toString());
			// set network input 
			neuralNetwork.setInput(
				company.getPricePerEarnings().doubleValue(),
				company.getChange().doubleValue(),
				company.getPricePerSales().doubleValue(),
				company.getPricePerBook().doubleValue(),
				company.getPricePerEarningsGrowth().doubleValue(),
				company.getEpsCurrentYearGrowth().doubleValue(),
				company.getCollabRating().doubleValue()
			); 
			// calculate network 
			neuralNetwork.calculate(); 
			// get network output 
			Vector <Double> networkOutput = neuralNetwork.getOutput(); 
			log.info(networkOutput);

			// Update classification results table
			CompanyClassification classification = new CompanyClassification();
			classification.setSymbol(company.getSymbol());
			classification.setOutput1(new BigDecimal(networkOutput.get(0)));
			classification.setOutput2(new BigDecimal(networkOutput.get(1)));
			classification.setOutlook(new Long(0));
			classification.setUpdateTmstp(new Date());
			
			/*if(poCompanyDao.existsCompanyClassification(company.getSymbol())) {
				poCompanyDao.updateCompanyClassification(classification);
			}
			else {
				poCompanyDao.insertCompanyClassification(classification);
			}*/
		}
	}

}