package com.btict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.btict.entity.City;
import com.btict.repository.CityDAO;

@Component
@Transactional
public class CityService {
  

  public CityDAO cityDAO;
	
  public List<City> findAllCity(){
	 
  return (List<City>) cityDAO.findAll();
  
  
 }
  public City findCityById(long id){
	  return cityDAO.findOne(id);
  }
  

public CityDAO getCityDAO() {
	return cityDAO;
}
@Autowired
public void setCityDAO(CityDAO cityDAO) {
	this.cityDAO = cityDAO;
}
  
  
}
