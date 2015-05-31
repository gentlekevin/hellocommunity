package com.btict.service.sms;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.btict.entity.PhoneCode;
import com.btict.repository.PhoneCodeDAO;

@Component
public class SMSService {
    
    private String accountSid = "ebf2ec3029e70e3d139da80a5925e3b9";//账号模板id 云之讯提供
    private String appId = "2ceb7707188143839a0d1b2203ea1873";//APP模板id 云之讯提供
    private String templateId = "7410";//短信模板id 云之讯提供
    private String period = "2";//短信的有效期 
    private String perDayMsg = "5";//每天的短息条数
    private String authToken = "075bc17fba43ae740ac53acf3ae2714a";
    
	@Autowired
   private 	SmsTemplat sms;
	@Autowired
	private PhoneCodeDAO phoneCodeDAO;
	
	/**
	 * 发送手机验证码
	 * @param authToken
	 * @param to 要发送的手机号
 	 * @param param 形如：验证码,5.其中5代表分钟
	 * @return
	 */
	public String  sendMsg( String to, String param){
		String result = sms.templateSMS(accountSid, authToken, appId, templateId, to, param+","+period);
		boolean flag = false;
		if(result.contains("000000")){//发送成功
			flag  =true;
		}
		return result;
	}
	/**
	 * 验证code
	 * @param phone
	 * @param code
	 * @return 返回(有效/无效/验证码错误)
	 */
	public String validteCode(String phone,String code){
	
     String result = "";
	 PhoneCode phoneCode = phoneCodeDAO.findByPhoneAndCodeAndDateStampGreaterThan(phone, code,getToday00());
	 long periodTmp = (new Date().getTime()-phoneCode.getDateStamp().getTime())/(1000*60);
	 if(phoneCode==null){
		 result = "验证码错误";
	 }else if(periodTmp<Integer.parseInt(period)){//小于有效时间间隔，则有效
		 result = "success";
	 }else{//验证码过期
		 result = "验证码过期";
	 }
	 
	return result;
	}
	
	/**
	 * 是否超过今天短信最大值
	 * @param phone
	 * @return ture:超过，false:没超过
	 */
	public boolean isOverPerDayMsg(String phone){
	    
		boolean flag  = false;
		List<PhoneCode> list = phoneCodeDAO.totalTodayCode(phone, getToday00());
		if(list!=null){
			if(list.size()==Integer.parseInt(perDayMsg)){
				flag = true;
			}
		}
		
		return flag;
	}
	
	public void saveCodeRecord(PhoneCode entity){
		phoneCodeDAO.save(entity);
	}
	
	
	/**
	 * 得到今天凌晨的时间
	 * @return
	 */
	public static Date getToday00(){
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(java.util.Calendar.HOUR_OF_DAY, 0);
		c.set(java.util.Calendar.MINUTE, 0);
		c.set(java.util.Calendar.SECOND, 0);
	     return c.getTime();
	}
	
}
