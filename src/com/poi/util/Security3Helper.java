package com.poi.util;

import java.util.Map;
import org.springframework.flex.security3.AuthenticationResultUtils;

public class Security3Helper {

    public Map<String, Object> getAuthentication() {
        return AuthenticationResultUtils.getAuthenticationResult();
    }

}