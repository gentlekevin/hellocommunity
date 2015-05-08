package com.btict.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 城市实体
 * @author yangkaiwen
 *
 */
@Entity
@Table(name = "t_city")
public class City extends IdEntity {

	
	private String name;
	
	
	public City() {
		super();
	}
	public City(int id) {
		
		this.id = (long) id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
