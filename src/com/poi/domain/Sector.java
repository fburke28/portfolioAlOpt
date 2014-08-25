package com.poi.domain;

import java.io.Serializable;

import javax.persistence.Id;

import org.springframework.stereotype.Repository;

import com.googlecode.objectify.annotation.Entity;

/**
 * Domain class used to represent Company data.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Oct 30, 2010   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
@SuppressWarnings("serial")
@Repository
@Entity
public class Sector implements Serializable {

	@Id String sector;

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

}