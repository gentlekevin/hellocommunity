package com.btict.repository;


import java.util.List;
import com.btict.entity.Complaint;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ComplaintDAO extends PagingAndSortingRepository<Complaint, Long>,
JpaSpecificationExecutor<Complaint> {
	
	
	

}
