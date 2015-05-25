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
 * 资讯实体
 * @author yangkaiwen
 *
 */
@Entity
@Table(name="t_information")
public class Information extends IdEntity {

	
	private String title;
    private String content;
    private String pic;
 	private Date publishDate;
    private Property property;
    private Set<CommunityActivityInfo> infos;
    
    
    public Information() {
	}

	public Information(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
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
	(cascade={CascadeType.REMOVE}, fetch = FetchType.LAZY,mappedBy="information")  
	@JsonIgnore
	public Set<CommunityActivityInfo> getInfos() {
		return infos;
	}

	public void setInfos(Set<CommunityActivityInfo> infos) {
		this.infos = infos;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
   
}
