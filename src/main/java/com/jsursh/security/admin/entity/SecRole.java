package com.jsursh.security.admin.entity;

import java.util.Date;


/**
 * role 
 * @author sunburst
 *
 */
public class SecRole implements java.io.Serializable {

	private static final long serialVersionUID = 2907317174547259108L;

	private Long id;
	
	private String text;
	
	private String code;
	
	private String remark;
	
	private Date createTime;
	
	private Date updateTime;

	public SecRole() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return (this.text == null ? "null":this.text) + "@" + (this.id==null?"null":this.id);
	}

	@Override
	public boolean equals(Object otherObj) {
		if (otherObj == null)
			return false;
		if (!(otherObj instanceof SecRole))
			return false;
		SecRole otherRole = (SecRole) otherObj;
		Long otherId = otherRole.getId();
		Long thisId = this.getId();
		if (otherId == null)
			return false;
		else if (thisId == null) 
			return false;
		else if (otherId.equals(thisId))
			return true;
		else 
			return false;
	}
	
	@Override
	public int hashCode() {
		return this.id == null ? 1 : this.id.intValue();
	}

}