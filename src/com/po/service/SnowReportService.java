package com.po.service;

import java.util.List;

import com.po.domain.ski.Item;
import com.po.domain.ski.ResortImage;
import com.po.domain.ski.SkiRegion;
import com.po.domain.ski.SkiResort;
import com.po.domain.ski.SnowReport;

/**
 * Snow report service interface.
 * 
 * @author fburke
 */
public interface SnowReportService {

	/**
	 * Load ski region data.
	 */
	void loadSkiRegions();

	/**
	 * Load ski resort data.
	 */
	void loadSkiResorts();

	/**
	 * Retrieve information on all ski regions.
	 * @return List of ski regions.
	 */
	List<SkiRegion> retrieveSkiRegions();

	/**
	 * Retrieve information on all ski resorts.
	 * @return List of ski resorts.
	 */
	List<SkiResort> retrieveSkiResorts();

	/**
	 * Retrieve information on ski resorts for a given region.
	 * @param regionName
	 * @return List of ski resorts.
	 */
	List<SkiResort> retrieveSkiResortsForRegion(String regionName);

	/**
	 * Load ski snow report data.
	 * @param Item Snow report item.
	 */
	void loadSnowReport(Item snowReport);
	
	/**
	 * Retrieve snow report information.
	 * @return List of snow reports.
	 */
	List<SnowReport> retrieveSnowReports();

	/**
	 * Retrieve snow report information for a given resort.
	 * @param resortName
	 * @return Snow report.
	 */
	SnowReport retrieveSnowReport(String resortName);

	/**
	 * Save resort image.
	 * @param resortImage Resort image
	 */
	void saveResortImage(ResortImage resortImage);

}