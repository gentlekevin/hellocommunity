package com.btict.functional.rest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.core.io.FileSystemResource;
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
public class registerRestFT extends BaseFunctionalTestCase {

	private static String resourceUrl = baseUrl + "/rest";

	private final RestTemplate restTemplate = new RestTemplate();
	private final JsonMapper jsonMapper = new JsonMapper();

  
	@Test
	@Category(Smoke.class)
	public void UploadHeadImg() {
		String location = resourceUrl+"/register";
		System.out.println(location);
		
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();

		Map map = new HashMap<>();
		map.put("password", "test");
		map.put("phone", "13126751501");
		param.add("json", jsonMapper.toJson(map));
		String string = restTemplate.postForObject(location, param, String.class);
		System.out.println(string);
	}

	
}
