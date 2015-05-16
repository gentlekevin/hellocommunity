package com.btict.repository;


import java.util.List;

import com.btict.entity.Information;
import com.btict.entity.Property;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InformationDAO extends PagingAndSortingRepository<Information, Long>,
JpaSpecificationExecutor<Information> {
	
	public  List<Information> findByPropertyId(Long prppertyId);
	
	public  List<Information> findByCommunityId(Long prppertyId);
}
