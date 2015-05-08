package com.btict.repository;

import com.btict.entity.City;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CityDAO extends PagingAndSortingRepository<City, Long> {
	

}
