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
import com.btict.entity.Repair;
import com.btict.repository.RepairDAO;

@Component
@Transactional
public class RepairService {
  

  public RepairDAO repairDAO;
  private Clock clock = Clock.DEFAULT;
  
 
  public Repair findRepairById(long id){
	  return repairDAO.findOne(id);
  }
  
  public List<Repair> findRepairByUserId(long id){
	  return repairDAO.findByUserId(id);
  }
  
  public void deleteRepair(long id){
	   repairDAO.delete(id);
  }
  
  public Repair saveRepair(Repair repair){
	  repair.setRepairDate(clock.getCurrentDate());
	  return repairDAO.save(repair);
  }
  
  
  public Page<Repair> getRepairs( Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
	
	    PageRequest pageRequest = SpecificationFindUtil.buildPageRequest(pageNumber, pageSize, sortType);
  	Specification<Repair> spec = SpecificationFindUtil.buildSpecification(searchParams, Repair.class);
	
	
		return repairDAO.findAll(spec, pageRequest);
	}

  
  
  @Autowired
public void setRepairDAO(RepairDAO repairDAO) {
	this.repairDAO = repairDAO;
}
  

public void setClock(Clock clock) {
	this.clock = clock;
}



  
}
