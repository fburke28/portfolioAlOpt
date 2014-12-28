/* File: EodUpdateCycle.java
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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.po.dao.CompanyDao;
import com.po.dao.MarketDataDao;
import com.po.domain.Company;
import com.po.domain.CompanyIdentifier;

/**
 * Command class used to update local datastore with updated information.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Nov 07, 2010   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
public class EodUpdateCycle implements Command {

	private static final Log log = LogFactory.getLog(EodUpdateCycle.class);

	private static final int PACKET_SIZE = 80;

	private CompanyDao companyDao;

	private MarketDataDao marketDataDao;

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	public void setMarketDataDao(MarketDataDao marketDataDao) {
		this.marketDataDao = marketDataDao;
	}

	/**
	 * Method used to update company data with end of day market data
	 */
	public void execute() {
		log.info("Executing update cycle command.");

		// Retrieve all company symbols
		List<CompanyIdentifier> identifiers = companyDao.retrieveCompanySymbols();
		log.info("Symbol count: " + identifiers.size());

		// for each symbol retrieve closing market data
		List<String> symbolPacket = new ArrayList<String>();
		List<String> sectorPacket = new ArrayList<String>();
		List<Company> companies = new ArrayList<Company>();
		for(CompanyIdentifier identifier : identifiers) {
			symbolPacket.add(identifier.getSymbol());
			sectorPacket.add(identifier.getSector());
			if(symbolPacket.size() == PACKET_SIZE) {
				List<Company> companyPac = marketDataDao.retrieveEodDetails(symbolPacket);
				for(int i=0; i<companyPac.size(); i++) {
					companyPac.get(i).setSector(sectorPacket.get(i));
				}
				companies.addAll(companyPac);
				symbolPacket.clear();
				sectorPacket.clear();
			}
		}
		log.info("Updating companies.");
		// Update sector : list of companies
		companyDao.updateCompany(companies);
	}

}