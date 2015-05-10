package com.jsursh.security.admin.entity;

import java.util.Date;


/**
 * 权限资源
 * @author sunburst
 *
 */
public class SecResource implements java.io.Serializable {

	private static final long serialVersionUID = 2320754574469636798L;

	private Long id;
	
	private String text; // 名字
	
	private String code; // 标识
	
	private int type = 0; // 0: 模块,1：菜单,2：按钮
	
	private String url; // 菜单url
	
	private int valid = 0; // 1: 有效， 0：无效
	
	private String remark;
	
	private Date createTime;
	
	private Date updateTime;

	public SecResource() {
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getValid() {
		return valid;
	}

	public void setValid(int valid) {
		this.valid = valid;
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
		if (!(otherObj instanceof SecResource))
			return false;
		SecResource otherRes = (SecResource) otherObj;
		Long otherId = otherRes.getId();
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
