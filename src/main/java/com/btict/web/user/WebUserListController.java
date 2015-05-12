
package com.btict.web.user;


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

import com.btict.entity.User;
import com.btict.service.UserService;
import com.btict.service.account.ShiroDbRealm.ShiroUser;
import com.google.common.collect.Maps;



@Controller
@RequestMapping(value = "/webuser/list")
public class WebUserListController {
	private static final String PAGE_SIZE = "10";
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动");
		
	}
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/commonAdminList",method = {RequestMethod.GET,RequestMethod.POST})
		
		public String findAll(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
				@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
				@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
				ServletRequest request) {
			Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
			Long userId = getCurrentUserId();
            String roles = "commonAdmin";
			
			Page<User> users = userService.getUsers(roles, searchParams, pageNumber, pageSize, sortType);

			model.addAttribute("users", users);
			model.addAttribute("sortType", sortType);
			model.addAttribute("sortTypes", sortTypes);
		
			// 将搜索条件编码成字符串，用于排序，分页的URL
			model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
            model.addAttribute("thisPagePath","/webuser/list/commonAdminList");
			return "user/commonAdminList";
		
	}
	@RequestMapping(value="/propertyadmin",method = {RequestMethod.GET,RequestMethod.POST,})
	public String findAllPropertyadmin() {
		return "/user/user_test2";
	}

	

	
	/**
	 * 取出Shiro中的当前用户Id.
	 */
	private Long getCurrentUserId() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.id;
	}
	
}
