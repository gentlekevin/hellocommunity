
package com.btict.web.community;


import java.util.Map;
import javax.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springside.modules.web.Servlets;
import com.btict.entity.Community;
import com.btict.entity.Property;
import com.btict.service.CityService;
import com.btict.service.CommunityService;
import com.btict.service.PropertyService;
import com.google.common.collect.Maps;



@Controller
@RequestMapping(value = "/propertyAdmin")
public class CommunityController {
	private static final String PAGE_SIZE = "10";
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动");
		
	}
	
	@Autowired
	private PropertyService propertyService;
	@Autowired
	private CommunityService communityService;
	@Autowired
	private CityService cityService;
	
	@RequestMapping(value="/list/communityList",method = {RequestMethod.GET,RequestMethod.POST,})
	public String communityList(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		Page<Community> communities = communityService.getCommunities(searchParams, pageNumber, pageSize, sortType);
			

		model.addAttribute("communities", communities);
		model.addAttribute("sortType", sortType);
		model.addAttribute("sortTypes", sortTypes);
	
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        model.addAttribute("thisPagePath","/propertyAdmin/list/communityList");
		return "propertyAdmin/communityList";
	}
	@RequestMapping(value="/page/addCommunityForm", method ={RequestMethod.GET,RequestMethod.POST})
	public String addCommunityForm(Model model) {
	
		
		model.addAttribute("formType", "add");
		model.addAttribute("properties", propertyService.findAllPropertis());
		
	return "propertyAdmin/communityForm";
	}
	
	
	@RequestMapping(value="/page/updateCommunityForm", method ={RequestMethod.GET,RequestMethod.POST})
	public String updateCommunityForm(Model model,Long id) {
	
		
		Community community = communityService.findById(id);
		model.addAttribute("community", community);
		model.addAttribute("formType", "update");
		model.addAttribute("properties", propertyService.findAllPropertis());
		
	return "propertyAdmin/communityForm";
	}
	@RequestMapping(value="/operation/addCommunity", method ={RequestMethod.GET,RequestMethod.POST})
	public String addCommunity(Community community,Long propertyId, Model model) {
		
	  if(propertyId!=0&&propertyId!=null){
		  Property property = propertyService.findPropertyId(propertyId);
		  community.setProperty(property);
			
	  }else{
		  community.setProperty(null); 
	  }	  	
	  communityService.saveCommunity(community);
		
		return "redirect:/propertyAdmin/list/communityList";
	}
	
	@RequestMapping(value="/operation/deleteCommunity",method ={RequestMethod.GET,RequestMethod.POST} )
	public String deleteCommunity(Long id){
		
		communityService.deleteCommunity(id);
	
		return "redirect:/propertyAdmin/list/communityList";
		
	}
	
	@RequestMapping(value="/operation/deleteCommunities",method ={RequestMethod.GET,RequestMethod.POST} )
	public String deleteCommunities(String communityIds){
		
		String [] ids = communityIds.split(",");
		for(String id:ids){
			communityService.deleteCommunity(Long.parseLong(id));
		}
		return "redirect:/propertyAdmin/list/communityList";
	}
	
	
}
