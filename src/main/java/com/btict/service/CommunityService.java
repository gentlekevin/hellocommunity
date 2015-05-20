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
import com.btict.repository.CommunityDAO;
import com.btict.repository.UserDao;

@Component
@Transactional
public class CommunityService {
  

  private CommunityDAO communityDAO;
  
 
  
  private Clock clock = Clock.DEFAULT;
  
  public List<Community> findByCityId(long cityId){
	  return communityDAO.findByCityId(cityId);
  }
  public List<Community> findByPropertyId(long propertyId){
	  return communityDAO.findByPropertyId(propertyId);
  }
  public Community findById(long id){
	  return communityDAO.findOne(id);
  }
  
  public void deleteCommunity(long id){
	   
     
      communityDAO.delete(id);
      
  }
  public void deleteCommunityEntity(Community community){
	  communityDAO.delete(community);
  }
  
  public Community saveCommunity(Community community){
	  community.setAddDate(clock.getCurrentDate());
	  return communityDAO.save(community);
  }
  
  
    
  public Page<Community> getCommunities( Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
	
	    PageRequest pageRequest = SpecificationFindUtil.buildPageRequest(pageNumber, pageSize, sortType);
  	    Specification<Community> spec = SpecificationFindUtil.buildSpecification(searchParams, Community.class);
		return communityDAO.findAll(spec, pageRequest);
	}
  
   @Autowired
  public void setCommunityDAO(CommunityDAO communityDAO) {
	this.communityDAO = communityDAO;
  }

   public void setClock(Clock clock) {
	this.clock = clock;
  }

  
}
