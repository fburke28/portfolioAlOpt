/* File: PortfolioDaoImpl.java
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
package com.po.dao.gae;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.Query;
import com.po.dao.PortfolioDao;
import com.po.domain.Holding;
import com.po.domain.Portfolio;
import com.po.domain.PortfolioConstraint;
import com.po.domain.HoldingUpdate;

/**
 * Data access class used to retrieve portfolio data.
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
public class PortfolioDaoImpl implements PortfolioDao {

	private static final Log log = LogFactory.getLog(PortfolioDaoImpl.class);

	private ObjectifyFactory objectifyFactory;

	/**
	 * @param objectifyFactory
	 */
	public void setObjectifyFactory(ObjectifyFactory objectifyFactory) {
		this.objectifyFactory = objectifyFactory;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Portfolio> retrievePortfolios() {
		Objectify ofy = objectifyFactory.begin();
		try {
			Query<Portfolio> queryPortfolios = ofy.query(Portfolio.class);
			return queryPortfolios.list();
		}
		catch(NotFoundException e) {
			log.warn("No data found for portfolios.", e);
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Holding> retrieveHoldings(String accountNumber) {
		log.info("Retrieving holdings for account: " + accountNumber);
		Objectify ofy = objectifyFactory.begin();
		try {
			Query<Holding> querySet = ofy.query(Holding.class).filter("accountNumber =", accountNumber);
			log.info("Retrieved holdings " + querySet.list().size());
			return querySet.list();
		}
		catch(NotFoundException e) {
			log.warn("No data found for holdings.", e);
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public BigDecimal retrieveCashHolding(String accountNumber) {
		log.info("Retrieving cash holding for account: " + accountNumber);

		Map map = new HashMap();
		map.put("accountNumber", accountNumber);
		return null; //(BigDecimal)getSqlMapClientTemplate().queryForObject("Portfolio.cashHolding", map);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void updatePortfolio(Portfolio portfolio) {
		Objectify ofy = objectifyFactory.begin();
		ofy.put(portfolio);

		log.info("Successfully updated portfolio data for: " + portfolio.getAccountNumber());
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void truncateOptHoldings() {
		log.info("Truncating optimized holdings table.");

		//getSqlMapClientTemplate().update("Portfolio.truncateOptHoldings");
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<PortfolioConstraint> retrievePortfolioConstraints() {

		return null; //(List<PortfolioConstraint>)getSqlMapClientTemplate().queryForList("Portfolio.retrievePortfolioConst");
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public Portfolio retrievePortfolio(String accountNumber) {
		log.info("Retrieving account details for: " + accountNumber);

		Map map = new HashMap();
		map.put("accountNumber", accountNumber);
		return null; // (Portfolio)getSqlMapClientTemplate().queryForObject("Portfolio.retrievePortfolio", map);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void insertOptHoldings(List<Holding> optHoldings) {
		for(Holding holding : optHoldings) {
			Map map = new HashMap();
			map.put("accountNumber", holding.getAccountNumber());
			map.put("symbol", holding.getSymbol());
			//map.put("quantity", holding.getQuantity());
			map.put("tradePrice", holding.getTradePrice());
			map.put("update_Tmstp", new Date());

			//getSqlMapClientTemplate().insert("Portfolio.insertHolding", map);
			log.info("Successfully inserted holding for: " + holding.getSymbol());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void insertOptCashHolding(Holding holding) {
		Map map = new HashMap();
		map.put("accountNumber", holding.getAccountNumber());
		map.put("symbol", holding.getSymbol());
		//map.put("quantity", holding.getQuantity());
		map.put("tradePrice", holding.getTradePrice());
		map.put("update_Tmstp", new Date());

		//getSqlMapClientTemplate().update("Portfolio.insertHolding", map);
		log.info("Successfully inserted optimized cash holding for: " + holding.getSymbol());
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public Portfolio retrievePortfolioConstraints(String accountNumber) {
		log.info("Retrieving portfolio constraints for: " + accountNumber);
		Objectify ofy = objectifyFactory.begin();
		Portfolio portfolio = ofy.query(Portfolio.class).filter("accountNumber =", accountNumber).get();
		return portfolio;

		/*Map map = new HashMap();
		map.put("accountNumber", accountNumber);
		return (Portfolio)getSqlMapClientTemplate().queryForObject("Portfolio.retrievePortfolioConstraints", map);*/
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public boolean checkAccountNumberAvailability(String accountNumber) {
		log.info("Checking account number availability for: " + accountNumber);

		Objectify ofy = objectifyFactory.begin();
		try {
			Portfolio portfolio = ofy.get(Portfolio.class, accountNumber);
			return false;
		}
		catch(NotFoundException nfe) {
			log.debug("Portfolio account number is available.");
			return true;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Holding> retrieveOptHoldings(String accountNumber) {
		log.info("Retrieving optimized holdings for account: " + accountNumber);

		Map map = new HashMap();
		map.put("accountNumber", accountNumber);
		List<Holding> holdings = null; //(List<Holding>)getSqlMapClientTemplate().queryForList("Portfolio.optholdings", map);
		return holdings;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void insertHolding(HoldingUpdate holdingUpdate) {
		holdingUpdate.setObjectifyValues();
		Objectify ofy = objectifyFactory.begin();
		ofy.put(holdingUpdate);
		log.info("Successfully inserted holding for: " + holdingUpdate.getSymbol());
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void updateHolding(HoldingUpdate holdingUpdate) {
		Map map = new HashMap();
		map.put("quantity", holdingUpdate.getUpdatedQuantity());
		map.put("updateTmstp", new Date());
		map.put("holdingKey", holdingUpdate.getHoldingKey());

		//getSqlMapClientTemplate().update("Portfolio.updateHolding", map);
		log.info("Successfully updated holding for: " + holdingUpdate.getSymbol());
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void updateCashHolding(HoldingUpdate holdingUpdate) {
		Map map = new HashMap();
		map.put("quantity", holdingUpdate.getUpdatedQuantity());
		map.put("update_Tmstp", new Date());
		map.put("accountNumber", holdingUpdate.getAccountNumber());
		map.put("symbol", holdingUpdate.getSymbol());

		//getSqlMapClientTemplate().update("Portfolio.updateCashHolding", map);
		log.info("Successfully updated holding for: " + holdingUpdate.getSymbol());
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void deleteHolding(HoldingUpdate holdingUpdate) {
		Map map = new HashMap();
		map.put("holdingKey", holdingUpdate.getHoldingKey());

		//getSqlMapClientTemplate().delete("Portfolio.deleteHolding", map);
		log.info("Successfully deleted holding for: " + holdingUpdate.getSymbol());
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void insertPortfolio(Portfolio portfolio) {
		Objectify ofy = objectifyFactory.begin();
		ofy.put(portfolio);

		log.info("Successfully inserted portfolio data for: " + portfolio.getAccountNumber());
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void insertPortfolioConstraints(Portfolio portfolio) {
		Map map = new HashMap();
		map.put("accountNumber", portfolio.getAccountNumber());
		map.put("maxPercentSingleStock", portfolio.getMaxPercentSingleStock());
		map.put("maxPercentSector", portfolio.getMaxPercentSector());
		map.put("dailyVolatility", portfolio.getDailyVolatility());
		map.put("preferredSector", portfolio.getPreferredSector());

		//getSqlMapClientTemplate().insert("Portfolio.insertPortfolioConstraints", map);
		log.info("Successfully inserted portfolio constraint data for: " + portfolio.getAccountNumber());
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void updatePortfolioConstraints(Portfolio portfolio) {
		Map map = new HashMap();
		map.put("maxPercentSingleStock", portfolio.getMaxPercentSingleStock());
		map.put("maxPercentSector", portfolio.getMaxPercentSector());
		map.put("dailyVolatility", portfolio.getDailyVolatility());
		map.put("preferredSector", portfolio.getPreferredSector());
		map.put("updateTmstp", new Date());
		map.put("accountNumber", portfolio.getAccountNumber());

		//getSqlMapClientTemplate().update("Portfolio.updatePortfolioConstraints", map);
		log.info("Successfully updated portfolio constraint data for: " + portfolio.getAccountNumber());
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void updatePortfolioDetails(Portfolio portfolio) {
		Map map = new HashMap();
		map.put("investment", portfolio.getInvestment());
		map.put("cashTotal", portfolio.getCashTotal());
		map.put("equityTotal", portfolio.getEquityTotal());
		map.put("totalValue", portfolio.getTotalValue());
		map.put("dailyPercentChange", portfolio.getDailyPercentChange());
		map.put("percentChanged", portfolio.getPercentChanged());
		map.put("updateTmstmp", portfolio.getUpdateTmstp());
		map.put("accountNumber", portfolio.getAccountNumber());

		//getSqlMapClientTemplate().update("Portfolio.updatePortfolio", map);
		log.info("Successfully updated portfolio data for: " + portfolio.getUserName());
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void deleteHoldings(String accountNumber) {
		log.info("Deleting holdings for: " + accountNumber);

		Map map = new HashMap();
		map.put("accountNumber", accountNumber);
		//getSqlMapClientTemplate().delete("Portfolio.deleteHoldings", map);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void deleteOptHoldings(String accountNumber) {
		log.info("Deleting optimized holdings for: " + accountNumber);

		Map map = new HashMap();
		map.put("accountNumber", accountNumber);
		//getSqlMapClientTemplate().delete("Portfolio.deleteOptHoldings", map);
	}
	
}