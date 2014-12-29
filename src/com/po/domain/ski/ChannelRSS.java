package com.po.domain.ski;

import java.util.List;

/**
 * Snow RSS domain object.
 * 
 * @author fburke
 */
public class ChannelRSS {

	private List<ItemRSS> item;

	public List<ItemRSS> getItem() {
		return item;
	}

	public void setItem(List<ItemRSS> item) {
		this.item = item;
	}

}