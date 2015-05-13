
package com.btict.web.user;


import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springside.modules.web.Servlets;

import com.btict.entity.Property;
import com.btict.entity.User;
import com.btict.service.PropertyService;
import com.btict.service.UserService;
import com.btict.service.account.AccountService;
import com.btict.service.account.ShiroDbRealm.ShiroUser;
import com.google.common.collect.Maps;



@Controller
@RequestMapping(value = "/commonAdmin")
public class PropertyAdminController {
	private static final String PAGE_SIZE = "10";
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动");
		
	}
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private PropertyService propertyService;
	
	@RequestMapping(value="/list/propertyAdminList",method = {RequestMethod.GET,RequestMethod.POST,})
	public String findPropertyAdminList(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		String roles = "propertyAdmin";
		Page<User> users = userService.getUsers(roles, searchParams, pageNumber, pageSize, sortType);

		model.addAttribute("users", users);
		model.addAttribute("sortType", sortType);
		model.addAttribute("sortTypes", sortTypes);
	
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        model.addAttribute("thisPagePath","/webuser/list/propertyAdminList");
		return "commonAdmin/propertyAdminList";
	}
	
	@RequestMapping(value="/page/addPropertyAdminForm", method ={RequestMethod.GET,RequestMethod.POST})
	public String addPropertyAdminForm(Model model) {
	
		
	model.addAttribute("roles", "propertyAdmin");
	model.addAttribute("properties", propertyService.findAllPropertis());
		return "commonAdmin/addPropertyAdminForm";
	}
	
	@RequestMapping(value="/operation/addPropertyAdmin", method ={RequestMethod.GET,RequestMethod.POST})
	public String addPropertyAdmin(@ModelAttribute("user") User user,long proId,Model model) {
		
		Property property = propertyService.findPropertyId(proId);
		user.setProperty(property);
		String msg = accountService.addUser(user);
		if(msg!=""){
			model.addAttribute("msg", msg);
			model.addAttribute("properties", propertyService.findAllPropertis());
			return "commonAdmin/addPropertyAdminForm";	
		}		
		return "redirect:/commonAdmin/list/propertyAdminList";
	}
	
	@RequestMapping(value="/operation/deletePropertyAdmin",method ={RequestMethod.GET,RequestMethod.POST} )
	public String deletePropertyAdmin(Long id){
		
		accountService.deleteUser(id);
	
		return "redirect:/commonAdmin/list/propertyAdminList";
		
	}
	
	@RequestMapping(value="/operation/deletePropertyAdmins",method ={RequestMethod.GET,RequestMethod.POST} )
	public String deletePropertyAdmins(String userIds){
		
		String [] ids = userIds.split(",");
		for(String id:ids){
			accountService.deleteUser(Long.parseLong(id));
		}
		return "redirect:/commonAdmin/list/propertyAdminList";
	}
	
	@RequestMapping(value="/page/updatePropertyAdminForm",method ={RequestMethod.GET,RequestMethod.POST} )
	public String updatePropertyAdmin(Long id,Model model){
	   
		model.addAttribute("user", accountService.getUser(id));
		model.addAttribute("properties", propertyService.findAllPropertis());
		return "commonAdmin/updatePropertyAdminForm";
		
	}
	
}
