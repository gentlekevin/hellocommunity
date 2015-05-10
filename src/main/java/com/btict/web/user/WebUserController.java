/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.btict.web.user;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping(value = "/webuser")
public class WebUserController {

	@RequestMapping(value="/sysadmin",method = {RequestMethod.GET,RequestMethod.POST,})
	public String findAll() {
		return "/user/user_test1";
	}
	@RequestMapping(value="/propertyadmin",method = {RequestMethod.GET,RequestMethod.POST,})
	public String findAllPropertyadmin() {
		return "/user/user_test2";
	}

	

}
