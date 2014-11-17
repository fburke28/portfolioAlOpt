package com.po.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

//import au.com.bytecode.opencsv.CSVReader;







import com.po.dao.SnowReportDao;
import com.po.domain.ski.Item;
import com.po.domain.ski.ResortImage;
import com.po.domain.ski.SkiRegion;
import com.po.domain.ski.SkiResort;
import com.po.domain.ski.SnowReport;

/**
 * Implemented snow report service class.
 * 
 * @author fburke
 */
public class SnowReportServiceImpl implements SnowReportService {

	private static final Log log = LogFactory.getLog(SnowReportServiceImpl.class);

	private SnowReportDao snowReportDao;

	/**
	 * @param snowReportDao
	 */
	public void setSnowReportDao(SnowReportDao snowReportDao) {
		this.snowReportDao = snowReportDao;
	}

	/**
	 * {@inheritDoc}
	 */
	public void loadSkiRegions() {
		try {
			List<SkiRegion> skiRegions = new ArrayList<SkiRegion>();
			Resource resource = new ClassPathResource("data/skiRegions.csv");
			InputStream stream = resource.getInputStream();
			InputStreamReader insReader = new InputStreamReader(stream);
			//CSVReader reader = new CSVReader(insReader, ',', '\"', 1);
			String [] nextLine = null;
			while (nextLine /*(nextLine = reader.readNext())*/ != null) {
				SkiRegion skiRegion = new SkiRegion();
				skiRegion.setRegionName(nextLine[0]);
				skiRegion.setConditionsUrl(nextLine[1]);
				skiRegion.setNumResorts(Long.valueOf(nextLine[2]));
				skiRegions.add(skiRegion);
			}
			snowReportDao.loadSkiRegions(skiRegions);
		} catch(Exception e) {
			log.error("Error encountered loading ski region data.", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void loadSkiResorts() {
		try {
			List<SkiResort> skiResorts = new ArrayList<SkiResort>();
			Resource resource = new ClassPathResource("data/skiResorts.csv");
			InputStream stream = resource.getInputStream();
			InputStreamReader insReader = new InputStreamReader(stream);
			//CSVReader reader = new CSVReader(insReader, ',', '\"', 1);
			String [] nextLine = null;
			while (nextLine /*(nextLine = reader.readNext())*/ != null) {
				SkiResort skiResort = new SkiResort();
				skiResort.setResortName(nextLine[0]);
				skiResort.setLocation(nextLine[1]);
				skiResort.setVerticalFeet(Long.valueOf(nextLine[2]));
				skiResort.setNumberOfTrials(Long.valueOf(nextLine[3]));
				skiResort.setNumberOfLifts(Long.valueOf(nextLine[4]));
				skiResort.setConditionsRequestUrl(nextLine[5]);
				skiResorts.add(skiResort);
			}
			snowReportDao.loadSkiResorts(skiResorts);
		} catch(Exception e) {
			log.error("Error encountered loading ski resort data.", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SkiRegion> retrieveSkiRegions() {
		return snowReportDao.retrieveSkiRegions();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SkiResort> retrieveSkiResorts() {
		return snowReportDao.retrieveSkiResorts();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SkiResort> retrieveSkiResortsForRegion(String regionName) {
		return snowReportDao.retrieveSkiResortsForRegion(regionName);
	}

	/**
	 * {@inheritDoc}
	 */
	public void loadSnowReport(Item snowReport) {
		SnowReport report = new SnowReport();
		
		// Set report values
		report.setBaseDepth(snowReport.getBaseDepth().longValue());
		report.setOpenStatus(snowReport.getOpenStaus());
		report.setPublishDate(snowReport.getPubDate());
		report.setRegionName(snowReport.getRegionName());
		report.setReportDescription(snowReport.getDescription());
		report.setResortName(snowReport.getTitle());
		report.setSnowFall48hr(snowReport.getSnowfall48Hr().longValue());
		report.setSnowFall48HrMetric(snowReport.getSnowfall48HrMetric());
		report.setSurfaceCondition(snowReport.getSurfaceCondition());
		
		snowReportDao.loadSnowReport(report);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SnowReport> retrieveSnowReports() {
		return snowReportDao.retrieveSnowReports();
	}

	/**
	 * {@inheritDoc}
	 */
	public SnowReport retrieveSnowReport(String resortName) {
		return snowReportDao.retrieveSnowReport(resortName);
	}

	/**
	 * {@inheritDoc}
	 */
	public void saveResortImage(ResortImage resortImage) {
		snowReportDao.saveResortImage(resortImage);
	}

}