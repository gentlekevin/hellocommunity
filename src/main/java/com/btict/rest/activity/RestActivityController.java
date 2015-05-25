package com.btict.rest.activity;

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
import com.btict.entity.CommunityActivityInfo;
import com.btict.entity.Information;
import com.btict.rest.StringToMapUtil;
import com.btict.service.CommunityActivityInfoService;
import com.btict.service.CommunityService;
import com.btict.service.InformationService;



@RestController
@RequestMapping(value="/rest/activity")
public class RestActivityController {
  
	 @Autowired
    private CommunityService cityService;
	@Autowired
	 private  CommunityActivityInfoService activityInfoService;
	 
	 @RequestMapping( value="/findActivityList", method = {RequestMethod.POST,RequestMethod.GET},
			 produces = MediaTypes.JSON_UTF_8)
	public Map findActivityList(@RequestParam(value="json") String json){
			Map <String,String>mapfromjson = StringToMapUtil.MapFromJSON(json);
			
			Long communityId = Long.parseLong(mapfromjson.get("communityId"));
	      List<CommunityActivityInfo> infos  = activityInfoService.findByCommunityId(communityId);
	      
		Map map = new HashMap();
		map.put("result", "0");
		List<RestInfo> list = new ArrayList<RestInfo>();
		RestInfo restInfo = null;
		for(CommunityActivityInfo activityInfo:infos){
			restInfo = new RestInfo();
			restInfo.setId(String.valueOf(activityInfo.getActivity().getId()));
			restInfo.setInfo(activityInfo.getActivity().getContent()==null?"":activityInfo.getActivity().getContent());
			restInfo.setLogoUrl(activityInfo.getActivity().getPic()==null?"":activityInfo.getActivity().getPic());
			restInfo.setTime(activityInfo.getActivity().getPublishDate().toString());
	     	list.add(restInfo);
				}
		map.put("data",list);
		return map;
	}
		 	 
	 public class RestInfo{
	
		private String id;
		private String info;
		private String logoUrl;
		private String time;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getInfo() {
			return info;
		}
		public void setInfo(String info) {
			this.info = info;
		}
		public String getLogoUrl() {
			return logoUrl;
		}
		public void setLogoUrl(String logoUrl) {
			this.logoUrl = logoUrl;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}		

	 }
}

