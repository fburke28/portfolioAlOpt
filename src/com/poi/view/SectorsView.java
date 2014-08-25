/* File: SectorsView.java
* 
* Copyright 2011, Finbarr Burke
* All Rights Reserved
*
* This software and all information contained herein is the property
* of Finbarr Burke.
*
*			  Restricted Rights Legend
*			  ------------------------
* Use, duplication, or disclosure by the Government is subject to
* restrictions as set forth in paragraph (b)(3)(B) of the Rights in
* Technical Data and Computer Software clause in DAR 7-104.9(a).
*/
package com.poi.view;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

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
public class SectorsView {

	private List<String> sectors;

	public List<String> getSectors() {
		return sectors;
	}

	public void setSectors(List<String> sectors) {
		this.sectors = sectors;
	}

}