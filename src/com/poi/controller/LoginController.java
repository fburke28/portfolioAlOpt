/* File: LoginController.java
* 
* Copyright 2010, Finbarr Burke
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

package com.poi.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.po.domain.Account;
import com.poi.service.IAccountService;
import com.poi.util.TokenTransfer;
import com.poi.util.TokenUtils;
import com.poi.view.AccountView;
import com.poi.view.RegisterUserView;

/**
 * Controller class used to handle login functionality.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Dec 10, 2010   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
@Controller
@RequestMapping("/home")
public class LoginController {

	private static final Log log = LogFactory.getLog(LoginController.class);

	private IAccountService accountService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * Method used to handle welcome page display
	 * @return tile name
	 */
	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public String welcome() {
		return "welcome";
	}

	/**
	 * Method used to check username availability.
	 * @param username The username to check
	 * @return view
	 */
	@RequestMapping(value = "/checkUsername", method = RequestMethod.GET)
	@ResponseBody
	public RegisterUserView checkUsernameAvailability(String username) {
		log.info("Checking username availability " + username);

		RegisterUserView view = new RegisterUserView();
		boolean availability = accountService.checkUsernameAvailability(username);
		view.setStatus(availability);
		return view;
	}

	/**
	 * Method used to check username availability.
	 * @param username The username to check
	 * @return view
	 */
	@RequestMapping(value = "/retrieveUser", method = RequestMethod.GET)
	@ResponseBody
	public AccountView retrieveUser(String userName) {
		log.info("Retrieving user " + userName);

		AccountView view = new AccountView();
		Account account = accountService.retrieveAccount(userName);
		view.setUser(account);
		return view;
	}

	/**
	 * Method used to register a user
	 * @param account The user account details
	 * @return view
	 */
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	@ResponseBody
	public RegisterUserView registerUserDetails(@RequestBody Account account) {
		log.info("Registering user " + account.getUserName());

		RegisterUserView view = new RegisterUserView();
		String encodedPassword = passwordEncoder.encode(account.getPassword());
		account.setPassword(encodedPassword);
		// Default start user rating
		account.setUserRating(new Double(40));
		boolean success = accountService.registerUserDetails(account);
		view.setStatus(success);
		return view;
	}

	/**
	 * Retrieves the currently logged in user.
	 * 
	 * @return A transfer containing the username and the roles.
	 */
	@RequestMapping(value="/getUser", method = RequestMethod.GET)
	public @ResponseBody Account getUser(HttpServletResponse response) throws IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof String && ((String) principal).equals("anonymousUser")) {
			response.sendError( HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized" );
		}
		UserDetails userDetails = (UserDetails) principal;
		Account account = accountService.retrieveAccount(userDetails.getUsername());

		return account;
	}

	/**
	 * Authenticates a user and creates an authentication token.
	 * 
	 * @param username
	 *            The name of the user.
	 * @param password
	 *            The password of the user.
	 * @return A transfer containing the authentication token.
	 */
	@RequestMapping(value="/login", method = RequestMethod.POST)
	@ResponseBody
	public TokenTransfer login(String userName, String password) {
		log.info("Authenticating user " + userName);

		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(userName, password);
		Authentication authentication = this.authManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		/*
		 * Reload user as password of authentication principal will be null after authorization and
		 * password is needed for token generation
		 */
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);

		log.info("Authenticated user " + userDetails.getUsername());

		return new TokenTransfer(TokenUtils.createToken(userDetails));
	}

}