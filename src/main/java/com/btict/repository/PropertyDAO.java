package com.btict.repository;


import java.util.List;
import com.btict.entity.Property;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PropertyDAO extends PagingAndSortingRepository<Property, Long> {
	
	public  List<Property> findByCityId(Long cityId);
}
