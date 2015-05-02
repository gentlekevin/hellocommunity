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
@RequestMapping(value="/rest/login")
public class RestLoginController {
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(method ={RequestMethod.POST,RequestMethod.GET},produces = MediaTypes.JSON_UTF_8)
	public Map login(@RequestParam(value="json") String json){
		
		Map <String,String>mapfromjson = StringToMapUtil.MapFromJSON(json);
		User user = new User();
		user.setPhone(mapfromjson.get("phone"));
		user.setPassword(mapfromjson.get("password"));
	    user = accountService.validateRestUser(user);	
	  if(user==null){
		  throw new RestException(HttpStatus.FORBIDDEN, RestException.errorResult("用户名或密码错误！"));
	  }else{
		  Map map = new HashMap();
		    map.put("result", "0");
			map.put("userId", user.getId());
			map.put("name", user.getName()==null?"":user.getName());
			map.put("sex", "0".equals(user.getSex())?"女":"男");
			map.put("headerImgUrl", user.getHeaderImgUrl()==null?"":user.getHeaderImgUrl());
		  return map;
	  }
	
	 }
}
