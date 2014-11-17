/* File: MarketDataService.java
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.flex.remoting.RemotingInclude;

import com.po.dao.MarketDataDao;
import com.po.domain.IndexData;
import com.poi.domain.MarketPerformanceData;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * Service class used to retrieve market data from injected request-uri.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Jan 12, 2011   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
public class MarketDataService implements IMarketDataService {

	private static final Log log = LogFactory.getLog(MarketDataService.class);

	private MarketDataDao marketDataDao;

	/**
	 * @param marketDataDao
	 */
	public void setMarketDataDao(MarketDataDao marketDataDao) {
		this.marketDataDao = marketDataDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@RemotingInclude
	public List<MarketPerformanceData> retrieveMarketData(String[] symbols) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);

		List<MarketPerformanceData> indexPerformanceData = new ArrayList<MarketPerformanceData>();
		for(String sym : symbols) {
			List<IndexData> currentData = marketDataDao.retrieveMarketData(sym,
				cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));

			// Add to returned data
			if(indexPerformanceData.size() == 0 ) {
				for(IndexData data : currentData) {
					MarketPerformanceData marketData = new MarketPerformanceData();
					marketData.setTimeframe(data.getTimeframe());
					if("DJIA".equals(sym)) {
						marketData.setDjiClose(data.getIndexClose());
					}
					if("%5EIXIC".equals(sym)) {
						marketData.setNasdaqClose(data.getIndexClose());
					}
					if("%5EGSPC".equals(sym)) {
						marketData.setSpClose(data.getIndexClose());
					}
					indexPerformanceData.add(marketData);
				}
			}
			else {
				for(int i=0; i < currentData.size(); i++ ) {
					if("DJIA".equals(sym)) {
						indexPerformanceData.get(i).setDjiClose(currentData.get(i).getIndexClose());
					}
					if("%5EIXIC".equals(sym)) {
						indexPerformanceData.get(i).setNasdaqClose(currentData.get(i).getIndexClose());
					}
					if("%5EGSPC".equals(sym)) {
						indexPerformanceData.get(i).setSpClose(currentData.get(i).getIndexClose());
					}
				}
			}	
		}

		Collections.reverse(indexPerformanceData);
		return indexPerformanceData;
	}

	/**
	 * {@inheritDoc}
	 */
	public String retrieveSymbolNewsItems(String symbol) {
		return marketDataDao.retrieveSymbolNewsItems(symbol);
	}

}