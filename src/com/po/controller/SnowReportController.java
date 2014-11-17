package com.po.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.appengine.api.datastore.Blob;
import com.po.domain.ski.Item;
import com.po.domain.ski.ResortImage;
import com.po.domain.ski.Rss;
import com.po.domain.ski.SkiRegion;
import com.po.domain.ski.SkiResort;
import com.po.domain.ski.SnowReport;
import com.po.service.SnowReportService;

/**
 * Controller to handle snow report information requests.
 * 
 * @author fburke
 */
@Controller
@RequestMapping("/snowReport")
public class SnowReportController {

	private static final Log log = LogFactory.getLog(SnowReportController.class);

	private SnowReportService snowReportService;

	/**
	 * @param snowReportService
	 */
	public void setSnowReportService(SnowReportService snowReportService) {
		this.snowReportService = snowReportService;
	}

	/**
	 * Method handles request to load datastore with ski regions information.
	 */
	@RequestMapping(value="/loadSkiRegions", method = RequestMethod.GET)
	public void loadSkiRegions() {
		snowReportService.loadSkiRegions();
	}

	/**
	 * Method handles request to load datastore with ski resorts information.
	 */
	@RequestMapping(value="/loadSkiResorts", method = RequestMethod.GET)
	public void loadSkiResorts() {
		snowReportService.loadSkiResorts();
	}

	/**
	 * Method handles request to retrieve ski region information.
	 * @return Region data
	 */
	@RequestMapping(value = "/retrieveSkiRegions", method = RequestMethod.GET)
	public @ResponseBody List<SkiRegion> retrieveSkiRegions() {
		List<SkiRegion> skiRegions = snowReportService.retrieveSkiRegions();
		return skiRegions;
	}

	/**
	 * Method handles request to retrieve ski resorts information.
	 * @return view
	 */
	@RequestMapping(value = "/retrieveSkiResorts", method = RequestMethod.GET)
	public @ResponseBody List<SkiResort> retrieveSkiResorts() {
		List<SkiResort> skiResorts = snowReportService.retrieveSkiResorts();
		return skiResorts;
	}

	/**
	 * Method handles request to retrieve ski resorts information for a given region.
	 * @return view
	 */
	@RequestMapping(value = "/retrieveSkiResortsForRegion", method = RequestMethod.GET)
	public @ResponseBody List<SkiResort> retrieveSkiResortsForRegion(String regionName) {
		List<SkiResort> skiResorts = snowReportService.retrieveSkiResortsForRegion(regionName);
		return skiResorts;
	}

	/**
	 * Method handles request to populate local datastore with ski resorts snow
	 * and weather conditions.
	 * @throws IOException 
	 * @throws JAXBException 
	 */
	@RequestMapping(value="/loadSnowReport", method = RequestMethod.GET)
	public void loadSnowReport() throws IOException, JAXBException {
		log.info("Loading snow report information.");

		List<SkiRegion> skiRegions = snowReportService.retrieveSkiRegions();
		log.info("Number of ski regions retrieved " + skiRegions.size());

		JAXBContext jaxbContext = JAXBContext.newInstance(Rss.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		for(SkiRegion skiRegion : skiRegions) {
			// Try URL fetch for GAE
			URL fetchUrl = new URL(skiRegion.getConditionsUrl());
			Rss snowRss = (Rss) unmarshaller.unmarshal(fetchUrl);
			
			List<Item> reportItems = snowRss.getChannel().getItem();
			log.info("Loading " + reportItems.size() + " reports for " + skiRegion.getRegionName());
			for(Item reportItem : reportItems) {
				snowReportService.loadSnowReport(reportItem);
			}
		}
	}

	/**
	 * Method handles request to retrieve local datastore of ski resorts snow
	 * and weather conditions.
	 */
	@RequestMapping(value="/retrieveSnowReports", method = RequestMethod.GET)
	public @ResponseBody List<SnowReport> retrieveSnowReports() {
		List<SnowReport> snowReports = snowReportService.retrieveSnowReports();
		log.info("Number of snow reports retrieved " + snowReports.size());

		return snowReports;
	}

	/**
	 * Method handles request to retrieve local datastore of ski resorts snow
	 * and weather conditions.
	 * @param resortName
	 */
	@RequestMapping(value="/retrieveSnowReport", method = RequestMethod.GET)
	public @ResponseBody SnowReport retrieveSnowReport(String resortName) {
		SnowReport snowReport = snowReportService.retrieveSnowReport(resortName);
		log.info("Snow report retrieved for " + snowReport.getResortName());

		return snowReport;
	}

	/**
	 * Method to handle loading resort image.
	 */
	@RequestMapping(value="/loadResortImage", method = RequestMethod.POST)
	public void loadResortImage(/*@RequestParam("resortName") String resortName,*/ HttpServletRequest request) {
		//log.info("Loading image for " + resortName);
		try {
			// Get the image representation
		    ServletFileUpload upload = new ServletFileUpload();
		    FileItemIterator iter = upload.getItemIterator(request);
		    FileItemStream imageItem = iter.next();
		    InputStream imgStream = imageItem.openStream();

		    ResortImage image = new ResortImage();
		    Blob blob = new Blob(IOUtils.toByteArray(imgStream));
		    image.setImage(blob);
		    image.setResortName("Killington");
		    image.setUploadDate(new Date());

		    // Save image
		    snowReportService.saveResortImage(image);
		} catch(Exception e) {
			log.error("Error occurred loading image.", e);
		}
	}

}