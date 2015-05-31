package com.btict.repository;




import java.util.Date;
import java.util.List;

import com.btict.entity.PhoneCode;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PhoneCodeDAO extends PagingAndSortingRepository<PhoneCode, Long>,
JpaSpecificationExecutor<PhoneCode> {
	
    
	public PhoneCode findByPhoneAndCodeAndDateStampGreaterThan(String phone,String code,Date today00);
    /**
     * 获取phone今天已经获得号码
     * @param phone
     * @param today00 今天凌晨
     * @return
     */
    @Query("from PhoneCode p where p.phone=?1 and p.dateStamp>?2")
	public List<PhoneCode> totalTodayCode(String phone,Date today00);
	

}
