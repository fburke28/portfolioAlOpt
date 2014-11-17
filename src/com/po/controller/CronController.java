package com.po.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.po.eod.invoker.EodCycleInvoker;

@Controller
@RequestMapping("/cron")
public class CronController {

	private static final Log log = LogFactory.getLog(CronController.class);

	private EodCycleInvoker eodCycleInvoker;

	/**
	 * @param eodCycleInvoker
	 */
	public void setEodCycleInvoker(EodCycleInvoker eodCycleInvoker) {
		this.eodCycleInvoker = eodCycleInvoker;
	};

    //cron job running this
	@RequestMapping(value="/runCycle", method = RequestMethod.GET)
	public void addCount(ModelMap model) {
		log.info("Starting EOD cycle command driver.");

		eodCycleInvoker.runCycle();
		log.info("Triggering EOD cycle command driver shutdown...");
	}

}