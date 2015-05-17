
package com.btict.web.repair;


import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springside.modules.web.Servlets;

import com.btict.entity.Repair;
import com.btict.entity.Community;
import com.btict.entity.User;
import com.btict.service.RepairService;
import com.btict.service.CommunityService;
import com.btict.service.account.AccountService;
import com.btict.service.account.ShiroDbRealm.ShiroUser;
import com.google.common.collect.Maps;



@Controller
@RequestMapping(value = "/propertyAdmin")
public class RepairController {
	private static final String PAGE_SIZE = "10";
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动");
		
	}
	
	@Autowired
	private AccountService accountService;
     @Autowired
	private CommunityService communityService;
	@Autowired
	private RepairService repairService;

	
	@RequestMapping(value="/list/repairList",method = {RequestMethod.GET,RequestMethod.POST,})
	public String repairList(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
	    User user = accountService.getUser(getCurrentUserId());
	    if(user.getProperty()!=null){
	    	searchParams.put("EQ_community.property.id", String.valueOf(user.getProperty().getId()));
	    }
		Page<Repair> repairs = repairService.getRepairs(searchParams, pageNumber, pageSize, sortType);
		model.addAttribute("repairs", repairs);
		model.addAttribute("sortType", sortType);
		model.addAttribute("sortTypes", sortTypes);
		
	
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        model.addAttribute("thisPagePath","/propertyAdmin/list/repairList");
		return "propertyAdmin/repairList";
	}
	
	
	
	@RequestMapping(value="/page/updateRepairForm", method ={RequestMethod.GET,RequestMethod.POST})
	public String updateRepairForm(Model model,Long id) {
		
        User account = accountService.getUser(getCurrentUserId());
		List<Community> communitylist  = communityService.findByPropertyId(account.getProperty().getId());
		Repair repair = repairService.findRepairById(id);
		model.addAttribute("communities",communitylist);
			
		model.addAttribute("repair", repair);
		model.addAttribute("formType", "update");
	
		
	return "propertyAdmin/repairForm";
	}
	
	
	
	
	/**
	 * 取出Shiro中的当前用户Id.
	 */
	private Long getCurrentUserId() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.id;
	}
}
