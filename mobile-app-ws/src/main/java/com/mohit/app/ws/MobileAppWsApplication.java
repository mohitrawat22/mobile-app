package com.mohit.app.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mohit.app.ws.security.AppProperties;

/*
 * Main class to run spring boot application
 */
@SpringBootApplication(scanBasePackages= {"com.mohit.app.ws"})
public class MobileAppWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileAppWsApplication.class, args);
	}
	
	/*
	 * To encrypt the user password
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
		
	}
	
	@Bean(name="AppProperties")
	public AppProperties appProperties() {
		return new AppProperties();
		
	}
}
