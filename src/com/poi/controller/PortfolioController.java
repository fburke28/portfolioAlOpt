package com.poi.controller;

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
import com.poi.service.IPortfolioService;
import com.poi.view.AccountNumberView;
import com.poi.view.HoldingsView;
import com.poi.view.PortfolioConstraintsView;
import com.poi.view.SectorsView;

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

	/**
	 * Method used to create a portfolio.
	 * @param portfolio The portfolio details
	 * @return view
	 */
	@RequestMapping(value = "/retrievePortfolioConstraints", method = RequestMethod.GET)
	@ResponseBody
	public PortfolioConstraintsView retrievePortfolioConstraints(String accountNumber) {
		PortfolioConstraintsView view = new PortfolioConstraintsView();
		Portfolio portfolio = portfolioService.retrievePortfolioConstraints(accountNumber);
		view.setPortfolio(portfolio);
		log.info("Retrieved account details for " + accountNumber);
		return view;
	}

	/**
	 * Method used to create a portfolio.
	 * @param portfolio The portfolio details
	 * @return view
	 */
	@RequestMapping(value = "/createPortfolio", method = RequestMethod.POST)
	@ResponseBody
	public AccountNumberView createPortfolio(Portfolio portfolio) {
		AccountNumberView view = new AccountNumberView();
		String accountNumber = portfolioService.createPortfolio(portfolio);
		view.setAccountNumber(accountNumber);
		log.info("Created portfolio with account number " + accountNumber);
		return view;
	}

	/**
	 * Method used to retrieve portfolio holdings.
	 * @return List of holdings
	 */
	@RequestMapping(value = "/retrieveHoldings", method = RequestMethod.GET)
	@ResponseBody
	public HoldingsView retrieveHoldings(String accountNumber) {
		HoldingsView view = new HoldingsView();
		List<Holding> holdings = portfolioService.retrieveHoldings(accountNumber);
		view.setHoldings(holdings);
		return view;
	}

}