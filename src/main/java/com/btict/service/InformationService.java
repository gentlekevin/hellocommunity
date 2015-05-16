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

import com.btict.entity.Information;
import com.btict.entity.Information;
import com.btict.repository.InformationDAO;
import com.btict.repository.InformationDAO;

@Component
@Transactional
public class InformationService {
  

  public InformationDAO informationDAO;
  private Clock clock = Clock.DEFAULT;
  
  public List<Information> findByPropertyId(long propertyId){
	  return informationDAO.findByPropertyId(propertyId);
  }
  
 
  
  public Information findInformationById(long id){
	  return informationDAO.findOne(id);
  }
  
  public void deleteInformation(long id){
	   informationDAO.delete(id);
  }
  
  public Information saveInformation(Information information){
	  information.setPublishDate(clock.getCurrentDate());
	  return informationDAO.save(information);
  }
  
  
  public Page<Information> getInformations( Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
	
	    PageRequest pageRequest = SpecificationFindUtil.buildPageRequest(pageNumber, pageSize, sortType);
  	Specification<Information> spec = SpecificationFindUtil.buildSpecification(searchParams, Information.class);
	
	
		return informationDAO.findAll(spec, pageRequest);
	}

  
  
  @Autowired
public void setInformationDAO(InformationDAO informationDAO) {
	this.informationDAO = informationDAO;
}
  

public void setClock(Clock clock) {
	this.clock = clock;
}



  
}
