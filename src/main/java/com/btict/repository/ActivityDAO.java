package com.btict.repository;


import java.util.List;

import com.btict.entity.Activity;
import com.btict.entity.Information;
import com.btict.entity.Property;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ActivityDAO extends PagingAndSortingRepository<Activity, Long>,
JpaSpecificationExecutor<Activity> {
	
	public  List<Activity> findByPropertyId(Long prppertyId);
	
	public  List<Activity> findByCommunityId(Long prppertyId);
}
