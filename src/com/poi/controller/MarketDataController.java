/* File: MarketDataController.java
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
package com.poi.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.po.eod.invoker.EodCycleInvoker;
import com.poi.domain.MarketPerformanceData;
import com.poi.service.IMarketDataService;
import com.poi.view.MarketDataView;
import com.poi.view.MarketNews;

/**
 * Controller class used to handle acquiring market data.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>May 07, 2010   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
@Controller
@RequestMapping("/marketData")
public class MarketDataController {

	private static final Log log = LogFactory.getLog(MarketDataController.class);

	private IMarketDataService marketDataService;

	/**
	 * @param marketDataService Market data service to set.
	 */
	public void setMarketDataService(IMarketDataService marketDataService) {
		this.marketDataService = marketDataService;
	}

	/**
	 * Method used to retrieve company related news data
	 * @param symbol The company symbol
	 * @return String The rss news data
	 */
	@RequestMapping(value = "/companyNews", method = RequestMethod.GET)
	@ResponseBody
	public MarketNews retrieveCompanyNews(@RequestParam("symbol") String symbol) {
		log.info("Retrieving company news data for " + symbol);
		MarketNews marketNews = new MarketNews();
		String companyNews = marketDataService.retrieveSymbolNewsItems(symbol);
		marketNews.setItems(companyNews);
		return marketNews;
	}

	/**
	 * Method used to retrieve company related news data
	 * @param symbol The company symbol
	 * @return String The rss news data
	 */
	@RequestMapping(value = "/retrieveMarketData", method = RequestMethod.GET)
	@ResponseBody
	public MarketDataView retrieveMarketData(@RequestParam("dji") String dji,
		@RequestParam("nasdaq") String nasdaq, @RequestParam("sp") String sp) {
		log.info(String.format("Retrieving company news data for %s %s %s.", dji, nasdaq, sp));
		String[] symbols = {dji, nasdaq, sp};
		MarketDataView view = new MarketDataView();
		List<MarketPerformanceData> marketData = marketDataService.retrieveMarketData(symbols);
		view.setItems(marketData);
		return view;
	}

}