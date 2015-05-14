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
import com.btict.entity.Property;
import com.btict.repository.PropertyDAO;

@Component
@Transactional
public class PropertyService {
 
  private PropertyDAO propertyDAO;
  	
  private Clock clock = Clock.DEFAULT;
  public List<Property> findByCityId(long cityId){
	  return propertyDAO.findByCityId(cityId);
  }
  
  public List<Property> findAllPropertis(){
	  return (List<Property>) propertyDAO.findAll();
  }
  
  public Property findPropertyId(long id){
	  return propertyDAO.findOne(id);
  }

  public Property saveProperty(Property property){
	  property.setAddDate(clock.getCurrentDate());
	  return propertyDAO.save(property);
  }
  
  public void deleteProperty(Long id){
	   propertyDAO.delete(id);
  }
  
  public Page<Property> getPropertys( Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
	
	    PageRequest pageRequest = SpecificationFindUtil.buildPageRequest(pageNumber, pageSize, sortType);
    	Specification<Property> spec = SpecificationFindUtil.buildSpecification(searchParams, Property.class);
	
	
		return propertyDAO.findAll(spec, pageRequest);
	}
  
 
  
  
@Autowired
public void setPropertyDAO(PropertyDAO propertyDAO) {
	this.propertyDAO = propertyDAO;
}

public void setClock(Clock clock) {
	this.clock = clock;
}
 
}
