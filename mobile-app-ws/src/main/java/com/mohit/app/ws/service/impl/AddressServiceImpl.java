package com.mohit.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohit.app.ws.io.entity.AddressEntity;
import com.mohit.app.ws.io.entity.UserEntity;
import com.mohit.app.ws.io.repositories.AddressRepository;
import com.mohit.app.ws.io.repositories.UserRepository;
import com.mohit.app.ws.service.AddressService;
import com.mohit.app.ws.shared.dto.AddressDto;
import com.mohit.app.ws.ui.model.response.ErrorMessages;

@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Override
	public List<AddressDto> getAddresses(String userId) throws Exception {
		
		if(userRepository.findByUserId(userId) != null)
			throw new Exception(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
		
		List<AddressDto> returnValue = new ArrayList<AddressDto>();
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		List<AddressEntity> addresses = addressRepository.findAllByUserDetails(userEntity);
		
		for(AddressEntity addressEntity : addresses) {
			returnValue.add(modelMapper.map(addressEntity, AddressDto.class));
		}
		
		return returnValue;
	}

}
