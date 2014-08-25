package com.poi.view;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.po.domain.SkiResort;

/**
 * View class used to return data.
 * 
 * @author fburke
 */
@XmlRootElement
public class SkiResortView {

	private List<SkiResort> skiResorts;

	/**
	 * @return skiResorts
	 */
	public List<SkiResort> getSkiResorts() {
		return skiResorts;
	}

	/**
	 * @param skiResorts
	 */
	public void setSkiResorts(List<SkiResort> skiResorts) {
		this.skiResorts = skiResorts;
	}

}