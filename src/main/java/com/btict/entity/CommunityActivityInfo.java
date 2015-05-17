package com.btict.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
/**
 * 社区活动资讯实体
 * @author yangkaiwen
 *
 */
@Entity
@Table(name="t_community_activity_info")
public class CommunityActivityInfo extends IdEntity {

	
	private Property property;
    private Community community;
    private Activity activity;
    private Information information;
    private String type;
    
  
    
    
    public CommunityActivityInfo() {
	}

	public CommunityActivityInfo(Long id) {
		this.id = id;
	}


	@ManyToOne
	@JoinColumn(name = "community_id")
	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}
	@ManyToOne
	@JoinColumn(name = "activity_id")
	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	@ManyToOne
	@JoinColumn(name = "information_id")
	public Information getInformation() {
		return information;
	}

	public void setInformation(Information information) {
		this.information = information;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	@ManyToOne
	@JoinColumn(name = "property_id")
		public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

		@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
   
}
