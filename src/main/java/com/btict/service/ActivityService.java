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
import com.btict.entity.Activity;
import com.btict.repository.ActivityDAO;

@Component
@Transactional
public class ActivityService {
  

  public ActivityDAO activityDAO;
  private Clock clock = Clock.DEFAULT;
  
  public List<Activity> findByPropertyId(long propertyId){
	  return activityDAO.findByPropertyId(propertyId);
  }
  
 
  
  public Activity findActivityById(long id){
	  return activityDAO.findOne(id);
  }
  
  public void deleteActivity(long id){
	   activityDAO.delete(id);
  }
  
  public Activity saveActivity(Activity activity){
	  activity.setPublishDate(clock.getCurrentDate());
	  return activityDAO.save(activity);
  }
  
  
  public Page<Activity> getActivities( Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
	
	    PageRequest pageRequest = SpecificationFindUtil.buildPageRequest(pageNumber, pageSize, sortType);
  	Specification<Activity> spec = SpecificationFindUtil.buildSpecification(searchParams, Activity.class);
	
	
		return activityDAO.findAll(spec, pageRequest);
	}

  
  
  @Autowired
public void setActivityDAO(ActivityDAO activityDAO) {
	this.activityDAO = activityDAO;
}
  

public void setClock(Clock clock) {
	this.clock = clock;
}



  
}
