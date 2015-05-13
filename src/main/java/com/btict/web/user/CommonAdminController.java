
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

import com.btict.entity.User;
import com.btict.service.UserService;
import com.btict.service.account.AccountService;
import com.btict.service.account.ShiroDbRealm.ShiroUser;
import com.google.common.collect.Maps;



@Controller
@RequestMapping(value = "/admin")
public class CommonAdminController {
	private static final String PAGE_SIZE = "10";
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动");
		
	}
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	@RequestMapping(value="/list/commonAdminList",method = {RequestMethod.GET,RequestMethod.POST})
		
		public String commonAdminList(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
				@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
				@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
				ServletRequest request) {
			Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
			String roles = "commonAdmin";
			Page<User> users = userService.getUsers(roles, searchParams, pageNumber, pageSize, sortType);

			model.addAttribute("users", users);
			model.addAttribute("sortType", sortType);
			model.addAttribute("sortTypes", sortTypes);
		
			// 将搜索条件编码成字符串，用于排序，分页的URL
			model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
            model.addAttribute("thisPagePath","/webuser/list/commonAdminList");
			return "admin/commonAdminList";
		
	}
	
	
	@RequestMapping(value="/page/addCommonAdminForm", method ={RequestMethod.GET,RequestMethod.POST})
	public String addCommonAdminForm(Model model) {
		
	model.addAttribute("roles", "commonAdmin,propertyAdmin");
		
		return "admin/addCommonAdminForm";
	}
	
	@RequestMapping(value="/operation/addCommonAdmin", method ={RequestMethod.GET,RequestMethod.POST})
	public String addCommonAdmin(@ModelAttribute("user") User user,Model model) {
		
		
		String msg = accountService.addUser(user);
		if(msg!=""){
			model.addAttribute("msg", msg);
			return "admin/addAdminForm";	
		}		
		return "redirect:/admin/list/commonAdminList";
	}
	
	@RequestMapping(value="/operation/deleteCommonAdmin",method ={RequestMethod.GET,RequestMethod.POST} )
	public String deleteCommonAdmin(Long id){
		
		accountService.deleteUser(id);
	
		return "redirect:/admin/list/commonAdminList";
		
	}
	@RequestMapping(value="/operation/deleteCommonAdmins",method ={RequestMethod.GET,RequestMethod.POST} )
	public String deleteCommonAdmins(String userIds){
		
		String [] ids = userIds.split(",");
		for(String id:ids){
			accountService.deleteUser(Long.parseLong(id));
		}
		return "redirect:/admin/list/commonAdminList";
		
	}
	
	@RequestMapping(value="/page/updateCommonAdminForm",method ={RequestMethod.GET,RequestMethod.POST} )
	public String updateCommonAdmin(Long id,Model model){
	    
		
		model.addAttribute("user", accountService.getUser(id));
		return "admin/updateAdminForm";
		
	}
	
	
	
	
	/**
	 * 取出Shiro中的当前用户Id.
	 */
	private Long getCurrentUserId() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.id;
	}
	
	private User getCurrentUser(){
		
		return accountService.getUser(getCurrentUserId());
	}
	
}
