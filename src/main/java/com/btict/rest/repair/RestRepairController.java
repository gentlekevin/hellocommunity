package com.btict.rest.repair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.web.MediaTypes;

import com.btict.entity.Community;
import com.btict.entity.Repair;
import com.btict.entity.User;
import com.btict.rest.StringToMapUtil;
import com.btict.service.CommunityService;
import com.btict.service.RepairService;
import com.btict.service.account.AccountService;



@RestController
@RequestMapping(value="/rest/repair")
public class RestRepairController {
  
	 @Autowired
    private RepairService repairService;
	 @Autowired
	 private CommunityService communityService;
	 @Autowired
	 private AccountService accountService;
	
	 @RequestMapping( value="/findByRepairId", method = {RequestMethod.POST,RequestMethod.GET},
			 produces = MediaTypes.JSON_UTF_8)
	public Map findAllCities(@RequestParam(value="json") String json){
		
		Map <String,String>mapfromjson = StringToMapUtil.MapFromJSON(json);
		Long repairId = Long.parseLong(mapfromjson.get("repairId"));
		Repair repair = repairService.findRepairById(repairId);
	    Map map = new HashMap();
		map.put("result", "0");
		map.put("repairId", repair.getId());
		map.put("address", repair.getAddress());
		map.put("status", repair.getStatus());
		map.put("info", repair.getContent());
	    map.put("tel", repair.getUserPhone());
		map.put("title", repair.getTitle());
	
		return map;
	}
	
	 @RequestMapping( value="/findRepairList", method = {RequestMethod.POST,RequestMethod.GET},
			 produces = MediaTypes.JSON_UTF_8)
	public Map findRepairList(@RequestParam(value="json") String json){
			Map <String,String>mapfromjson = StringToMapUtil.MapFromJSON(json);
			
			Long communityId = Long.parseLong(mapfromjson.get("communityId"));
			Long userId = Long.parseLong(mapfromjson.get("userId"));
	   List<Repair> list = repairService.findRepairByUserId(userId);
	   
	   List<RestRepair> data = new ArrayList<RestRepair>();
	   RestRepair restRepair = null;
	   for(Repair repair:list){
		   restRepair = new RestRepair();
		   restRepair.setAddress(repair.getAddress()==null?"":repair.getAddress());
		   restRepair.setInfo(repair.getContent()==null?"":repair.getContent());
		   restRepair.setRepairId(String.valueOf(repair.getId()));
		   restRepair.setStatus(repair.getStatus()==null?"":repair.getStatus());
		   restRepair.setTel(repair.getUserPhone()==null?"":repair.getUserPhone());
		   restRepair.setTitle(repair.getTitle()==null?"":repair.getTitle());
		   data.add(restRepair);
	   }
	    
		Map map = new HashMap();
		map.put("result", "0");
		map.put("data", data);
		return map;
	}
	
	 @RequestMapping( value="/saveRepair", method = {RequestMethod.POST,RequestMethod.GET},
			 produces = MediaTypes.JSON_UTF_8)
	public Map saveRepair(@RequestParam(value="json") String json){
			Map <String,String>mapfromjson = StringToMapUtil.MapFromJSON(json);
			
			Long communityId = Long.parseLong(mapfromjson.get("communityId"));
			String address = mapfromjson.get("address");
			String info = mapfromjson.get("info");
			String title = mapfromjson.get("title");
			String tel = mapfromjson.get("tel");
			Long userId =  Long.parseLong(mapfromjson.get("userId"));
   
			User user = accountService.getUser(userId);
			Community community = communityService.findById(communityId);
			Repair repair = null;
			
			if(!"".equals(mapfromjson.get("repairId"))&&mapfromjson.get("repairId")!=null){
				Long repairId = Long.parseLong(mapfromjson.get("repairId"));
				repair = repairService.findRepairById(repairId);
				
			}else{
				repair = new Repair();
			}
			repair.setUser(user);
			repair.setCommunity(community);
			repair.setContent(info);
            repair.setStatus("0");
            repair.setTitle(title); 
            repair.setUserPhone(tel);
            repair.setAddress(address);
            repairService.saveRepair(repair);
            Map map = new HashMap();
    		map.put("result", "0");
    		map.put("repairId", repair.getId());
    		map.put("address", repair.getAddress()==null?"":repair.getAddress());
    		map.put("status", repair.getStatus()==null?"":repair.getStatus());
    		map.put("info", repair.getContent()==null?"":repair.getContent());
    	    map.put("tel", repair.getUserPhone()==null?"":repair.getUserPhone());
    		map.put("title", repair.getTitle()==null?"":repair.getTitle());
    		return map;
	 }
	   
	 public class RestRepair{
		private String repairId;
		private String address;
		private String info;
		private String title;
		private String tel;
		private String status;
		public String getRepairId() {
			return repairId;
		}
		public void setRepairId(String repairId) {
			this.repairId = repairId;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getInfo() {
			return info;
		}
		public void setInfo(String info) {
			this.info = info;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		

	 }
}

