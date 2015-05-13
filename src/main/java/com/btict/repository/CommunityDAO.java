package com.btict.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.btict.entity.Community;

public interface CommunityDAO extends PagingAndSortingRepository<Community, Long> {

	public  List<Community> findByCityId(Long cityId);
	
	public  List<Community> findByPropertyId(Long propertyId);
}
