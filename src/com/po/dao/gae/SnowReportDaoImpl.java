package com.po.dao.gae;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.po.dao.SnowReportDao;
import com.po.domain.ski.ResortImage;
import com.po.domain.ski.SkiRegion;
import com.po.domain.ski.SkiResort;
import com.po.domain.ski.SnowReport;

/**
 * Implemented snow report data access class.
 * 
 * @author fburke
 */
public class SnowReportDaoImpl implements SnowReportDao {

	private static final Log log = LogFactory.getLog(SnowReportDaoImpl.class);

	private ObjectifyFactory objectifyFactory;

	/**
	 * @param objectifyFactory
	 */
	public void setObjectifyFactory(ObjectifyFactory objectifyFactory) {
		this.objectifyFactory = objectifyFactory;
	}

	/**
	 * {@inheritDoc}
	 */
	public void loadSkiRegions(List<SkiRegion> skiRegions) {
		log.info("Number of ski regions to load " + skiRegions.size());

		SkiRegion[] regions = skiRegions.toArray(new SkiRegion[skiRegions.size()]);
		Objectify ofy = objectifyFactory.begin();
		ofy.put(regions);
	}

	/**
	 * {@inheritDoc}
	 */
	public void loadSkiResorts(List<SkiResort> skiResorts) {
		log.info("Number of ski resorts to load " + skiResorts.size());

		SkiResort[] resorts = skiResorts.toArray(new SkiResort[skiResorts.size()]);
		Objectify ofy = objectifyFactory.begin();
		ofy.put(resorts);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SkiRegion> retrieveSkiRegions() {
		List<SkiRegion> skiRegions = new ArrayList<SkiRegion>();
		Objectify ofy = objectifyFactory.begin();
		skiRegions = ofy.query(SkiRegion.class).list();
		return skiRegions;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SkiResort> retrieveSkiResorts() {
		List<SkiResort> skiResorts = new ArrayList<SkiResort>();
		Objectify ofy = objectifyFactory.begin();
		skiResorts = ofy.query(SkiResort.class).list();
		return skiResorts;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SkiResort> retrieveSkiResortsForRegion(String regionName) {
		List<SkiResort> skiResorts = new ArrayList<SkiResort>();
		Objectify ofy = objectifyFactory.begin();
		skiResorts = ofy.query(SkiResort.class).filter("location =", regionName).list();
		return skiResorts;
	}

	/**
	 * {@inheritDoc}
	 */
	public void loadSnowReport(SnowReport snowReport) {
		Objectify ofy = objectifyFactory.begin();
		ofy.put(snowReport);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SnowReport> retrieveSnowReports() {
		List<SnowReport> reports = new ArrayList<SnowReport>();
		Objectify ofy = objectifyFactory.begin();
		reports = ofy.query(SnowReport.class).list();
		return reports;
	}

	/**
	 * {@inheritDoc}
	 */
	public SnowReport retrieveSnowReport(String resortName) {
		Objectify ofy = objectifyFactory.begin();
		SnowReport report = ofy.get(SnowReport.class, resortName);
		return report;
	}

	/**
	 * {@inheritDoc}
	 */
	public void saveResortImage(ResortImage resortImage) {
		Objectify ofy = objectifyFactory.begin();
		ofy.put(resortImage);
	}

}