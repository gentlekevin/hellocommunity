package com.btict.repository;


import java.util.List;

import com.btict.entity.Property;
import com.btict.entity.User;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PropertyDAO extends PagingAndSortingRepository<Property, Long>,
JpaSpecificationExecutor<Property> {
	
	public  List<Property> findByCityId(Long cityId);
}
