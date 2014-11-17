/*
 *  Copyright (C) 2010 Taylor Leese (tleese22@gmail.com)
 *
 *  This file is part of jappstart.
 *
 *  jappstart is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  jappstart is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with jappstart.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.po.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.po.domain.Account;
import com.poi.domain.EnhancedUser;

/**
 * The user details service implementation.
 */
@Service
public class UserDetailsServiceImpl implements EnhancedUserDetailsService {

	/**
     * The default cache expiration in seconds.
     */
    private static final int DEFAULT_EXPIRATION = 3600;

	private ObjectifyFactory objectifyFactory;

	/**
     * The memcache service.
     */
    private MemcacheService memcacheService;

	/**
	 * @param objectifyFactory
	 */
	public void setObjectifyFactory(ObjectifyFactory objectifyFactory) {
		this.objectifyFactory = objectifyFactory;
	}

	/**
     * Returns the memcache service.
     *
     * @return the memcache service
     */
    public final MemcacheService getMemcacheService() {
        return memcacheService;
    }

    /**
     * Sets the memcache service.
     *
     * @param memcacheService the memcache service
     */
    public final void setMemcacheService(
        final MemcacheService memcacheService) {
        this.memcacheService = memcacheService;
    }

	/**
     * Locates the user based on the username.
     *
     * @param username string the username
     * @return the user details
     */
    @Override
    public final UserDetails loadUserByUsername(final String username) {
    	Objectify ofy = objectifyFactory.begin();
    	final List<GrantedAuthority> authorities =
            new ArrayList<GrantedAuthority>();
    	Account user = (Account) memcacheService.get(username);

        if (user == null) {
            try {
                user = ofy.get(Account.class, username);
                memcacheService.put(username, user,
                    Expiration.byDeltaSeconds(DEFAULT_EXPIRATION));
            } catch (NotFoundException e) {
                throw new UsernameNotFoundException("Username not found.", e);
            }
        }

        authorities.add(new GrantedAuthorityImpl("ROLE_USER"));
        return new EnhancedUser(user.getUserName(), user.getEmail(),
            user.getFirstName() + " " + user.getLastName(), 
            user.getPassword(), "", true, true, true, true, authorities);
    }

    /**
     * Returns the user account for the given username.
     *
     * @param username the username
     * @return the user account
     */
    @Override
    public final Account getUser(final String username) {
    	Objectify ofy = objectifyFactory.begin();
        Account user = (Account) memcacheService.get(username);

        if (user == null) {
            try {
            	user = ofy.get(Account.class, username);
                memcacheService.put(username, user,
                    Expiration.byDeltaSeconds(DEFAULT_EXPIRATION));
            } catch (NotFoundException e) {
                return null;
            }
        }
        return user;
    }

}