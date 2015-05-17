package com.btict.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.btict.entity.CommunityActivityInfo;

public interface CommunityActivityInfoDAO extends PagingAndSortingRepository<CommunityActivityInfo, Long>,
JpaSpecificationExecutor<CommunityActivityInfo>{


	
	public  List<CommunityActivityInfo> findByPropertyId(Long propertyId);
	
	public  List<CommunityActivityInfo> findByInformationId(Long informationId);
	
	public  List<CommunityActivityInfo> findByActivityId(Long activityId);
	
	public  List<CommunityActivityInfo> findByCommunityId(Long communityId);
	
	@Modifying
	@Query("delete from  CommunityActivityInfo a where a.information.id=?1")
	public  void deleteByInformationId(Long informaionId);
	
	@Modifying
	@Query("delete from  CommunityActivityInfo a where a.activity.id=?1")
	public  void deleteByActivityId(Long activityId);
	

}
