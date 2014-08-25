/* File: ClassificationLoader.java
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

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.po.dao.CompanyDao;
import com.po.domain.CompanyClassification;

/**
 * Command class used to run the the classification loader.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>May 14, 2011   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
public class ClassificationLoader implements Command {

	private static final Log log = LogFactory.getLog(ClassificationLoader.class);

	private static final BigDecimal BUY_LIMIT = new BigDecimal(0.90);
	private static final BigDecimal SELL_LIMIT = new BigDecimal(0.30);

	private static final String BUY_CLASSIFICATION = "BUY";
	private static final String HOLD_CLASSIFICATION = "HOLD";
	private static final String SELL_CLASSIFICATION = "SELL";

	private CompanyDao companyDao;

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	/**
	 * Method used to classify securities into BUY/HOLD/SELL types
	 */
	public void execute() {
		// Categorize neural network 
		/*List<CompanyClassification> classifications = poCompanyDao.retrieveOrderedClassification();
		log.info("Classification count " + classifications.size());
		if(!CollectionUtils.isEmpty(classifications)) {
			for(CompanyClassification classification : classifications) {
				if(classification.getOutput1().compareTo(BUY_LIMIT) >= 0 &&
					classification.getOutput2().compareTo(BUY_LIMIT) >= 0) {
					poCompanyDao.updateCompanyClassification(classification.getSymbol(), BUY_CLASSIFICATION);
				}
				else if(classification.getOutput1().compareTo(SELL_LIMIT) <= 0 &&
					classification.getOutput2().compareTo(BUY_LIMIT) >= 0) {
					poCompanyDao.updateCompanyClassification(classification.getSymbol(), SELL_CLASSIFICATION);
				}
				else {
					poCompanyDao.updateCompanyClassification(classification.getSymbol(), HOLD_CLASSIFICATION);
				}
			}
		}*/
	}

}