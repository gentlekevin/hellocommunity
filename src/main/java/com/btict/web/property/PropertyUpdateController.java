
package com.btict.web.property;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.btict.entity.City;
import com.btict.entity.Property;
import com.btict.service.CityService;
import com.btict.service.PropertyService;


@Controller
@RequestMapping(value = "/commonAdmin")
public class PropertyUpdateController {
	
	
	@Autowired
	private PropertyService propertyService;
	@Autowired
	private CityService cityService;

	
	@RequestMapping(value="/operation/updateProperty",method ={RequestMethod.GET,RequestMethod.POST} )
	public String updatePropertyAdmin(@ModelAttribute("property") Property property ,Long cityId,Model model){
		
		if(!"".equals(cityId)&&cityId!=null){
			  City city = cityService.findCityById(cityId);
				property.setCity(city); 
		  }else{
			  property.setCity(null); 
		  }
		
		propertyService.saveProperty(property);
		
		return "redirect:/commonAdmin/list/propertyList";
		
	}
	
	
	
	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出User对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getProperty(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
		if (id != -1) {
			model.addAttribute("property", propertyService.findPropertyId(id));
		}
	}

	
	
	

	
}
