package com.po.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.po.domain.Holding;
import com.po.domain.Portfolio;
import com.po.service.IPortfolioService;

/**
 * Controller class used to handle portfolio data.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>June 03, 2012  Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
@Controller
@RequestMapping("/portfolioData")
public class PortfolioController {

	private static final Log log = LogFactory.getLog(PortfolioController.class);

	private IPortfolioService portfolioService;

	/**
	 * @param portfolioService
	 */
	public void setPortfolioService(IPortfolioService portfolioService) {
		this.portfolioService = portfolioService;
	}

	

}