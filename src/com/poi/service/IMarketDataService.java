/* File: IMarketDataService.java
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

import java.util.List;

import com.poi.domain.MarketPerformanceData;

/**
 * Service interface used to retrieve market data from injected request-uri.
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
public interface IMarketDataService {

	/**
	 * Method used to retrieve market data.
	 * @param symbol The symbol to query on
	 * @return List of market data performance values
	 */
	List<MarketPerformanceData> retrieveMarketData(String[] symbol);

	/**
	 * Method used to retrieve symbol related news.
	 * @param symbol The symbol to query on
	 * @return String The rss xml
	 */
	String retrieveSymbolNewsItems(String symbol);

}