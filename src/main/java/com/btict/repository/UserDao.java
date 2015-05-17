
package com.btict.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.btict.entity.User;


public interface UserDao extends PagingAndSortingRepository<User, Long>,JpaSpecificationExecutor<User> {
	
	User findByLoginName(String loginName);
	
	User findByPhone(String phone);
	
	User findByPhoneAndRoles(String phone,String role);
	
	@Query("from User u where u.id=?1")
	User findById(long id);

  
}
