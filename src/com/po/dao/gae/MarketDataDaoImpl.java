/* File: MarketDataDaoImpl.java
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
package com.po.dao.gae;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.po.dao.MarketDataDao;
import com.po.domain.Company;
import com.po.domain.IndexData;

/**
 * Data access class used to retrieve market data from injected request-uri.
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
public class MarketDataDaoImpl implements MarketDataDao {

	private static final Log log = LogFactory.getLog(MarketDataDaoImpl.class);

	private String indexDataUri;
	private String indexTwoDataUri;
	private String newsDataUri;
	private String priceDataUri;

	/**
	 * @param indexDataUri
	 */
	public void setIndexDataUri(String indexDataUri) {
		this.indexDataUri = indexDataUri;
	}

	public void setIndexTwoDataUri(String indexTwoDataUri) {
		this.indexTwoDataUri = indexTwoDataUri;
	}

	/**
	 * @param newsDataUri
	 */
	public void setNewsDataUri(String newsDataUri) {
		this.newsDataUri = newsDataUri;
	}

	/**
	 * @param priceDataUri
	 */
	public void setPriceDataUri(String priceDataUri) {
		this.priceDataUri = priceDataUri;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Company> retrieveEodDetails(List<String> symbols) {
		// Create request URI
		StringBuffer url = new StringBuffer(indexTwoDataUri);
		for(String symbol : symbols) {
			url.append(symbol + "+");
		}
		url.deleteCharAt(url.lastIndexOf("+"));
		url.append("&f=s"); // Symbol
		url.append("j1"); // Market capitalization
		url.append("r"); // P/E
		url.append("c1"); // P/C
		url.append("p5"); // P/S
		url.append("p6"); // P/B
		url.append("r5"); // PEG
		url.append("r6"); // Price/EPS Estimate Current Year
		url.append("l1"); // Last Trade Price

		log.info("Retrieving data for request URI: " + url.toString());

		BufferedReader in = null;
		List<Company> companies = new ArrayList<Company>();
		try {
			// Try URL fetch for GAE
			URL fetchUrl = new URL(url.toString());
            in = new BufferedReader(new InputStreamReader(fetchUrl.openStream()));
			// The first line is the header, ignoring
			String inputLine = "";
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.startsWith("<"))
					continue;
				String[] item = inputLine.split(",");
				Company company = new Company();
				company.setSymbol(item[0].substring(1, item[0].length() - 1));
				company.setMarketCap(item[1]);
				company.setPricePerEarnings("N/A".equals(item[2]) ? new Double(0) : new Double(item[2]));
				Double priceChange;
				if("+".equals(item[3].charAt(0))) {
					priceChange = new Double(item[3].substring(1));
				}
				else {
					if("N/A".equals(item[3])) {
						priceChange = new Double(0);
					}
					else {
						priceChange = new Double(item[3]);
					}
				}
				company.setChange(priceChange);
				company.setPricePerSales("N/A".equals(item[4]) ? new Double(0) : new Double(item[4]));
				company.setPricePerBook("N/A".equals(item[5]) ? new Double(0) : new Double(item[5]));
				company.setPricePerEarningsGrowth("N/A".equals(item[6]) ? new Double(0) : new Double(item[6]));
				company.setEpsCurrentYearGrowth("N/A".equals(item[7]) ? new Double(0) : new Double(item[7]));
				company.setPrice("N/A".equals(item[8]) ? new Double(0) : new Double(item[8]));
				company.setUpdateTmstmp(new Date());
				companies.add(company);
			}
		} catch(IOException e) {
			log.error("Error retrieving market data", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
		return companies;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<IndexData> retrieveMarketData(String index, int day,
		int month, int year) {

		// Create request URI
		StringBuffer url = new StringBuffer(indexDataUri + index);
		url.append("&a=" + month);
		url.append("&b=" + day);
		url.append("&c=" + year);

		log.info("Retrieving data for request URI: " + url.toString());

		List<IndexData> marketPerformanceData = new ArrayList<IndexData>();
		try {
			//HttpClient client = new HttpClient();
			//client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
			//HttpMethod method = new GetMethod(url.toString());
			//method.setFollowRedirects(true);
			//client.executeMethod(method);

			// Try URL fetch for GAE
			URL fetchUrl = new URL(url.toString());
            BufferedReader in = new BufferedReader(new InputStreamReader(fetchUrl.openStream()));

			//BufferedReader in = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
			// The first line is the header, ignoring
			String inputLine = in.readLine();

			while ((inputLine = in.readLine()) != null) {
				if (inputLine.startsWith("<"))
					continue;
				String[] item = inputLine.split(",");
				if (item.length < 5)
					continue;

				IndexData data = new IndexData();
				data.setTimeframe(item[0]);
				data.setIndexClose(item[4]);
				marketPerformanceData.add(data);
			}
			in.close();
		}
		catch(Exception e) {
			log.error("Error retrieving market data for " + index, e);
		}

		return marketPerformanceData;
	}

	/**
	 * {@inheritDoc}
	 */
	public String retrieveSymbolNewsItems(String symbol) {
		String rss = "";
		// Create request URI
		StringBuffer url = new StringBuffer(newsDataUri + symbol);
		try {
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
			HttpMethod method = new GetMethod(url.toString());
			method.setFollowRedirects(true);
			client.executeMethod(method);
			rss = method.getResponseBodyAsString();
			log.info("Returned XML: " + rss);
		}
		catch(Exception e) {
			log.error("Error retrieving news items for " + symbol, e);
		}
		return rss;
	}

	/**
	 * {@inheritDoc}
	 */
	public BigDecimal retrieveLatestPriceForSymbol(String symbol) {
		// Create request URI
		StringBuffer url = new StringBuffer(priceDataUri + symbol);
		url.append("&f=l1");

		log.info("Retrieving latest price data for request URI: " + url.toString());
		BigDecimal latestPrice = null;
		try {
			URL fetchUrl = new URL(url.toString());
            BufferedReader in = new BufferedReader(new InputStreamReader(fetchUrl.openStream()));
            String inputLine = in.readLine();
            while ((inputLine = in.readLine()) != null) {
            	latestPrice = new BigDecimal(inputLine);
			}
			in.close();
		}
		catch(Exception e) {
			log.error("Error retrieving latest price data for " + symbol, e);
		}

		return latestPrice;
	}

}