package com.btict.hellocommunity.rest.account;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.MediaTypes;

import com.btict.hellocommunity.entity.User;
import com.btict.hellocommunity.service.account.AccountService;

@RestController
@RequestMapping(value = "/rest/register")
public class RestRegisterController {
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(method = RequestMethod.POST,produces = MediaTypes.JSON_UTF_8)
	
	public User register(@Valid User user) {
	
		return accountService.registerRestUser(user);

		
	
	}
}
