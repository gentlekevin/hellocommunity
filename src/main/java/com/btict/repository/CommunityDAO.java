package com.btict.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.btict.entity.Community;

public interface CommunityDAO extends PagingAndSortingRepository<Community, Long>,
JpaSpecificationExecutor<Community>{

	public  List<Community> findByCityId(Long cityId);
	
	public  List<Community> findByPropertyId(Long propertyId);
	
	
}
