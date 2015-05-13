
package com.btict.web.account;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，
 * 
 * 真正登录的POST请求由Filter完成,
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController {

	@RequestMapping(method ={ RequestMethod.GET,RequestMethod.POST})
	public String index() {
		return "/index";
	}

	

}
