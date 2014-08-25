/* File: YqlServiceClient.java
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

package com.po.eod.wsclient;

import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Client class used to connect to the Yahoo data services.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Oct 22, 2010   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
public class YqlServiceClient extends AbstractWsClient {

	private static Log log = LogFactory.getLog(YqlServiceClient.class);
	
	private HttpClient client;

	public YqlServiceClient() {

	}

	/**
     * init method.
     */
    public void init() throws Exception {
    	client = new HttpClient(getConnectionManager());
    }

    /**
     * Retrieve details from the yahoo data service call.
     */
    public void retrieveEodDetails() {
    	GetMethod method = new GetMethod("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20csv%20where%20url%3D'http%3A%2F%2Fdownload.finance.yahoo.com%2Fd%2Fquotes.csv%3Fs%3DYHOO%2CGOOG%2CAAPL%26f%3Dsl1d1t1c1ohgv%26e%3D.csv'%20and%20columns%3D'symbol%2Cprice%2Cdate%2Ctime%2Cchange%2Ccol1%2Chigh%2Clow%2Ccol2'&diagnostics=true" /*getServiceUrl()*/);
    	try {
    		int statusCode = client.executeMethod(method);
        	if (statusCode != HttpStatus.SC_OK) {
        		log.error("Method failed: " + method.getStatusLine());
        	}
        	else {
        		InputStream rstream = null;
        		rstream = method.getResponseBodyAsStream();
        		Document response = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(rstream);
        		XPathFactory factory = XPathFactory.newInstance();
                XPath xPath = factory.newXPath();
                // Get all search Result nodes
                NodeList nodes = (NodeList) xPath.evaluate("query/results/row", response, XPathConstants.NODESET);
                int nodeCount = nodes.getLength();
                // iterate over search Result nodes
        		System.out.println("Number of nodes: " + nodeCount);

        		/*OutputFormat format = new OutputFormat(response);
                format.setLineWidth(65);
                format.setIndenting(true);
                format.setIndent(2);
                Writer out = new StringWriter();
                XMLSerializer serializer = new XMLSerializer(out, format);
                serializer.serialize(response);

                System.out.println(out.toString());*/

        		// iterate over search Result nodes
                for (int i = 0; i < nodeCount; i++) {
                	// Get each xpath expression as a string
                    String symbol = (String) xPath.evaluate("symbol", nodes.item(i), XPathConstants.STRING);
                    String price = (String) xPath.evaluate("price", nodes.item(i), XPathConstants.STRING);
                    String date = (String) xPath.evaluate("date", nodes.item(i), XPathConstants.STRING);

                    // print out the Title, Summary, and URL for each search result
                    System.out.println("symbol: " + symbol);
                    System.out.println("price: " + price);
                    System.out.println("date: " + date);
                    System.out.println("-----------");
                }
        	}
    	}
    	catch(Exception e) {
    		log.error("YqlServiceClient exception", e);
    	}
    }

}