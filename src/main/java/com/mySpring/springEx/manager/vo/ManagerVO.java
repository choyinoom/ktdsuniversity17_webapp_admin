package com.mySpring.springEx.manager.vo;

import org.springframework.stereotype.Component;

/**
 * @author User
 *
 */
@Component("managerVO")
public class ManagerVO {
	
	private String id;
	private String name;
	private String pw;
	private String email;
	private String authorization;
	private String joindate;

	public ManagerVO() {
	}
	
	public ManagerVO(String id, String name, String pw, String email, String authorization, String joindate) {
		this.id = id;
		this.name = name;
		this.pw = pw;
		this.email = email;
		this.authorization = authorization;
		this.joindate = joindate;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public String getemail() {
		return email;
	}
	
	public void setemail(String phone) {
		this.email = phone;
	}

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	public String getJoindate() {
		return joindate;
	}

	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}
	
	

}
