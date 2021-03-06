package com.btict.repository;


import java.util.List;
import com.btict.entity.Information;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InformationDAO extends PagingAndSortingRepository<Information, Long>,
JpaSpecificationExecutor<Information> {
	
	public  List<Information> findByPropertyId(Long prppertyId);
	

}
