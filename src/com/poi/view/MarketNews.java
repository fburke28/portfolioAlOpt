
package com.poi.view;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MarketNews {

	private String items;

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

}