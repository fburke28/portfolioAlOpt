/* File: EodCycleInvoker.java
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
package com.po.eod.invoker;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.po.eod.command.Command;

/**
 * Class used to invoke the end of day cycle commands.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Oct 30, 2010   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
public class EodCycleInvoker extends QuartzJobBean {

	private List<Command> commands;

	/**
	 * @param commands
	 */
	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}

	/**
	 * Method used to run the end of day cycle
	 */
	public void runCycle() {
		for(Command command : commands) {
			command.execute();
		}
	}

	/**
	 * Run the optimizer service scheduled by quartz.
	 * @param ctx
	 * @throws JobExecutionException
	 */
	protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
 		runCycle();
	}

}