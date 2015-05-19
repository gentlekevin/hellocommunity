
package com.btict.web.activity;


import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springside.modules.web.Servlets;

import com.btict.entity.Activity;
import com.btict.entity.Community;
import com.btict.entity.CommunityActivityInfo;
import com.btict.entity.User;
import com.btict.service.ActivityService;
import com.btict.service.CommunityActivityInfoService;
import com.btict.service.CommunityService;
import com.btict.service.account.AccountService;
import com.btict.service.account.ShiroDbRealm.ShiroUser;
import com.google.common.collect.Maps;



@Controller
@RequestMapping(value = "/propertyAdmin")
public class ActivityController {
	private static final String PAGE_SIZE = "10";
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动");
		
	}
	
	@Autowired
	private AccountService accountService;
     @Autowired
	private CommunityService communityService;
	@Autowired
	private ActivityService activityService;

	@Autowired
	private CommunityActivityInfoService communityActivityInfoService;
	
	@RequestMapping(value="/list/activityList",method = {RequestMethod.GET,RequestMethod.POST,})
	public String activityList(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		User user = accountService.getUser(getCurrentUserId());
	    if(user.getProperty()!=null){
	    	searchParams.put("EQ_property.id", String.valueOf(user.getProperty().getId()));
	    }
		Page<Activity> activities = activityService.getActivities(searchParams, pageNumber, pageSize, sortType);
		model.addAttribute("activities", activities);
		model.addAttribute("sortType", sortType);
		model.addAttribute("sortTypes", sortTypes);
		model.addAttribute("user", accountService.getUser(getCurrentUserId()));
	
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        model.addAttribute("thisPagePath","/propertyAdmin/list/activityList");
		return "propertyAdmin/activityList";
	}
	@RequestMapping(value="/page/addActivityForm", method ={RequestMethod.GET,RequestMethod.POST})
	public String addActivityForm(Model model) {
	    
		User account = accountService.getUser(getCurrentUserId());
		
		List<Community> communitylist  = communityService.findByPropertyId(account.getProperty().getId());
		model.addAttribute("communities",communitylist);
		model.addAttribute("formType", "add");
	return "propertyAdmin/activityForm";
	}
	
	
	@RequestMapping(value="/page/updateActivityForm", method ={RequestMethod.GET,RequestMethod.POST})
	public String updateActivityAdminForm(Model model,Long id) {
		
        User account = accountService.getUser(getCurrentUserId());
		List<Community> communitylist  = communityService.findByPropertyId(account.getProperty().getId());
		Activity activity = activityService.findActivityById(id);
		model.addAttribute("communities",communitylist);
		
		List<CommunityActivityInfo> lists = communityActivityInfoService.findByActivityId(id);
		
		StringBuffer oldCommunityId = new StringBuffer();
		for(CommunityActivityInfo c:lists){
			oldCommunityId.append(c.getCommunity().getId());
			oldCommunityId.append(",");
		}
		model.addAttribute("checkCommunities",lists);
		
		String temp = oldCommunityId.toString();
		if(temp!=null&&!"".equals(temp)){
			temp = temp.contains(",")?temp.substring(0, temp.length()-1):temp;//去掉最后一个“，”	
		}
		model.addAttribute("oldCommunityIds",temp);
		model.addAttribute("activity", activity);
		model.addAttribute("formType", "update");
	
		
	return "propertyAdmin/activityForm";
	}
	
	
	@RequestMapping(value="/operation/addActivity", method ={RequestMethod.GET,RequestMethod.POST})
	public String addActivityAdmin(Activity activity,String communityId, Model model) {
		
		User user = accountService.getUser(getCurrentUserId());
		activity.setProperty(user.getProperty());
		activityService.saveActivity(activity);
		if(communityId!=null){
			 String [] communityIds = communityId.split(",");
			    Community community = null;
			    CommunityActivityInfo activeInfo =null;
			    for(String id:communityIds){
			    	activeInfo = new CommunityActivityInfo();
			    	community = communityService.findById(Long.parseLong(id));
			    	activeInfo.setCommunity(community);
			    	activeInfo.setProperty(community.getProperty());
			    	activeInfo.setActivity(activity);
			    	activeInfo.setType("1");//0:activity,1:activity
			    	communityActivityInfoService.saveCommunityActivityInfo(activeInfo);
			    }
		}
	   	
		return "redirect:/propertyAdmin/list/activityList";
	}
	
	@RequestMapping(value="/operation/deleteActivity",method ={RequestMethod.GET,RequestMethod.POST} )
	public String deleteActivity(Long id){
		
		activityService.deleteActivity(id);
		communityActivityInfoService.deleteByActivityId(id);
		return "redirect:/propertyAdmin/list/activityList";
		
	}
	
	@RequestMapping(value="/operation/deleteActivities",method ={RequestMethod.GET,RequestMethod.POST} )
	public String deleteActivities(String activityIds){
		
		String [] ids = activityIds.split(",");
		for(String id:ids){
			activityService.deleteActivity(Long.parseLong(id));
			
			communityActivityInfoService.deleteByInformtionId(Long.parseLong(id));
			
		}
		return "redirect:/propertyAdmin/list/activityList";
	}
	
	/**
	 * 取出Shiro中的当前用户Id.
	 */
	private Long getCurrentUserId() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.id;
	}
}
