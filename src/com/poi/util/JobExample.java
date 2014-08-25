package com.poi.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;  
import org.quartz.JobExecutionException;  
import org.springframework.scheduling.quartz.QuartzJobBean;  

public class JobExample extends QuartzJobBean {

	private static final Log LOG = LogFactory.getLog(JobExample.class);

	protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
		LOG.info("Job invoked at : " + new java.util.Date());
  
		// TODO: implement the job here
	}

}