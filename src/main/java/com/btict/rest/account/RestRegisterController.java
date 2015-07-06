package com.btict.rest.account;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springside.modules.mapper.JsonMapper;
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
	
	//商城注册地址
	private static String shangchengRegisterURL = "http://123.56.114.102/diguo/ectouchfenxiao/ECMobile/?url=/user/signup";
	
	private final RestTemplate restTemplate = new RestTemplate();
	private final JsonMapper jsonMapper = new JsonMapper();
	@RequestMapping(method = {RequestMethod.POST,RequestMethod.GET},produces = MediaTypes.JSON_UTF_8)
	  public Map login(@RequestParam(value="json") String json){
				
		Map <String,String>mapfromjson = StringToMapUtil.MapFromJSON(json);
		
		//String name = mapfromjson.get("name");
		//String email = mapfromjson.get("email");
		String phone = mapfromjson.get("phone");
		String code = mapfromjson.get("smsCode");
		String password  = mapfromjson.get("password");
		User user = new User();
		user.setPhone(phone);
		user.setPassword(password);
		String shangchengflag = registerShangcheng(phone,  password);
		//-------------------------商城和小区注册处理逻辑------------------------------------
		//商城          失败                                                                     失败               成功                                                                              成功
		//小区          不能注册（验证码失败/已经注册/其他）   可以注册       不能注册（验证码失败/已经注册/其他）   可以注册
		//返回结果  
		//-------------------------商城和小区注册处理逻辑------------------------------------
		if(shangchengflag!=null){
			  //商城注册失败
			if("用户名或email已使用".equals(shangchengflag)){
					return registerCommunityProxy(shangchengflag, phone, code, user);
				}else{
				//商城注册失败（不是‘用户名或email已使用’）
				throw new RestException(HttpStatus.OK, RestException.errorResult(shangchengflag));
			}
			
		}else{
			return registerCommunityProxy(null, phone, code, user);
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
	//注册商城
	  private String registerShangcheng(String phone,String password){

		  		
			MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
    String returnStr = null;
			Map map1 = new HashMap<>();
			// 传至格式为json={"field":[{"id":"5","value":"13718334687"}],"email":"xxx@xxx",
			//"password":"1234567890","name":"13718334687"}
			map1.put("id", "5");
			map1.put("value", phone);
			Map json = new HashMap<>();
			List field =  new ArrayList<>();
			field.add(map1);
			json.put("field", field);
			json.put("email", "");
			json.put("password", password);
			json.put("name", phone);
			System.out.println(jsonMapper.toJson(json));
			param.add("json", jsonMapper.toJson(json));
			String string = restTemplate.postForObject(shangchengRegisterURL, param, String.class);
		  
		  if(string.contains("session")&&string.contains("uid")){
			//注册成功
			  
		  }else{
			  Map <String,Map>map = StringToMapUtil.StringMapFromJSON(string);
				
				Map<String,String> stauts =  map.get("status");
			
				returnStr = stauts.get("error_desc");
		  }	  
		  
		  return returnStr;
	  }
	
	
	private String getRandomSix(){
		Random random = new Random();
		String result="";
		for(int i=0;i<6;i++){
		result+=random.nextInt(10);
		}
		return result;
	}
	
	private Map registerCommunityProxy(String shangchengflag,String phone,String code,User user){
		//检验小区注册验证码是否超时
		
		String result =sMSService.validteCode(phone, code);
		if(!"success".equals(result)){
			//验证码超时
		throw new RestException(HttpStatus.OK, RestException.errorResult(result));
		}else{
			//验证码没有超时）
					  		
	       if(accountService.restUserExist(user)){
	    	   //手机号已经注册(此时 用户名或email已使用，该手机号已经注册！)
	    	   throw new RestException(HttpStatus.OK, RestException.errorResult(shangchengflag+",该手机号已经注册！"));
	       }else{
	    	   //注册小区
	    		 return	registerCommunity( user);
	       }
		}
	}
	
	
	private Map registerCommunity(User user){
		Map  map = new HashMap();
		user =  accountService.registerRestUser(user);
		if(user ==null)
		throw new RestException(HttpStatus.OK, RestException.errorResult("小区注册出现异常，请联系管理员！"));
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
