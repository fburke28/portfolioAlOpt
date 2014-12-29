package com.po.domain.ski;

import java.io.Serializable;

import javax.persistence.Id;

import org.springframework.stereotype.Repository;

import com.googlecode.objectify.annotation.Entity;

/**
 * Domain class used to model a ski resort.
 * 
 * @author fburke
 */
@SuppressWarnings("serial")
@Repository
@Entity
public class SkiResort implements Serializable {

	@Id private String resortName;
	private String location;
	private Long verticalFeet;
	private Long numberOfTrials;
	private Long numberOfLifts;
	private String conditionsRequestUrl;

	/**
	 * @return
	 */
	public String getResortName() {
		return resortName;
	}

	/**
	 * @param resortName
	 */
	public void setResortName(String resortName) {
		this.resortName = resortName;
	}

	/**
	 * @return
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return verticalFeet
	 */
	public Long getVerticalFeet() {
		return verticalFeet;
	}
	
	/**
	 * @param verticalFeet
	 */
	public void setVerticalFeet(Long verticalFeet) {
		this.verticalFeet = verticalFeet;
	}
	
	/**
	 * @return numberOfTrials
	 */
	public Long getNumberOfTrials() {
		return numberOfTrials;
	}
	
	/**
	 * @param numberOfTrials
	 */
	public void setNumberOfTrials(Long numberOfTrials) {
		this.numberOfTrials = numberOfTrials;
	}

	/**
	 * @return numberOfLifts
	 */
	public Long getNumberOfLifts() {
		return numberOfLifts;
	}

	/**
	 * @param numberOfLifts
	 */
	public void setNumberOfLifts(Long numberOfLifts) {
		this.numberOfLifts = numberOfLifts;
	}

	/**
	 * @return conditionsRequestUrl
	 */
	public String getConditionsRequestUrl() {
		return conditionsRequestUrl;
	}

	/**
	 * @param conditionsRequestUrl
	 */
	public void setConditionsRequestUrl(String conditionsRequestUrl) {
		this.conditionsRequestUrl = conditionsRequestUrl;
	}

}