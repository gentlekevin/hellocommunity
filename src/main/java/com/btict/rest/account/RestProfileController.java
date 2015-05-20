package com.btict.rest.account;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springside.modules.web.MediaTypes;

import com.btict.entity.Community;
import com.btict.entity.User;
import com.btict.rest.RestException;
import com.btict.rest.StringToMapUtil;
import com.btict.service.CommunityService;
import com.btict.service.account.AccountService;
import com.btict.util.DateUtil;

@RestController
@RequestMapping(value="/rest/profile")
public class RestProfileController {
 
	
@Autowired
private AccountService accountService;
@Autowired
private CommunityService communityService;

@ResponseBody
@RequestMapping(value="/update",method ={RequestMethod.POST,RequestMethod.GET},produces = MediaTypes.JSON_UTF_8)
public Map UpdateProfile(@RequestParam(value="json") String json){
	Map <String,String>mapfromjson = StringToMapUtil.MapFromJSON(json);
	
	long userId = Long.parseLong(mapfromjson.get("userId"));
	User user = accountService.getUser(userId);
	user.setName(mapfromjson.get("name"));
	user.setSex(mapfromjson.get("sex"));
	user.setAddress(mapfromjson.get("address"));
	String s = mapfromjson.get("communityId");
	if(!"".equals(s)&&s!=null){
		long communityId = Long.parseLong(s);
		 Community community =communityService.findById(communityId);
		user.setCommunity(community);
		user.setProperty(community.getProperty());
	}
	
		
	
	user=accountService.updateRestUser(user);
	  Map map = new HashMap();
	    map.put("result", "0");
	    map.put("userId", mapfromjson.get("userId"));
	   map.put("name", user.getName()==null?"":user.getName());
	   map.put("sex", "0".equals(user.getSex())?"女":"男");
	   map.put("address", user.getAddress()==null?"":user.getAddress());
	   if(user.getCommunity()!=null){
			map.put("communityId", String.valueOf(user.getCommunity().getId()));
			map.put("communityName", user.getCommunity().getName());
			}else{
				map.put("communityId", "");
				map.put("communityName", "");
			}
	    return map;
	
}

@ResponseBody
@RequestMapping(value="/uploadHeadImg",method ={RequestMethod.POST,RequestMethod.GET},
                 produces = MediaTypes.JSON_UTF_8)
 public Map UploadHeadImg(@RequestParam(value="headImg") MultipartFile mult,
		@RequestParam(value="json") String json,HttpServletRequest request){
	String fileSeparator=  System.getProperty("file.separator");
	StringBuffer ctxPath = new StringBuffer();
	//ctxPath.append(request.getSession().getServletContext().getRealPath("/"));
	String uploadfilePackage =fileSeparator+"usr"+ fileSeparator+"upload"+fileSeparator+"headImg"+fileSeparator;
    ctxPath.append(uploadfilePackage);
    
    String fileName =DateUtil.getCurrentTimestamp()+mult.getOriginalFilename();
	 String uploadRealPath =ctxPath.append(fileName).toString();
	   
	File file = new File(uploadRealPath); 
	
Map <String,String>mapfromjson = StringToMapUtil.MapFromJSON(json);
	
	long userId = Long.parseLong(mapfromjson.get("userId"));
	try {
		System.out.println(mult.getInputStream());
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
    try {
		FileUtils.copyInputStreamToFile(mult.getInputStream(),file);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new RestException(HttpStatus.OK, "头像上传失败！");
	}
    User user  = accountService.getUser(userId);
    user.setHeaderImgUrl(request.getContextPath()+"/upload/headImg/"+fileName);
    accountService.updateUser(user);
    
    Map map = new HashMap();
    map.put("result", "0");
    map.put("userId", String.valueOf(userId));
    map.put("headerImgUrl", user.getHeaderImgUrl());
    return map;

}

	@RequestMapping(value = "/addCommunity", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = MediaTypes.JSON_UTF_8)
	public Map validateUserInCommunity(@RequestParam(value = "json") String json) {
		Map<String, String> mapfromjson = StringToMapUtil.MapFromJSON(json);

		Long userId = Long.parseLong(mapfromjson.get("userId"));
		User user =  accountService.getUser(userId);
		String s = mapfromjson.get("communityId");

	
			 Community community =communityService.findById(Long.parseLong(s));
			user.setCommunity(community);
			user.setProperty(community.getProperty());
		
		accountService.updateRestUser(user);
		
		Map map = new HashMap();
		map.put("result", "0");
		return map;
	}

}
