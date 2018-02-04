package com.zooplus.sdc.converter.security;

import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsAuthenticationSuccessHandler extends CustomAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", Boolean.TRUE.toString());
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,HEAD,POST");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
    }
}
