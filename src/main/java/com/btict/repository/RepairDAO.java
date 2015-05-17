package com.btict.repository;


import java.util.List;

import com.btict.entity.Information;
import com.btict.entity.Repair;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RepairDAO extends PagingAndSortingRepository<Repair, Long>,
JpaSpecificationExecutor<Repair> {
	
	
	public  List<Repair> findByUserId(Long userId);

	

}
