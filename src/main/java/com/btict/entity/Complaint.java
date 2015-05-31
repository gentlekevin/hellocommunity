package com.btict.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 投诉实体
 * @author yangkaiwen
 *
 */
@Entity
@Table(name="t_complaint")
public class Complaint extends IdEntity {

	
	private String userPhone;
	private String title;
	private String content;
	private String pic;
	private String handlePerson;
	private String phone;
	private Date handleDate;
	private User user;
    private Date complaintDate;
    private Community community;
    private String status;
    private String flag;
    
    public Complaint() {
	}

	public Complaint(Long id) {
		this.id = id;
	}

	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
		@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getHandlePerson() {
		return handlePerson;
	}

	public void setHandlePerson(String handlePerson) {
		this.handlePerson = handlePerson;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getHandleDate() {
		return handleDate;
	}

	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getComplaintDate() {
		return complaintDate;
	}

	public void setComplaintDate(Date complaintDate) {
		this.complaintDate = complaintDate;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne
	@JoinColumn(name = "community_id")
	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public String getPhone() {
		return phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
   
}
