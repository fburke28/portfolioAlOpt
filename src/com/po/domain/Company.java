/* File: Company.java
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

package com.po.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.springframework.stereotype.Repository;

import com.googlecode.objectify.annotation.Entity;

/**
 * Domain class used to represent Company data.
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
@SuppressWarnings("serial")
@Repository
@Entity
public class Company implements Serializable {

	@Id private String symbol;
	private String companyDesc;
	private String sector;
	private String industry;
	private String country;
	private String marketCap;
	private Double pricePerEarnings;
	private Double price;
	private Double change;
	private Double pricePerSales;
	private Double pricePerBook;
	private Double pricePerEarningsGrowth;
	private Double epsCurrentYearGrowth;
	private Double collabRating;
	private Double systemRating;
	private Date updateTmstmp;
	
	private List<Property> properties;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCompanyDesc() {
		return companyDesc;
	}

	public void setCompanyDesc(String companyDesc) {
		this.companyDesc = companyDesc;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(String marketCap) {
		this.marketCap = marketCap;
	}

	public Double getPricePerEarnings() {
		return pricePerEarnings;
	}

	public void setPricePerEarnings(Double pricePerEarnings) {
		this.pricePerEarnings = pricePerEarnings;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getChange() {
		return change;
	}

	public void setChange(Double change) {
		this.change = change;
	}

	public Double getPricePerSales() {
		return pricePerSales;
	}

	public void setPricePerSales(Double pricePerSales) {
		this.pricePerSales = pricePerSales;
	}

	public Double getPricePerBook() {
		return pricePerBook;
	}

	public void setPricePerBook(Double pricePerBook) {
		this.pricePerBook = pricePerBook;
	}

	public Double getPricePerEarningsGrowth() {
		return pricePerEarningsGrowth;
	}

	public void setPricePerEarningsGrowth(Double pricePerEarningsGrowth) {
		this.pricePerEarningsGrowth = pricePerEarningsGrowth;
	}

	public Double getEpsCurrentYearGrowth() {
		return epsCurrentYearGrowth;
	}

	public void setEpsCurrentYearGrowth(Double epsCurrentYearGrowth) {
		this.epsCurrentYearGrowth = epsCurrentYearGrowth;
	}

	public Double getCollabRating() {
		return collabRating;
	}

	public void setCollabRating(Double collabRating) {
		this.collabRating = collabRating;
	}

	public Double getSystemRating() {
		return systemRating;
	}

	public void setSystemRating(Double systemRating) {
		this.systemRating = systemRating;
	}

	public Date getUpdateTmstmp() {
		return updateTmstmp;
	}

	public void setUpdateTmstmp(Date updateTmstmp) {
		this.updateTmstmp = updateTmstmp;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Company [symbol=");
		builder.append(symbol);
		builder.append(", companyDesc=");
		builder.append(companyDesc);
		builder.append(", sector=");
		builder.append(sector);
		builder.append(", industry=");
		builder.append(industry);
		builder.append(", country=");
		builder.append(country);
		builder.append(", marketCap=");
		builder.append(marketCap);
		builder.append(", pricePerEarnings=");
		builder.append(pricePerEarnings);
		builder.append(", price=");
		builder.append(price);
		builder.append(", change=");
		builder.append(change);
		builder.append(", pricePerSales=");
		builder.append(pricePerSales);
		builder.append(", pricePerBook=");
		builder.append(pricePerBook);
		builder.append(", pricePerEarningsGrowth=");
		builder.append(pricePerEarningsGrowth);
		builder.append(", epsCurrentYearGrowth=");
		builder.append(epsCurrentYearGrowth);
		builder.append(", collabRating=");
		builder.append(collabRating);
		builder.append(", systemRating=");
		builder.append(systemRating);
		builder.append(", updateTmstmp=");
		builder.append(updateTmstmp);
		builder.append("]");
		return builder.toString();
	}

}