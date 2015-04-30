package com.btict.rest.account;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.web.MediaTypes;

import com.btict.entity.User;
import com.btict.service.account.AccountService;



@RestController
@RequestMapping(value="/rest/login")
public class RestLoginController {
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	public User login(@Valid User user){
		
	  return accountService.validateRestUser(user);	
				
	
	}
}
