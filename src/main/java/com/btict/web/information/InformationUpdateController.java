
package com.btict.web.information;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.btict.entity.Community;
import com.btict.entity.CommunityActivityInfo;
import com.btict.entity.Information;
import com.btict.service.CommunityActivityInfoService;
import com.btict.service.CommunityService;
import com.btict.service.InformationService;
import com.btict.service.PropertyService;


@Controller
@RequestMapping(value = "/propertyAdmin")
public class InformationUpdateController {
	
	
	@Autowired
	private PropertyService propertyService;
	@Autowired
	private InformationService informationService;
	@Autowired
	private CommunityService communityService;
	@Autowired
	private CommunityActivityInfoService communityActivityInfoService;

	
	@RequestMapping(value="/operation/updateInformation",method ={RequestMethod.GET,RequestMethod.POST} )
	public String updateInformation(@ModelAttribute("information") Information information ,String communityId,
			String oldCommunityIds, Model model){
		
		informationService.saveInformation(information);
		
		if(!oldCommunityIds.equals(communityId)&&oldCommunityIds!=null&&oldCommunityIds!=""){//对社区进行了修改
			
				communityActivityInfoService.deleteByInformtionId(information.getId());
			}
		if(communityId!=null){
			String [] communityIds = communityId.split(",");
		    Community community = null;
		    CommunityActivityInfo activeInfo =null;
		    for(String id:communityIds){
		    	activeInfo = new CommunityActivityInfo();
		    	community = communityService.findById(Long.parseLong(id));
		    	activeInfo.setCommunity(community);
		    	activeInfo.setProperty(community.getProperty());
		    	activeInfo.setInformation(information);
		    	activeInfo.setType("0");//0:information,1:activity
		    	communityActivityInfoService.saveCommunityActivityInfo(activeInfo);
		    }
		}
			
			 
		
		
		
		return "redirect:/propertyAdmin/list/informationList";
		
	}
	
	
	
	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出User对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getProperty(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
		if (id != -1) {
			model.addAttribute("information", informationService.findInformationById(id));
		}
	}

	
	
	

	
}