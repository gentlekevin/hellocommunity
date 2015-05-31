
package com.btict.web.complaint;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springside.modules.utils.Clock;

import com.btict.entity.Complaint;
import com.btict.service.ComplaintService;
import com.btict.service.PropertyService;


@Controller
@RequestMapping(value = "/propertyAdmin")
public class ComplaintUpdateController {
	
	
	@Autowired
	private PropertyService propertyService;
	@Autowired
	private ComplaintService complaintService;
	
	private Clock clock = Clock.DEFAULT;

	
	@RequestMapping(value="/operation/complaintUpdate",method ={RequestMethod.GET,RequestMethod.POST} )
	public String updateComplaint(@ModelAttribute("complaint") Complaint complaint,Model model){
		
		
		if("3".equals(complaint.getStatus())){
			complaint.setFlag("1");//设置成完成
			complaint.setComplaintDate(clock.getCurrentDate());
		}
		complaintService.saveComplaint(complaint);
	   return "redirect:/propertyAdmin/list/complaintList";
		
	}
	
	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出User对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getProperty(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
		
		if (id != -1) {
			model.addAttribute("complaint", complaintService.findComplaintById(id));
		}
	}



	public void setClock(Clock clock) {
		this.clock = clock;
	}
	
}
