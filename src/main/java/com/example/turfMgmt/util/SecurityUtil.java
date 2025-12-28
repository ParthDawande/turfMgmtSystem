package com.example.turfMgmt.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static Long getCurrentUserId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (Long) auth.getPrincipal();
    }

    public static String getCurrentUserRole(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().iterator().next().getAuthority();
    }
}
