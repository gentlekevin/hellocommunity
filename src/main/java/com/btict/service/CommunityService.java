package com.btict.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.btict.entity.Community;
import com.btict.repository.CommunityDAO;

@Component
@Transactional
public class CommunityService {
  

  public CommunityDAO communityDAO;
	
  public Community findByCityId(long cityId){
	  return communityDAO.findByCityId(cityId);
  }
  
  public Community findById(long id){
	  return communityDAO.findOne(id);
  }
  
  
  @Autowired
public void setCommunityDAO(CommunityDAO communityDAO) {
	this.communityDAO = communityDAO;
}
  
}
