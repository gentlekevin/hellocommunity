
package com.btict.web.user;




import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.web.bind.annotation.ResponseBody;

import com.btict.entity.Property;
import com.btict.entity.User;
import com.btict.service.PropertyService;
import com.btict.service.UserService;
import com.btict.service.account.AccountService;
import com.btict.service.account.ShiroDbRealm.ShiroUser;




@Controller
@RequestMapping(value = "/admin")
public class CommonAdminUpdateController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private PropertyService propertyService;
	
	
	@RequestMapping(value="/operation/updateCommonAdmin",method ={RequestMethod.GET,RequestMethod.POST} )
	public String updateAdmin(@ModelAttribute("user") User user,Model model){
		accountService.updateUser(user);
		
		return "redirect:/admin/list/commonAdminList";
		
	}
	
	
	
	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出User对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getUser(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
		if (id != -1) {
			model.addAttribute("user", accountService.getUser(id));
		}
	}

	/**
	 * 取出Shiro中的当前用户Id.
	 */
	private Long getCurrentUserId() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.id;
	}

	
	

	
}
