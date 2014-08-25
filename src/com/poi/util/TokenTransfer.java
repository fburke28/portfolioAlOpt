package com.poi.util;

/**
 * Authentication token.
 * 
 * @author fburke
 */
public class TokenTransfer {

	private String token;

	public TokenTransfer() {
		super();
	}

	/**
	 * Constructor called by JSON creation code.
	 * @param token
	 */
	public TokenTransfer(String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}