
package com.btict.web.property;


import java.util.List;
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

import com.btict.entity.City;
import com.btict.entity.Community;
import com.btict.entity.Property;
import com.btict.entity.User;
import com.btict.service.CityService;
import com.btict.service.CommunityService;
import com.btict.service.PropertyService;
import com.btict.service.UserService;
import com.btict.service.account.AccountService;
import com.google.common.collect.Maps;



@Controller
@RequestMapping(value = "/commonAdmin")
public class PropertyController {
	private static final String PAGE_SIZE = "10";
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动");
		
	}
	@Autowired
	private UserService userService;
	@Autowired
	private PropertyService propertyService;
	@Autowired
	private CityService cityService;
	@Autowired
	private CommunityService communityService;
	@Autowired
	private AccountService accountService;
	@RequestMapping(value="/list/propertyList",method = {RequestMethod.GET,RequestMethod.POST,})
	public String propertyList(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		Page<Property> properties = propertyService.getPropertys(searchParams, pageNumber, pageSize, sortType);
			

		model.addAttribute("properties", properties);
		model.addAttribute("sortType", sortType);
		model.addAttribute("sortTypes", sortTypes);
	
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        model.addAttribute("thisPagePath","/commonAdmin/list/propertyList");
		return "commonAdmin/propertyList";
	}
	@RequestMapping(value="/page/addPropertyForm", method ={RequestMethod.GET,RequestMethod.POST})
	public String addPropertyForm(Model model) {
	
		
		model.addAttribute("formType", "add");
		model.addAttribute("cities", cityService.findAllCity());
		
	return "commonAdmin/propertyForm";
	}
	
	
	@RequestMapping(value="/page/updatePropertyForm", method ={RequestMethod.GET,RequestMethod.POST})
	public String updatePropertyAdminForm(Model model,Long id) {
	
		Property property = propertyService.findPropertyId(id);
		model.addAttribute("property", property);
		model.addAttribute("formType", "update");
		model.addAttribute("cities", cityService.findAllCity());
		
	return "commonAdmin/propertyForm";
	}
	@RequestMapping(value="/operation/addProperty", method ={RequestMethod.GET,RequestMethod.POST})
	public String addPropertyAdmin(Property property,String cityId, Model model) {
		
	  if(!"".equals(cityId)&&cityId!=null){
		  City city = cityService.findCityById(Long.parseLong(cityId));
			property.setCity(city); 
	  }else{
		  property.setCity(null); 
	  }	  	
		propertyService.saveProperty(property);
		return "redirect:/commonAdmin/list/propertyList";
	}
	
	@RequestMapping(value="/operation/deleteProperty",method ={RequestMethod.GET,RequestMethod.POST} )
	public String deleteProperty(Long id){
		
		propertyService.deleteProperty(id);
		
		//修改引用此物业的社区
	    List<Community> communityList = communityService.findByPropertyId(id);
	    for(Community c:communityList){
	    	c.setProperty(null);
	    	communityService.saveCommunity(c);
	    }
	    //删除引用此物业的管理员
	    List<User> propertyAdminList = accountService.findByPropertyId(id);
	    for(User u:propertyAdminList){
	    	accountService.deleteUser(u.getId());
	    }
	    
	    //修改应用此物业的App用户
	    accountService.setUserProperyNull(id);
	   
		return "redirect:/commonAdmin/list/propertyList";
		
	}
	
	@RequestMapping(value="/operation/deleteProperties",method ={RequestMethod.GET,RequestMethod.POST} )
	public String deleteProperties(String propertyIds){
		
		String [] ids = propertyIds.split(",");
		for(String id:ids){
			propertyService.deleteProperty(Long.parseLong(id));
		}
		return "redirect:/commonAdmin/list/propertyList";
	}
	
	
}
