package com.btict.functional.rest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.btict.functional.BaseFunctionalTestCase;

import org.springside.modules.mapper.JsonMapper;
import org.springside.modules.test.category.Smoke;

/**
 * 任务管理的功能测试, 测试页面JavaScript及主要用户故事流程.
 * 
 * @author kevin
 */
public class CommunityRestFT extends BaseFunctionalTestCase {

	private static String resourceUrl = baseUrl + "/rest/community";
	private final RestTemplate restTemplate = new RestTemplate();
	private final JsonMapper jsonMapper = new JsonMapper();
	



	@Test
	@Category(Smoke.class)
	public void findCommunityByCityId() {
		String location = resourceUrl+"/validateUserInCommunity";
		System.out.println(location);
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();

		Map map = new HashMap<>();
		map.put("communityId", "8");
		
		map.put("userId", "41");
		
		
		param.add("json", jsonMapper.toJson(map));
		String string = restTemplate.postForObject(location,param , String.class);
		System.out.println(string);
	}
		
}
