package com.mohit.app.ws.ui.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.app.ws.service.AddressService;
import com.mohit.app.ws.service.UserService;
import com.mohit.app.ws.shared.dto.AddressDto;
import com.mohit.app.ws.shared.dto.UserDto;
import com.mohit.app.ws.ui.model.request.UserDetailsRequestModel;
import com.mohit.app.ws.ui.model.response.AddressRest;
import com.mohit.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AddressService addressService;
	
	/*
	 * Get users based on user id
	 */
	@GetMapping(path="/{userId}" , produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
	public UserRest getUser(@PathVariable String userId) {
		UserRest returnValue = new UserRest();
		UserDto userDto = userService.getUserByUserId(userId);
		BeanUtils.copyProperties(userDto, returnValue);
		
		return returnValue;
	}
	
	/*
	 * Get users and addresses based on user id
	 */
	@GetMapping(path="/{userId}/addresses" , produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
	public List<AddressRest> getAddresses(@PathVariable String userId) throws Exception {
		List<AddressRest> returnValue = new ArrayList<AddressRest>();
		List<AddressDto> addressDto = addressService.getAddresses(userId);
		//BeanUtils.copyProperties(userDto, returnValue);
		
		if(addressDto != null && !addressDto.isEmpty()) {
			Type listType = new TypeToken<List<AddressRest>>() {}.getType();
		
			ModelMapper modelMapper = new ModelMapper();
			returnValue = modelMapper.map(addressDto, listType);
		}
		
		return returnValue;
	}
	
	/*
	 * Post users
	 */
	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE} , 
					consumes = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserRest returnValue = new UserRest();
		System.out.println("Post controller called");
		
		//UserDto userDto = new UserDto();
		//BeanUtils.copyProperties(userDetails, userDto);
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		
		UserDto createdUser = userService.createUser(userDto);
		returnValue = modelMapper.map(createdUser, UserRest.class);
		
		return returnValue;
	}
	
	/*
	 * Update users based on user id
	 */
	@PutMapping(path="/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE} , 
			consumes = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
	public UserRest updateUser(@RequestBody UserDetailsRequestModel userDetails, @PathVariable String userId) {
		UserRest returnValue = new UserRest();

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);

		UserDto updatedUser = userService.updateUser(userId, userDto);
		BeanUtils.copyProperties(updatedUser, returnValue);

		return returnValue;
	}
	
	/*
	 * Delete users based on user id
	 */
	@DeleteMapping(path="/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE} , 
			consumes = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
	public void deleteUser(@PathVariable String userId) {
		
		userService.deleteUser(userId);
	}

}
