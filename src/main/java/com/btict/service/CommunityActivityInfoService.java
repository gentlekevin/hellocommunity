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

import com.btict.entity.Community;
import com.btict.entity.CommunityActivityInfo;
import com.btict.repository.CommunityActivityInfoDAO;
import com.btict.repository.CommunityDAO;

@Component
@Transactional
public class CommunityActivityInfoService {
  

  public CommunityActivityInfoDAO communityActivityInfoDAO;
  private Clock clock = Clock.DEFAULT;

  
  public List<CommunityActivityInfo> findByPropertyId(long propertyId){
	  return communityActivityInfoDAO.findByPropertyId(propertyId);
  }
  
  public List<CommunityActivityInfo>   findByInformationId(long informationId){
	  return communityActivityInfoDAO.findByInformationId(informationId);
  }
  
  public List<CommunityActivityInfo>   findByCommunityId(long communityId){
	  return communityActivityInfoDAO.findByCommunityId(communityId);
  }

  public List<CommunityActivityInfo>   findByActivityId(long activityId){
	  return communityActivityInfoDAO.findByActivityId(activityId);
  }
  
  public CommunityActivityInfo findById(long id){
	  return communityActivityInfoDAO.findOne(id);
  }
  
  public void deleteCommunityActivityInfo(long id){
	  communityActivityInfoDAO.delete(id);
  }
  
  public CommunityActivityInfo saveCommunityActivityInfo(CommunityActivityInfo communityActivityInfo){
	  
	  return communityActivityInfoDAO.save(communityActivityInfo);
  }
  
 
 public void deleteByInformtionId(long informationId){
	 
	 communityActivityInfoDAO.deleteByInformationId(informationId);
	
 }
 
 public void deleteByActivityId(long activityId){
	 
	 communityActivityInfoDAO.deleteByActivityId(activityId);
	
 }
  
  

@Autowired
public void setCommunityActivityInfoDAO(
		CommunityActivityInfoDAO communityActivityInfoDAO) {
	this.communityActivityInfoDAO = communityActivityInfoDAO;
}
public void setClock(Clock clock) {
	this.clock = clock;
}
  
}
