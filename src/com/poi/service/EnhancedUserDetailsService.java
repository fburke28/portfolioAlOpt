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
package com.poi.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.po.domain.Account;

/**
 * The enhanced user details service interface.
 */
public interface EnhancedUserDetailsService extends UserDetailsService {

    /**
     * Returns the user account for the given username.
     *
     * @param username the username
     * @return the user account
     */
    Account getUser(final String username);

}