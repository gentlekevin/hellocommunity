package com.btict.rest.account;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.web.MediaTypes;
import com.btict.entity.User;
import com.btict.rest.RestException;
import com.btict.service.account.AccountService;

@RestController
@RequestMapping(value = "/rest/register")
public class RestRegisterController {
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(method = RequestMethod.POST,produces = MediaTypes.JSON_UTF_8)
	
	public Map register(User user)  {
		
       if(accountService.restUserExist(user))
    	throw new RestException(HttpStatus.FORBIDDEN, RestException.errorResult("该手机号已经注册！"));
       	Map  map = new HashMap();
		user =  accountService.registerRestUser(user);
		if(user ==null)
		throw new RestException(HttpStatus.FORBIDDEN, RestException.errorResult("注册失败，请重注册！"));
		else {
			map.put("result", "0");
		    return map;
		}
		
	}
}
