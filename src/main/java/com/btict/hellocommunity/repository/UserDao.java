
package com.btict.hellocommunity.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.btict.hellocommunity.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> {
	User findByLoginName(String loginName);
}
