package com.poi.util;

import java.util.Map;

import org.springframework.flex.security.AuthenticationResultUtils;

public class SecurityHelper {

    public Map<String, Object> getAuthentication() {
        return AuthenticationResultUtils.getAuthenticationResult();
    }
}
