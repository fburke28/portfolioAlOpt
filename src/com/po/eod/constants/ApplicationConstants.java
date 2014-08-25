/* File: ApplicationConstants.java
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

package com.po.eod.constants;

/**
 * Application constants class.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Nov 10, 2010   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
public class ApplicationConstants {

	private static final String QUOTE_SERVICE_URL="http://query.yahooapis.com/v1/public/yql?q=" +
		"select%20*%20from%20csv%20where%20url%3D'http%3A%2F%2Fdownload.finance.yahoo.com%2Fd%2Fquotes.csv" +
		"%3Fs%3D<SYMBOL>%26f%3Dsb4l1d1t1c1t8r%26e%3D.csv'%20and%20columns%3D'symbol%2CbookValue%2Cprice%2Cdate%2Ctime%2Cchange%2COneyrTargetPrice%2CPERatio'" +
		"&diagnostics=true";

}