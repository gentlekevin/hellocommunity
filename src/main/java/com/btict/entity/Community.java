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
import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * 社区实体
 * @author yangkaiwen
 *
 */
@Entity
@Table(name="t_community")
public class Community extends IdEntity {

	
	private String name;
    private String address;
    private String contacts;
    private String phone;
    private Date addDate;
    private City city;
    private Property property;
    private Set<Repair> repairs;
    private Set<Complaint> complaints;
   
    private Set<CommunityActivityInfo> activityInfos = null;
    public Community() {
	}

	public Community(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getContacts() {
		return contacts;
	}



	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@ManyToOne
	@JoinColumn(name = "city_id")
	public City getCity() {
		return city;
	}
	
	public void setCity(City city) {
		this.city = city;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	
	
	@ManyToOne
	@JoinColumn(name = "property_id")
	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	
	
	
	

	@OneToMany
	(cascade={CascadeType.REMOVE}, fetch = FetchType.LAZY,mappedBy="community")  
	@JsonIgnore
	public Set<Repair> getRepairs() {
		return repairs;
	}

	public void setRepairs(Set<Repair> repairs) {
		this.repairs = repairs;
	}



	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	@OneToMany
	(cascade={CascadeType.REMOVE}, fetch = FetchType.LAZY,mappedBy="community")  
	@JsonIgnore
	public Set<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(Set<Complaint> complaints) {
		this.complaints = complaints;
	}

	@OneToMany
	(cascade={CascadeType.REMOVE}, fetch = FetchType.LAZY,mappedBy="community")  
	@JsonIgnore
	public Set<CommunityActivityInfo> getActivityInfos() {
		return activityInfos;
	}

	public void setActivityInfos(Set<CommunityActivityInfo> activityInfos) {
		this.activityInfos = activityInfos;
	}
   
}
