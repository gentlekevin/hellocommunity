
package com.btict.web.information;


import java.util.Map;
import javax.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springside.modules.web.Servlets;
import com.btict.entity.City;
import com.btict.entity.Information;
import com.btict.service.CityService;
import com.btict.service.InformationService;
import com.btict.service.UserService;
import com.google.common.collect.Maps;



@Controller
@RequestMapping(value = "/commonAdmin")
public class InformationController {
	private static final String PAGE_SIZE = "10";
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动");
		
	}
	@Autowired
	private UserService userService;
	

	@Autowired
	private InformationService informationService;

	
	@RequestMapping(value="/list/informationList",method = {RequestMethod.GET,RequestMethod.POST,})
	public String informationList(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		Page<Information> properties = informationService.getInformations(searchParams, pageNumber, pageSize, sortType);
			

		model.addAttribute("properties", properties);
		model.addAttribute("sortType", sortType);
		model.addAttribute("sortTypes", sortTypes);
	
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        model.addAttribute("thisPagePath","/commonAdmin/list/informationList");
		return "commonAdmin/informationList";
	}
	@RequestMapping(value="/page/addInformationForm", method ={RequestMethod.GET,RequestMethod.POST})
	public String addInformationForm(Model model) {
	
		
		model.addAttribute("formType", "add");

		
	return "commonAdmin/informationForm";
	}
	
	
	@RequestMapping(value="/page/updateInformationForm", method ={RequestMethod.GET,RequestMethod.POST})
	public String updateInformationAdminForm(Model model,Long id) {
	
		Information information = informationService.findInformationById(id);
		model.addAttribute("information", information);
		model.addAttribute("formType", "update");
	
		
	return "commonAdmin/informationForm";
	}
	@RequestMapping(value="/operation/addInformation", method ={RequestMethod.GET,RequestMethod.POST})
	public String addInformationAdmin(Information information,String cityId, Model model) {
		
	
		informationService.saveInformation(information);
		return "redirect:/commonAdmin/list/informationList";
	}
	
	@RequestMapping(value="/operation/deleteInformation",method ={RequestMethod.GET,RequestMethod.POST} )
	public String deleteInformation(Long id){
		
		informationService.deleteInformation(id);
	
		return "redirect:/commonAdmin/list/informationList";
		
	}
	
	@RequestMapping(value="/operation/deleteInformations",method ={RequestMethod.GET,RequestMethod.POST} )
	public String deleteProperties(String informationIds){
		
		String [] ids = informationIds.split(",");
		for(String id:ids){
			informationService.deleteInformation(Long.parseLong(id));
		}
		return "redirect:/commonAdmin/list/informationList";
	}
	
	
}
