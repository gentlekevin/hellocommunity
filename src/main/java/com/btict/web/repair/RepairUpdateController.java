
package com.btict.web.repair;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springside.modules.utils.Clock;

import com.btict.entity.Repair;
import com.btict.service.RepairService;
import com.btict.service.PropertyService;


@Controller
@RequestMapping(value = "/propertyAdmin")
public class RepairUpdateController {
	
	
	@Autowired
	private PropertyService propertyService;
	@Autowired
	private RepairService repairService;
	
	private Clock clock = Clock.DEFAULT;

	
	@RequestMapping(value="/operation/repairUpdate",method ={RequestMethod.GET,RequestMethod.POST} )
	public String updateRepair(@ModelAttribute("repair") Repair repair,Model model){
		
		
		if("3".equals(repair.getStatus())){
			repair.setFlag("1");//设置成完成
			repair.setRepairDate(clock.getCurrentDate());
		}
		repairService.saveRepair(repair);
		
	   return "redirect:/propertyAdmin/list/repairList";
		
	}
	
	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出User对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getProperty(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
		
		if (id != -1) {
			model.addAttribute("repair", repairService.findRepairById(id));
		}
	}



	public void setClock(Clock clock) {
		this.clock = clock;
	}
	
}
