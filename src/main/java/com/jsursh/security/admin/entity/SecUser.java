package com.jsursh.security.admin.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * login user
 * 
 * @author sunburst
 *
 */
@Entity
@Table(name="sec_user")
public class SecUser implements Serializable {

	private static final long serialVersionUID = -5255719849430981056L;

	private Long id;
	
	private String loginName;
	
	private String longinPassword;
	
	private String fullName;
	
	private String post; // 签名
	
	private Long roleId;
	
	private String roleName;

	private Date registerTime;
	
	private int valid = 0;// 0:未审核，1：正常, 2：删除

	public SecUser() {
	}
	
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}



	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}



	public String getLonginPassword() {
		return longinPassword;
	}



	public void setLonginPassword(String longinPassword) {
		this.longinPassword = longinPassword;
	}



	public String getFullName() {
		return fullName;
	}



	public void setFullName(String fullName) {
		this.fullName = fullName;
	}



	public String getPost() {
		return post;
	}



	public void setPost(String post) {
		this.post = post;
	}



	public Long getRoleId() {
		return roleId;
	}



	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}



	public String getRoleName() {
		return roleName;
	}



	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}



	public Date getRegisterTime() {
		return registerTime;
	}



	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}



	public int getValid() {
		return valid;
	}



	public void setValid(int valid) {
		this.valid = valid;
	}

	@Override
	public boolean equals(Object otherObj) {
		if (otherObj == null)
			return false;
		if (!(otherObj instanceof SecUser))
			return false;
		SecUser otherSecuser = (SecUser) otherObj;
		Long otherId = otherSecuser.getId();
		if (otherId.equals(this.getId())) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.id == null ? 0 : this.id.intValue();
	}
	
	@Override
	public String toString() {
		return (this.loginName==null ? "null" : this.loginName) +"@" +(this.id==null?"null":this.id);
	}
	
	
}
