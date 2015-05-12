
package com.btict.web.account;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.btict.entity.User;
import com.btict.service.account.AccountService;
import com.btict.service.account.ShiroDbRealm.ShiroUser;

/**
 * 用户修改自己资料的Controller.
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@RequestMapping(value="/updatePasswordForm", method ={RequestMethod.GET,RequestMethod.POST})
	public String updatePasswordForm(Model model) {
		Long id = getCurrentUserId();
		model.addAttribute("user", accountService.getUser(id));
		return "account/updatePassword";
	}
	@RequestMapping(value="/updatePersonInfoForm", method ={RequestMethod.GET,RequestMethod.POST})
	public String updatePersonInfoForm(Model model) {
		Long id = getCurrentUserId();
		model.addAttribute("user", accountService.getUser(id));
		return "account/updatePersonInfo";
	}
	
	@RequestMapping(value="/updatePersonInfo",method ={RequestMethod.GET,RequestMethod.POST})
	public String update(@ModelAttribute("user") User user) {
		accountService.updateUser(user);
		updateCurrentUserName(user.getName());
		return "account/personInfo";
	}

	@RequestMapping(value="/updateUserPassowrd",method ={RequestMethod.GET,RequestMethod.POST})
	public String updatePassword(long id,String password,String plainPassword,Model model) {
		
		
        String msg=accountService.updateUserPassword(id, password, plainPassword);
        if(!msg.equals("")||msg!=""){
        	model.addAttribute("msg", msg);	
        	return "account/updatePassword";
        }
        else return "account/personInfo";
	}	
	
	@RequestMapping(value="/personInfo",method ={RequestMethod.GET,RequestMethod.POST} )
	public String personInfo(Model model){
		Long id = getCurrentUserId();
		model.addAttribute("user", accountService.getUser(id));
		return "account/personInfo";
		
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

	/**
	 * 更新Shiro中当前用户的用户名.
	 */
	private void updateCurrentUserName(String userName) {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		user.name = userName;
	}
	
	
}
