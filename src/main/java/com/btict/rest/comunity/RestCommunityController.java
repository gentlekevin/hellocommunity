package com.btict.rest.comunity;

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
import com.btict.rest.StringToMapUtil;
import com.btict.service.CommunityService;



@RestController
@RequestMapping(value="/rest/community")
public class RestCommunityController {
  
	 @Autowired
    public CommunityService cityService;
	 
	 
	 @RequestMapping( value="/findByCityId", method = {RequestMethod.POST,RequestMethod.GET},
			 produces = MediaTypes.JSON_UTF_8)
	public Map findAllCities(@RequestParam(value="json") String json){
			Map <String,String>mapfromjson = StringToMapUtil.MapFromJSON(json);
			
			Long cityId = Long.parseLong(mapfromjson.get("cityId"));
	       Community community  = cityService.findByCityId(cityId);
		Map map = new HashMap();
		map.put("result", "0");
		List<RestCommunity> list = new ArrayList<RestCommunity>();
		list.add(new RestCommunity(String.valueOf(community.getId()), community.getName()));
		map.put("data",list);
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

