
package com.btict.web.upload;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springside.modules.web.MediaTypes;




@Controller
@RequestMapping(value = "/propertyAdmin")
public class UploadController {

	@ResponseBody
	@RequestMapping(value="/upload/uploadPic", method ={RequestMethod.GET,RequestMethod.POST},
	 produces = MediaTypes.JSON_UTF_8)
	public Map uploadpic(@RequestParam(value="pic") MultipartFile mult,String guid,String type,Model model) {
		String fileSeparator=  System.getProperty("file.separator");
		StringBuffer ctxPath = new StringBuffer();
		//ctxPath.append(request.getSession().getServletContext().getRealPath("/"));
		String uploadfilePackage =fileSeparator+"usr"+ fileSeparator+"upload"+fileSeparator+type+fileSeparator;
	    ctxPath.append(uploadfilePackage);
	    
	    String fileName =guid+"."+mult.getOriginalFilename();
		 String uploadRealPath =ctxPath.append(fileName).toString();
		   
		File file = new File(uploadRealPath); 
		
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
			
		}
	    Map map = new HashMap();
	    map.put("msg", "success");
	    
	return map;
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
	    	FileUtils.forceDelete(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 map.put("msg", "删除失败！");
			 return map;
		}
	    map.put("msg", "success");
	    
	return map;
	}
	
	
	
}
