package com.mohit.app.ws.security;

import com.mohit.app.ws.SpringApplicationContext;

public class SecurityConstants {
	
	public static final long EXPIRATION_DATE = 60000;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/users";
	public static final String TOKEN_SECRET = "mohit";
	
	public static String getTokenSecret() {
		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
		return appProperties.getTokenSecret();
	}
	
}
