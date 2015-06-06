package com.btict.rest.account;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.web.MediaTypes;

import com.btict.entity.PhoneCode;
import com.btict.entity.User;
import com.btict.rest.RestException;
import com.btict.rest.StringToMapUtil;
import com.btict.service.account.AccountService;
import com.btict.service.sms.SMSService;

@RestController
@RequestMapping(value = "/rest/register")
public class RestRegisterController {
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private SMSService sMSService;
	
	
	@RequestMapping(method = {RequestMethod.POST,RequestMethod.GET},produces = MediaTypes.JSON_UTF_8)
	  public Map login(@RequestParam(value="json") String json){
				
		Map <String,String>mapfromjson = StringToMapUtil.MapFromJSON(json);
		String phone = mapfromjson.get("phone");
		String code = mapfromjson.get("smsCode");
		String result =sMSService.validteCode(phone, code);
		if(!"success".equals(result)){
			throw new RestException(HttpStatus.OK, RestException.errorResult(result));
		}
		User user = new User();
		user.setPhone(phone);
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
	
	
	@RequestMapping( value="/getCode",method ={RequestMethod.POST,RequestMethod.GET},produces = MediaTypes.JSON_UTF_8)
	public Map getCode(@RequestParam(value="json") String json){
		
		Map <String,String>mapfromjson = StringToMapUtil.MapFromJSON(json);
        String phone =mapfromjson.get("phone"); 		
  	
        User user = accountService.findUserByPhoneAndRoles(phone, "appUser");
        if(user!=null){
        	throw new RestException(HttpStatus.OK, RestException.errorResult("该手机已经注册！"));
        }
        
    	 String code =getRandomSix();
    	 String result = sMSService.sendMsg( phone, code);
    	if(result.contains("000000")){
    		PhoneCode phoneCode = new PhoneCode();
    		phoneCode.setPhone(phone);
    		phoneCode.setDateStamp(new Date());
    		phoneCode.setCode(code);
    		sMSService.saveCodeRecord(phoneCode);
    	}else if(result.contains("105122")){
    		throw new RestException(HttpStatus.OK, RestException.errorResult("你今天的验证码用完了！"));
    	}    	else{
    		throw new RestException(HttpStatus.OK, RestException.errorResult(result));
    	}
          
            Map map = new HashMap();
		    map.put("result", "0");
			return map;
	  }	 
	
	private String getRandomSix(){
		Random random = new Random();
		String result="";
		for(int i=0;i<6;i++){
		result+=random.nextInt(10);
		}
		return result;
	}
}
