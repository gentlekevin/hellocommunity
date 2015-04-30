
package com.btict.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.btict.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> {
	User findByLoginName(String loginName);
	
	User findByPhone(String phone);
}
