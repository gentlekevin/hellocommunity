package com.btict.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.btict.entity.Property;
import com.btict.repository.PropertyDAO;

@Component
@Transactional
public class PropertyService {
 
  public PropertyDAO propertyDAO;
  	
  public List<Property> findByCityId(long cityId){
	  return propertyDAO.findByCityId(cityId);
  }
  
  public List<Property> findAllPropertis(){
	  return (List<Property>) propertyDAO.findAll();
  }
  
  public Property findPropertyId(long id){
	  return propertyDAO.findOne(id);
  }

@Autowired
public void setPropertyDAO(PropertyDAO propertyDAO) {
	this.propertyDAO = propertyDAO;
}
 
}
