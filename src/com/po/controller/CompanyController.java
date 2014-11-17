package com.po.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.po.domain.Company;
import com.po.domain.CompanyIdentifier;
import com.po.domain.StockRating;
import com.po.service.ICompanyService;

/**
 * Controller class used to handle company data.
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
@RequestMapping("/companyData")
public class CompanyController {

	private static final Log log = LogFactory.getLog(CompanyController.class);

	private ICompanyService companyService;

	/**
	 * @param companyService
	 */
	public void setCompanyService(ICompanyService companyService) {
		this.companyService = companyService;
	}

	/**
	 * Method used to retrieve company sectors.
	 * @return List of sectors
	 */
	@RequestMapping(value = "/retrieveSectors", method = RequestMethod.GET)
	public @ResponseBody List<String> retrieveSectorsByJson() {
		List<String> sectors = companyService.retrieveCompanySectors();
		log.info("Number of sectors retrieved " + sectors.size());
		return sectors;
	}

	/**
	 * Method used to retrieve company details.
	 * @return List of sectors
	 */
	@RequestMapping(value = "/retrieveCompanies", method = RequestMethod.GET)
	public @ResponseBody List<Company> retrieveCompanies() {
		List<Company> companies = companyService.retrieveCompanies();
		log.info("Company retrieved " + companies.size());
		return companies;
	}

	/**
	 * Method used to retrieve company details.
	 * @return List of sectors
	 */
	@RequestMapping(value = "/retrieveCompanyDetails", method = RequestMethod.GET)
	public @ResponseBody Company retrieveCompanyDetailsByJson(String symbol) {
		Company company = companyService.retrieveCompanyForSymbol(symbol);
		log.info("Company retrieved " + company);
		return company;
	}

	/**
	 * Method used to retrieve symbols.
	 * @return List of sectors
	 */
	@RequestMapping(value = "/retrieveSymbols", method = RequestMethod.GET)
	public @ResponseBody List<CompanyIdentifier> retrieveSymbols() {
		return companyService.retrieveCompanySymbols();
	}

	/**
	 * Method used to retrieve company details.
	 * @return List of sectors
	 */
	@RequestMapping(value = "/retrieveStockRating", method = RequestMethod.GET)
	public @ResponseBody StockRating retrieveStockRating(String userName, String symbol) {
		StockRating rating = companyService.retrieveStockRating(userName, symbol);
		if(null != rating) {
			log.info("Rating retrieved " + rating);
		} else {
			log.info("No rating retrieved for " + userName + ", " + symbol);
		}

		return rating;
	}

	/**
	 * Method used to register a user
	 * @param account The user account details
	 * @return view
	 */
	@RequestMapping(value = "/persistStockRating", method = RequestMethod.POST)
	@ResponseBody
	public StockRating persistStockRating(@RequestBody StockRating stockRating) {
		log.info("Persisting stock rating for user " + stockRating.getUserName());

		companyService.persistStockRating(stockRating);
		return stockRating;
	}

}