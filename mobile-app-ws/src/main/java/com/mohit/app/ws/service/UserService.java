package com.mohit.app.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mohit.app.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService{
	
	UserDto createUser(UserDto user) throws Exception;
	UserDto getUser(String email);
	UserDto getUserByUserId(String userId);
	UserDto updateUser(String userId, UserDto user);
	void deleteUser(String userId);

}
