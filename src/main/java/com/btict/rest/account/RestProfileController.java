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

import com.btict.entity.User;
import com.btict.rest.RestException;
import com.btict.rest.StringToMapUtil;
import com.btict.service.account.AccountService;
import com.btict.util.DateUtil;

@RestController
@RequestMapping(value="/rest/profile")
public class RestProfileController {
 
	
@Autowired
private AccountService accountService;
@ResponseBody
@RequestMapping(value="/update",method ={RequestMethod.POST,RequestMethod.GET},produces = MediaTypes.JSON_UTF_8)
public Map UpdateProfile(@RequestParam(value="json") String json){
	Map <String,String>mapfromjson = StringToMapUtil.MapFromJSON(json);
	
	long userId = Long.parseLong(mapfromjson.get("userId"));
	User user = accountService.getUser(userId);
	user.setName(mapfromjson.get("name"));
	user.setSex(mapfromjson.get("sex"));
	user.setAddress(mapfromjson.get("address"));
	user=accountService.updateRestUser(user);
	  Map map = new HashMap();
	    map.put("result", "0");
	    map.put("userId", mapfromjson.get("userId"));
	   map.put("name", user.getName()==null?"":user.getName());
	   map.put("sex", "0".equals(user.getSex())?"女":"男");
	   map.put("address", user.getAddress()==null?"":user.getAddress());
	    return map;
	
}
@ResponseBody
@RequestMapping(value="/uploadHeadImg",method ={RequestMethod.POST,RequestMethod.GET},
                 produces = MediaTypes.JSON_UTF_8)
 public Map UploadHeadImg(@RequestParam(value="headImg") MultipartFile mult,
		@RequestParam(value="userId") long userid,HttpServletRequest request){
     String finalPath = "/upload/headImg/" + DateUtil.getCurrentTimestamp()+mult.getOriginalFilename();
	File file = new File(finalPath); 
    try {
		FileUtils.copyInputStreamToFile(mult.getInputStream(),file);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new RestException(HttpStatus.FORBIDDEN, "头像上传失败！");
	}
    User user  = accountService.getUser(userid);
    user.setHeaderImgUrl(finalPath);
    accountService.updateUser(user);
    
    Map map = new HashMap();
    map.put("result", "0");
    map.put("userId", userid);
    map.put("headerImgUrl", finalPath);
    return map;

}

}
