
package com.btict.web.information;


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

import com.btict.entity.Community;
import com.btict.entity.CommunityActivityInfo;
import com.btict.entity.Information;
import com.btict.entity.User;
import com.btict.service.CommunityActivityInfoService;
import com.btict.service.CommunityService;
import com.btict.service.InformationService;
import com.btict.service.account.AccountService;
import com.btict.service.account.ShiroDbRealm.ShiroUser;
import com.google.common.collect.Maps;



@Controller
@RequestMapping(value = "/propertyAdmin")
public class InformationController {
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
	private InformationService informationService;

	@Autowired
	private CommunityActivityInfoService communityActivityInfoService;
	
	@RequestMapping(value="/list/informationList",method = {RequestMethod.GET,RequestMethod.POST,})
	public String informationList(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		User user = accountService.getUser(getCurrentUserId());
	    if(user.getProperty()!=null){
	    	searchParams.put("EQ_property.id", String.valueOf(user.getProperty().getId()));
	    }
		Page<Information> properties = informationService.getInformations(searchParams, pageNumber, pageSize, sortType);
			

		model.addAttribute("informations", properties);
		model.addAttribute("sortType", sortType);
		model.addAttribute("sortTypes", sortTypes);
		model.addAttribute("user", accountService.getUser(getCurrentUserId()));
	
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        model.addAttribute("thisPagePath","/propertyAdmin/list/informationList");
		return "propertyAdmin/informationList";
	}
	@RequestMapping(value="/page/addInformationForm", method ={RequestMethod.GET,RequestMethod.POST})
	public String addInformationForm(Model model) {
	    
		User account = accountService.getUser(getCurrentUserId());
		
		List<Community> communitylist  = communityService.findByPropertyId(account.getProperty().getId());
		model.addAttribute("communities",communitylist);
		model.addAttribute("formType", "add");
	return "propertyAdmin/informationForm";
	}
	
	
	@RequestMapping(value="/page/updateInformationForm", method ={RequestMethod.GET,RequestMethod.POST})
	public String updateInformationAdminForm(Model model,Long id) {
		
        User account = accountService.getUser(getCurrentUserId());
		List<Community> communitylist  = communityService.findByPropertyId(account.getProperty().getId());
		Information information = informationService.findInformationById(id);
		model.addAttribute("communities",communitylist);
		
		List<CommunityActivityInfo> lists = communityActivityInfoService.findByInformationId(id);
		
		StringBuffer oldCommunityId = new StringBuffer();
		for(CommunityActivityInfo c:lists){
			oldCommunityId.append(c.getCommunity().getId());
			oldCommunityId.append(",");
		}
		String temp = oldCommunityId.toString();
		if(temp!=null&&!"".equals(temp)){
			temp = temp.contains(",")?temp.substring(0, temp.length()-1):temp;//去掉最后一个“，”	
		}
		model.addAttribute("checkCommunities",lists);
		model.addAttribute("oldCommunityIds",temp);
		
		model.addAttribute("information", information);
		model.addAttribute("formType", "update");
		
	return "propertyAdmin/informationForm";
	}
	
	
	@RequestMapping(value="/operation/addInformation", method ={RequestMethod.GET,RequestMethod.POST})
	public String addInformationAdmin(Information information,String communityId, Model model) {
		
		User user = accountService.getUser(getCurrentUserId());
		information.setProperty(user.getProperty());
		String content= information.getContent(); 
        if(content.contains("<img src=")){
        	String picUrl = content.substring(content.indexOf("/hellocommunity"),content.indexOf(" alt"));
        	information.setPic(picUrl.substring(0,picUrl.length()-1));
        }
			
		informationService.saveInformation(information);
		if(communityId!=null){
			 String [] communityIds = communityId.split(",");
			    Community community = null;
			    CommunityActivityInfo activeInfo =null;
			    for(String id:communityIds){
			    	activeInfo = new CommunityActivityInfo();
			    	community = communityService.findById(Long.parseLong(id));
			    	activeInfo.setCommunity(community);
			    	activeInfo.setProperty(community.getProperty());
			    	activeInfo.setInformation(information);
			    	activeInfo.setType("0");//0:information,1:activity
			    	communityActivityInfoService.saveCommunityActivityInfo(activeInfo);
			    }
		}
	   
		
		return "redirect:/propertyAdmin/list/informationList";
	}
	
	@RequestMapping(value="/operation/deleteInformation",method ={RequestMethod.GET,RequestMethod.POST} )
	public String deleteInformation(Long id){
		
		informationService.deleteInformation(id);
		//communityActivityInfoService.deleteByInformtionId(id);
		return "redirect:/propertyAdmin/list/informationList";
		
	}
	
	@RequestMapping(value="/operation/deleteInformations",method ={RequestMethod.GET,RequestMethod.POST} )
	public String deleteProperties(String informationIds){
		
		String [] ids = informationIds.split(",");
		for(String id:ids){
			informationService.deleteInformation(Long.parseLong(id));
			
			communityActivityInfoService.deleteByInformtionId(Long.parseLong(id));
			
		}
		return "redirect:/propertyAdmin/list/informationList";
	}
	
	/**
	 * 取出Shiro中的当前用户Id.
	 */
	private Long getCurrentUserId() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.id;
	}
}
