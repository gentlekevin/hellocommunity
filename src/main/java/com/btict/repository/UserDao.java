
package com.btict.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.btict.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> {
	
	User findByLoginName(String loginName);
	
	User findByPhone(String phone);
	
	@Query("from User u where u.id=?1")
	User findById(long id);

  
}
