package com.btict.rest.account;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.web.MediaTypes;
import com.btict.entity.User;
import com.btict.rest.RestException;
import com.btict.rest.StringToMapUtil;
import com.btict.service.account.AccountService;

@RestController
@RequestMapping(value = "/rest/register")
public class RestRegisterController {
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(method = {RequestMethod.POST,RequestMethod.GET},produces = MediaTypes.JSON_UTF_8)
	
     public Map login(@RequestParam(value="json") String json){
		
		Map <String,String>mapfromjson = StringToMapUtil.MapFromJSON(json);
		User user = new User();
		user.setPhone(mapfromjson.get("phone"));
		user.setPassword(mapfromjson.get("password"));
	  
		
       if(accountService.restUserExist(user))
    	throw new RestException(HttpStatus.OK, RestException.errorResult("该手机号已经注册！"));
       	Map  map = new HashMap();
		user =  accountService.registerRestUser(user);
		if(user ==null)
		throw new RestException(HttpStatus.OK, RestException.errorResult("注册失败，请重注册！"));
		else {
			
			map.put("result", "0");
			map.put("userId", String.valueOf(user.getId()));
			map.put("phone", user.getPhone());
			map.put("password", user.getPassword());
			map.put("name", user.getName()==null?"":user.getName());
			map.put("sex", "0".equals(user.getSex())?"女":"男");
			map.put("headerImgUrl", user.getHeaderImgUrl()==null?"":user.getHeaderImgUrl());
		    return map;
		}
		
	}
}
