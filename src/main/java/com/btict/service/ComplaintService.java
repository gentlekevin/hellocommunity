package com.btict.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.Clock;
import com.btict.entity.Complaint;
import com.btict.repository.ComplaintDAO;

@Component
@Transactional
public class ComplaintService {
  

  public ComplaintDAO complaintDAO;
  private Clock clock = Clock.DEFAULT;
  
 
  
 
  
  public Complaint findComplaintById(long id){
	  return complaintDAO.findOne(id);
  }
  
  public void deleteComplaint(long id){
	   complaintDAO.delete(id);
  }
  
  public Complaint saveComplaint(Complaint complaint){
	  complaint.setComplaintDate(clock.getCurrentDate());
	  return complaintDAO.save(complaint);
  }
  
  
  public Page<Complaint> getComplaints( Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
	
	    PageRequest pageRequest = SpecificationFindUtil.buildPageRequest(pageNumber, pageSize, sortType);
  	Specification<Complaint> spec = SpecificationFindUtil.buildSpecification(searchParams, Complaint.class);
	
	
		return complaintDAO.findAll(spec, pageRequest);
	}

  
  
  @Autowired
public void setComplaintDAO(ComplaintDAO complaintDAO) {
	this.complaintDAO = complaintDAO;
}
  

public void setClock(Clock clock) {
	this.clock = clock;
}



  
}
