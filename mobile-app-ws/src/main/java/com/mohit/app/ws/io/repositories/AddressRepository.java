package com.mohit.app.ws.io.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mohit.app.ws.io.entity.AddressEntity;
import com.mohit.app.ws.io.entity.UserEntity;

public interface AddressRepository extends CrudRepository<AddressEntity, Long>{
	
	List<AddressEntity> findAllByUserDetails(UserEntity userEntity);

}
