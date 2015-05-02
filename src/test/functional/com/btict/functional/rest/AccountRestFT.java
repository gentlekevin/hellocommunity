/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.btict.functional.rest;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.btict.data.TaskData;
import com.btict.entity.Task;
import com.btict.functional.BaseFunctionalTestCase;

import org.springside.modules.mapper.JsonMapper;
import org.springside.modules.test.category.Smoke;

/**
 * 任务管理的功能测试, 测试页面JavaScript及主要用户故事流程.
 * 
 * @author calvin
 */
public class AccountRestFT extends BaseFunctionalTestCase {

	private static String resourceUrl = baseUrl + "/rest/profile";

	private final RestTemplate restTemplate = new RestTemplate();
	private final JsonMapper jsonMapper = new JsonMapper();

	
	/**
	 * 上传头像
	 *
	@Test
	@Category(Smoke.class)
	public void UploadHeadImg() {
		String location = resourceUrl+"/uploadHeadImg";
		System.out.println(location);
		String filePath = "D:\\psb.jpg";
		FileSystemResource resource = new FileSystemResource(new File(filePath));
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
		param.add("headImg", resource);
		param.add("userId", "2");
		String string = restTemplate.postForObject(location, param, String.class);
		System.out.println(string);
	}
	*/
	/**
	 * 上传头像
	 */
	@Test
	@Category(Smoke.class)
	public void UpdateProfile() {
		String location = resourceUrl+"/update";
		
	
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
	
		System.out.println(location);
		Map map = new HashMap<>();
		map.put("userId", "3");
		map.put("name", "2");
		map.put("sex", "1");
		map.put("address", "sdsdsdd");
		param.add("json", jsonMapper.toJson(map));
				String string = restTemplate.postForObject(location, param, String.class);
		System.out.println(string);
	}
	
}
