package com.btict.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * 手机验证码实体
 * @author yangkaiwen
 *
 */
@Entity
@Table(name="t_phone_code")
public class PhoneCode extends IdEntity {

	private String phone;
	private String code;
	private Date DateStamp;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getDateStamp() {
		return DateStamp;
	}
	public void setDateStamp(Date dateStamp) {
		DateStamp = dateStamp;
	}
	public PhoneCode(String phone, String code, Date dateStamp) {
		super();
		this.phone = phone;
		this.code = code;
		DateStamp = dateStamp;
	}
	public PhoneCode() {
		super();
	}
	
	
	
   
}
