package com.btict.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
/**
 * 物业实体
 * @author yangkaiwen
 *
 */
@Entity
@Table(name="t_property")
public class Property extends IdEntity {

	
	private String name;
    private String address;
    private String contacts;
    private String phone;
    private City city;
    
    public Property() {
	}

	public Property(Long id) {
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
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
   
}
