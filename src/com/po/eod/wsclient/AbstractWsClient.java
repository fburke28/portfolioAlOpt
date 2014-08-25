/* File: AbstractWsClient.java
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

import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NTCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Base class of ws client.
 * 
 * @author a412060
 * @since
 */
public class AbstractWsClient {

    private static final Log LOG = LogFactory.getLog(AbstractWsClient.class);

    private MultiThreadedHttpConnectionManager connectionManager;
    private int timeOutInMilliSeconds;
    private String serviceUrl;

    // fields for NTLM credentials
    private String userName;
    private String password;
    private String domain;

    public MultiThreadedHttpConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public void setConnectionManager(MultiThreadedHttpConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    /**
     * @return Returns the timeOutInMilliSeconds.
     */
    public int getTimeOutInMilliSeconds() {
        return timeOutInMilliSeconds;
    }

    /**
     * @param timeOutInMilliSeconds The timeOutInMilliSeconds to set.
     */
    public void setTimeOutInMilliSeconds(int timeOutInMilliSeconds) {
        this.timeOutInMilliSeconds = timeOutInMilliSeconds;
    }

    /**
     * @return Returns the serviceUrl.
     */
    public String getServiceUrl() {
        return serviceUrl;
    }

    /**
     * @param serviceUrl The serviceUrl to set.
     */
    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    /**
     * @return Returns the userName.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName The userName to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return Returns the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return Returns the domain.
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain The domain to set.
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * @return ConfigurationContext ConfigurationContext
     */
    protected ConfigurationContext getConfigurationContext() throws Exception {
        // Cache the http client
        HttpClient client = new HttpClient(connectionManager);

        if (userName != null && password != null && domain != null) {
            LOG.info("WsClient credentials: userName: " + userName + ", domain: " + domain);

            // apply NT based credentials for web service authentication
            client.getState().setCredentials(AuthScope.ANY, new NTCredentials(userName, password, "", domain));
        }
        else {
            LOG.info("No credentials provided. Skipping credentials.");
        }

        ConfigurationContext configContext = ConfigurationContextFactory.createDefaultConfigurationContext();
        configContext.setProperty(HTTPConstants.REUSE_HTTP_CLIENT, "true");
        configContext.setProperty(HTTPConstants.CACHED_HTTP_CLIENT, client);

        return configContext;
    }

}