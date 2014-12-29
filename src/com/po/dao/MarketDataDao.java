/* File: MarketDataDao.java
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

package com.po.dao;

import java.math.BigDecimal;
import java.util.List;

import com.po.domain.Company;
import com.po.domain.IndexData;

/**
 * Data access interface used to retrieve market data from injected request-uri.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Feb 13, 2011   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
public interface MarketDataDao {

	/**
	 * Method used to retrieve end of day market data for the
	 * specified symbols
	 * @param symbols The company symbols
	 * @return list of end of day company details
	 */
	List<Company> retrieveEodDetails(List<String> symbols);
	
	/**
	 * Method used to retrieve from data for a given index and timeframe
	 * @param index Market index
	 * @param day The day to retrieve data from
	 * @param month The month to retrieve data from
	 * @param year The year to retrieve data from
	 * @return the index performance data
	 */
	List<IndexData> retrieveMarketData(String index, int day, int month, int year);

	/**
	 * Method to retrieve latest price.
	 */
	

	/**
	 * Method to retrieve symbol related news items
	 * @param symbol The specified company symbol
	 */
	String retrieveSymbolNewsItems(String symbol);

	/**
	 * Method used to retrieve latest price for given symbol
	 * @param symbol The specified company symbol
	 */
	BigDecimal retrieveLatestPriceForSymbol(String symbol);

}