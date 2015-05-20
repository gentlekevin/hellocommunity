
package com.btict.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.btict.entity.User;


public interface UserDao extends PagingAndSortingRepository<User, Long>,JpaSpecificationExecutor<User> {
	
	  User findByLoginName(String loginName);
	
	  User findByPhone(String phone);
	
	  User findByPhoneAndRoles(String phone,String role);
	
	  User findByIdAndCommunityId(Long id,Long communityId);
	@Query("from User u where u.id=?1")
	public  User findById(long id);
    
	public  List<User> findByPropertyId(Long propertyId);
	@Modifying
	@Query("update User u set u.property=null where u.community.id=?1")
	public  void setUserProperyNull(Long communityId);
	@Modifying
	@Query("update User u set u.community=null where u.community.id=?1")
	public  void setUserCommunityNull(Long communityId);
}
