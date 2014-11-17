/* File: EodCommandDriver.java
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

package com.po.eod.driver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.po.eod.invoker.EodCycleInvoker;

/**
 * Command driver class used to run a set of end of day stock cycle commands.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Oct 21, 2010   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
public class EodCommandDriver {

	private static final Log log = LogFactory.getLog(EodCommandDriver.class);

	/**
	 * Default constructor.
	 */
	public EodCommandDriver() {

	}

	/**
	 * Test driver to run application.
	 */
	public static void main(String[] args) {
		log.info("Starting EOD cycle command driver.");

		// Create spring application context
		String[] config = new String[] {
			"spring/opt-cycle-context.xml"
		};

		ApplicationContext context = new ClassPathXmlApplicationContext(config);

		// Initialize end of day cycle command invoker
		EodCycleInvoker invoker = (EodCycleInvoker)context.getBean("eodCycleInvoker");
		invoker.runCycle();

		log.info("Triggering EOD cycle command driver shutdown...");
	}

}