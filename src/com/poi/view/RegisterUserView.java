package com.poi.view;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RegisterUserView {

	private boolean status;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}