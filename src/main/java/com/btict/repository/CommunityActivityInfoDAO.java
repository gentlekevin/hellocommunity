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
	
	@Query(" from  CommunityActivityInfo a where a.community.id=?1 and a.type=?2")
	public  List<CommunityActivityInfo> findInfoByCommunityId(Long communityId,String type);
	
	@Query("from  CommunityActivityInfo a where a.community.id=?1 and a.type=?2")
	public  List<CommunityActivityInfo> findActivityByCommunityId(Long communityId,String type);
	
	@Modifying
	@Query("delete from  CommunityActivityInfo a where a.information.id=?1")
	public  void deleteByInformationId(Long informaionId);
	
	@Modifying
	@Query("delete from  CommunityActivityInfo a where a.activity.id=?1")
	public  void deleteByActivityId(Long activityId);
	

}
