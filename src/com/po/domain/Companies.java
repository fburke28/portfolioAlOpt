package com.po.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Serialized;
import com.googlecode.objectify.annotation.Unindexed;

@SuppressWarnings("serial")
@Unindexed 
@Entity
public class Companies implements Serializable {

	@Id long id;
	@Serialized List<CompanyIdentifier> identifiers;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<CompanyIdentifier> getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(List<CompanyIdentifier> identifiers) {
		this.identifiers = identifiers;
	}

}