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
public class SnowReport implements Serializable {

	@Id private String resortName;
	private String publishDate;
	private String regionName;
	private String reportDescription;
	private String openStatus;
	private Long baseDepth;
	private Long snowFall48hr;
	private String surfaceCondition;
	private String snowFall48HrMetric;

	public String getResortName() {
		return resortName;
	}

	public void setResortName(String resortName) {
		this.resortName = resortName;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getReportDescription() {
		return reportDescription;
	}

	public void setReportDescription(String reportDescription) {
		this.reportDescription = reportDescription;
	}

	public String getOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(String openStatus) {
		this.openStatus = openStatus;
	}

	public Long getBaseDepth() {
		return baseDepth;
	}

	public void setBaseDepth(Long baseDepth) {
		this.baseDepth = baseDepth;
	}

	public Long getSnowFall48hr() {
		return snowFall48hr;
	}

	public void setSnowFall48hr(Long snowFall48hr) {
		this.snowFall48hr = snowFall48hr;
	}

	public String getSurfaceCondition() {
		return surfaceCondition;
	}

	public void setSurfaceCondition(String surfaceCondition) {
		this.surfaceCondition = surfaceCondition;
	}

	public String getSnowFall48HrMetric() {
		return snowFall48HrMetric;
	}

	public void setSnowFall48HrMetric(String snowFall48HrMetric) {
		this.snowFall48HrMetric = snowFall48HrMetric;
	}

}