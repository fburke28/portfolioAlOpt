/* File: ViewController.java
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

package com.po.controller;

import java.security.Principal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.po.service.IAccountService;

/**
 * Controller class used to handle viewing pages.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Mar 23, 2011   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
@Controller
@RequestMapping("/view")
public class ViewController {

	private static final Log log = LogFactory.getLog(ViewController.class);

	private IAccountService accountService;

	/**
	 * @param accountService
	 */
	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * Test view.
	 * @return view name
	 */
	@RequestMapping(value="/testView", method = RequestMethod.GET)
	public String testView(Model model, Principal user) {
		return "test";
	}

	/**
	 * Method used to handle current holdings page display
	 * @return tile name
	 */
	@RequestMapping(value="/currentHoldings", method = RequestMethod.GET)
	public String viewCurrentHoldings(Model model, Principal user) {
		String returnView = "";
		if(accountService.portfolioExists(user.getName())) {
			String accountNumber = accountService.retrieveAccountNumber(user.getName());
			model.addAttribute("accountNumber", accountNumber);
			model.addAttribute("viewIndex", 0);
			returnView = "manageHoldings";
		}
		else {
			model.addAttribute("accountNumber", "");
			returnView = "home";
		}
		return returnView;
	}

	/**
	 * Method used to handle manage holdings page display
	 * @return tile name
	 */
	@RequestMapping(value="/manageHoldings", method = RequestMethod.GET)
	public String viewManageHoldings(Model model, Principal user) {
		String returnView = "";
		if(accountService.portfolioExists(user.getName())) {
			String accountNumber = accountService.retrieveAccountNumber(user.getName());
			model.addAttribute("accountNumber", accountNumber);
			model.addAttribute("viewIndex", 1);
			returnView = "manageHoldings";
		}
		else {
			model.addAttribute("accountNumber", "");
			returnView = "home";
		}
		return returnView;
	}

	/**
	 * Method used to handle manage holdings page display
	 * @return tile name
	 */
	@RequestMapping(value="/optimizedResults", method = RequestMethod.GET)
	public String viewOptimizedResults(Model model, Principal user) {
		String returnView = "";
		if(accountService.portfolioExists(user.getName())) {
			String accountNumber = accountService.retrieveAccountNumber(user.getName());
			model.addAttribute("accountNumber", accountNumber);
			model.addAttribute("viewIndex", 2);
			returnView = "manageHoldings";
		}
		else {
			model.addAttribute("accountNumber", "");
			returnView = "home";
		}
		return returnView;
	}

	/**
	 * Method used to handle account details page display
	 * @return tile name
	 */
	@RequestMapping(value="/accountDetails", method = RequestMethod.GET)
	public String viewAccountDetails(Model model, Principal user) {
		String returnView = "";
		if(accountService.portfolioExists(user.getName())) {
			String accountNumber = accountService.retrieveAccountNumber(user.getName());
			model.addAttribute("accountNumber", accountNumber);
			returnView = "accountDetails";
		}
		else {
			model.addAttribute("accountNumber", "");
			returnView = "home";
		}
		return returnView;
	}

	/**
	 * Method used to handle user details page display
	 * @return tile name
	 */
	@RequestMapping(value="/userDetails", method = RequestMethod.GET)
	public String viewUserDetails(Model model, Principal user) {
		if(accountService.portfolioExists(user.getName())) {
			String accountNumber = accountService.retrieveAccountNumber(user.getName());
			model.addAttribute("accountNumber", accountNumber);
		}
		else {
			model.addAttribute("accountNumber", "");
		}
		return "userDetails";
	}

	/**
	 * Method used to handle rating maintenance page display
	 * @return tile name
	 */
	@RequestMapping(value="/userRatings", method = RequestMethod.GET)
	public String viewRatingsMaintenance(Model model, Principal user) {
		model.addAttribute("viewIndex", 1);
		if(accountService.portfolioExists(user.getName())) {
			String accountNumber = accountService.retrieveAccountNumber(user.getName());
			model.addAttribute("accountNumber", accountNumber);
		}
		else {
			model.addAttribute("accountNumber", "");
		}
		return "ratingsMaintenance";
	}

	/**
	 * Method used to handle rating maintenance page display
	 * @return tile name
	 */
	@RequestMapping(value="/securityRatings", method = RequestMethod.GET)
	public String viewSecurityRatings(Model model, Principal user) {
		String returnView = "";
		if(accountService.portfolioExists(user.getName())) {
			String accountNumber = accountService.retrieveAccountNumber(user.getName());
			model.addAttribute("accountNumber", accountNumber);
			model.addAttribute("viewIndex", 0);
			returnView = "ratingsMaintenance";
		}
		else {
			model.addAttribute("accountNumber", "");
			returnView = "home";
		}
		return returnView;
	}

}