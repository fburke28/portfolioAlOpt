package com.po.domain.ski;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.Blob;
import com.googlecode.objectify.annotation.Entity;

/**
 * Domain class used to model a ski resort.
 * 
 * @author fburke
 */
@SuppressWarnings("serial")
@Repository
@Entity
public class ResortImage implements Serializable {

	@Id private String resortName;
	private Blob image;
	private Date uploadDate;

	public String getResortName() {
		return resortName;
	}

	public void setResortName(String resortName) {
		this.resortName = resortName;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

}