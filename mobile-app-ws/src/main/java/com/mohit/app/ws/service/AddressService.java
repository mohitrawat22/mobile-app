package com.mohit.app.ws.service;

import java.util.List;

import com.mohit.app.ws.shared.dto.AddressDto;

public interface AddressService {
	
	List<AddressDto> getAddresses(String userId) throws Exception;

}
