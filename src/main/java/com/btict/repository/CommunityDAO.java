package com.btict.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.btict.entity.City;
import com.btict.entity.Community;

public interface CommunityDAO extends PagingAndSortingRepository<Community, Long> {

	public  Community findByCityId(Long cityId);
}
