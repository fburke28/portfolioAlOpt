package com.po.domain.ski;

import javax.xml.bind.annotation.XmlElement;

/**
 * Item RSS domain object.
 * 
 * @author fburke
 */
public class ItemRSS {

	private String openStatus;

	@XmlElement(name="open_staus", namespace="ots")
	public String getOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(String openStatus) {
		this.openStatus = openStatus;
	}

}