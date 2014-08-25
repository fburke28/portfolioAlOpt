package com.poi.view;

import javax.xml.bind.annotation.XmlRootElement;

import com.po.domain.Account;

/**
 * View class used to return data.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>May 11, 2011   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
@XmlRootElement
public class AccountView {

	private Account user;

	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}

}