
package com.po.domain;


public class OptClassifierDetails {

	private String classification;
	private String sector;

	/**
	 * Constructor with args
	 * @param classification
	 * @param sector
	 */
	public OptClassifierDetails(String classification, String sector) {
		super();
		this.classification = classification;
		this.sector = sector;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

}