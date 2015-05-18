package com.btict.rest.comunity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.web.MediaTypes;

import com.btict.entity.Community;
import com.btict.entity.User;
import com.btict.rest.RestException;
import com.btict.rest.StringToMapUtil;
import com.btict.service.CommunityService;
import com.btict.service.account.AccountService;



@RestController
@RequestMapping(value="/rest/community")
public class RestCommunityController {
  
	 @Autowired
    private  CommunityService communityService;
	@Autowired
	private AccountService accountService;
		 	 
	 @RequestMapping( value="/findByCityId", method = {RequestMethod.POST,RequestMethod.GET},
			 produces = MediaTypes.JSON_UTF_8)
	public Map findAllCities(@RequestParam(value="json") String json){
			Map <String,String>mapfromjson = StringToMapUtil.MapFromJSON(json);
			
			Long cityId = Long.parseLong(mapfromjson.get("cityId"));
	      List<Community> communitys  = communityService.findByCityId(cityId);
		Map map = new HashMap();
		map.put("result", "0");
		List<RestCommunity> list = new ArrayList<RestCommunity>();
		for(Community community:communitys){
		list.add(new RestCommunity(String.valueOf(community.getId()), community.getName()));
		map.put("data",list);
				}
		return map;
	}
	
	 @RequestMapping( value="/validateUserInCommunity", method = {RequestMethod.POST,RequestMethod.GET},
			 produces = MediaTypes.JSON_UTF_8)
	public Map validateUserInCommunity(@RequestParam(value="json") String json){
			Map <String,String>mapfromjson = StringToMapUtil.MapFromJSON(json);
			
			Long userId = Long.parseLong(mapfromjson.get("userId"));
			Long communityId = Long.parseLong(mapfromjson.get("communityId"));
			
			User user = accountService.findByIdAndCommunityId(userId, communityId);
			Map map = new HashMap();
			if(user!=null){

				map.put("result", "0");	
			}else{
				  throw new RestException(HttpStatus.OK, RestException.errorResult("该用户不在该社区内！"));
			}
		    
		return map;
	}
	 
	 
	 public class RestCommunity{
		
		private String communityId;
		private String communityName;
		public String getCommunityId() {
			return communityId;
		}
		public void setCommunityId(String communityId) {
			this.communityId = communityId;
		}
		public String getCommunityName() {
			return communityName;
		}
		public void setCommunityName(String communityName) {
			this.communityName = communityName;
		}
		public RestCommunity(String communityId, String communityName) {
			super();
			this.communityId = communityId;
			this.communityName = communityName;
		}
		

	 }
}

