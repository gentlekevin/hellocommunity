
package com.btict.web.upload;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springside.modules.web.MediaTypes;

import com.btict.util.DateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;




@Controller
@RequestMapping(value = "/propertyAdmin")
public class UploadController {

	@ResponseBody
	@RequestMapping(value="/upload/uploadPictest", method ={RequestMethod.GET,RequestMethod.POST},
	 produces = MediaTypes.JSON_UTF_8)
	public Map uploadPictest(@RequestParam(value="pic") MultipartFile mult,String guid,String type,Model model) {
		String fileSeparator=  System.getProperty("file.separator");
		StringBuffer ctxPath = new StringBuffer();
		//ctxPath.append(request.getSession().getServletContext().getRealPath("/"));
		String uploadfilePackage =fileSeparator+"usr"+ fileSeparator+"upload"+fileSeparator+type+fileSeparator;
	    ctxPath.append(uploadfilePackage);
	    
	    String fileName =guid+"."+mult.getOriginalFilename();
		 String uploadRealPath =ctxPath.append(fileName).toString();
		   
		File file = new File(uploadRealPath); 
		
		
		
	    try {
			FileUtils.copyInputStreamToFile(mult.getInputStream(),file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	    Map map = new HashMap();
	    map.put("msg", "success");
	    
	return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/upload/uploadPic", method ={RequestMethod.GET,RequestMethod.POST},
	 produces = MediaTypes.JSON_UTF_8)
	public String  uploadPictest(@RequestParam(value="imgFile") MultipartFile mult,HttpServletRequest request) {
				
		if(!ServletFileUpload.isMultipartContent(request)){
			return getError("请选择文件。");
			}
//		String dirName = request.getParameter("dir");
		
		//检查扩展名
		String fileName = mult.getOriginalFilename();
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		if(!"gif,jpg,jpeg,png,bmp".contains(fileExt)){
			return getError("请上传图片格式的文件。");
			
		}
		
		String fileSeparator=  System.getProperty("file.separator");
		StringBuffer ctxPath = new StringBuffer();
		//ctxPath.append(request.getSession().getServletContext().getRealPath("/"));
		String uploadfilePackage ="";
		if(isWindows()){//测试用判断当前系统是不是windows系统
			 uploadfilePackage =request.getServletContext().getRealPath("/");
		}		
		uploadfilePackage += fileSeparator+"usr"+ fileSeparator+"upload"+fileSeparator;
	    ctxPath.append(uploadfilePackage);
	    
	     fileName =DateUtil.getCurrentTimestamp()+fileName;
		 String uploadRealPath =ctxPath.append(fileName).toString();
		System.out.println("uploadRealPath----------->"+uploadRealPath);   
		File file = new File(uploadRealPath); 
		
	    try {
			FileUtils.copyInputStreamToFile(mult.getInputStream(),file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return getError("上传失败。");
		}
	    
	    if(isWindows()){//测试用判断当前系统是不是windows系统
	    	return getSuccess(request.getContextPath() +"/usr/upload/"+fileName);		
		}		 
	    return getSuccess(request.getContextPath() +"/upload/"+fileName);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/upload/deletePic", method ={RequestMethod.GET,RequestMethod.POST},
	 produces = MediaTypes.JSON_UTF_8)
	public Map deletePic(String guid,String fileName,String type,Model model) {
		String fileSeparator=  System.getProperty("file.separator");
		StringBuffer ctxPath = new StringBuffer();
		//ctxPath.append(request.getSession().getServletContext().getRealPath("/"));
		String uploadfilePackage =fileSeparator+"usr"+ fileSeparator+"upload"+fileSeparator+type+fileSeparator;
	    ctxPath.append(uploadfilePackage);
	    
	    String filen =guid+"."+fileName;
		 String uploadRealPath =ctxPath.append(filen).toString();
		   
		File file = new File(uploadRealPath); 
		
		Map map = new HashMap();
	   
	    try {
	    	if(file.exists()){
	    	FileUtils.forceDelete(file);
	    	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 map.put("msg", "删除失败！");
			 return map;
		}
	    map.put("msg", "success");
	    
	return map;
	}
	
	
	private String getError(String message) {
		ObjectMapper mapper = new ObjectMapper();
		Map map = new HashMap();
		Map  map1 = new HashMap();
		map.put("error", "1");
		map.put("message", message);
		try {
			return  mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	private String getSuccess(String url) {
		ObjectMapper mapper = new ObjectMapper();
		Map map = new HashMap();
		Map  map1 = new HashMap();
		map.put("error", 0);
		map.put("url", url);
		try {
			return  mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public static boolean isWindows(){
		System.out.println(System.getProperties().getProperty("os.name").toUpperCase());
		  boolean flag = false;
		  if (System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1) {
		   flag = true;
		  }
		  return flag;
		 } 
}
