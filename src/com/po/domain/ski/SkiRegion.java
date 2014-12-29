package com.po.domain.ski;

import java.io.Serializable;

import javax.persistence.Id;

import org.springframework.stereotype.Repository;

import com.googlecode.objectify.annotation.Entity;

/**
 * Domain class used to model a ski region.
 * 
 * @author fburke
 */
@SuppressWarnings("serial")
@Repository
@Entity
public class SkiRegion implements Serializable {

	@Id private String regionName;
	private String description;
	private String conditionsUrl;
	private Long numResorts;

	/**
	 * @return regionName
	 */
	public String getRegionName() {
		return regionName;
	}
	
	/**
	 * @param regionName
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	/**
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return conditionsUrl
	 */
	public String getConditionsUrl() {
		return conditionsUrl;
	}
	
	/**
	 * @param conditionsUrl
	 */
	public void setConditionsUrl(String conditionsUrl) {
		this.conditionsUrl = conditionsUrl;
	}

	/**
	 * @return numResorts
	 */
	public Long getNumResorts() {
		return numResorts;
	}

	/**
	 * @param numResorts
	 */
	public void setNumResorts(Long numResorts) {
		this.numResorts = numResorts;
	}

}