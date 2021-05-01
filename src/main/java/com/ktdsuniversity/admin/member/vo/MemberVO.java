package com.ktdsuniversity.admin.member.vo;


import org.springframework.stereotype.Component;

@Component("memberVO")
public class MemberVO {
	private String id;
	private String name;
	private String pw;
	private String phone;
	private String email;
	private String subscription;
	private String companyName;
	private String delYN;
	private String joinDate;
	private String modDate;
	private String tel;
	private int loginFail;
	
	
	public MemberVO() {}
	
	public MemberVO(String id, String name, String pw, String phone, String email, String subscription,
			String companyName, String delYN, String joinDate, String modDate, String tel, int loginFail) {
		this.id = id;
		this.name = name;
		this.pw = pw;
		this.phone = phone;
		this.email = email;
		this.subscription = subscription;
		this.companyName = companyName;
		this.delYN = delYN;
		this.joinDate = joinDate;
		this.modDate = modDate;
		this.tel = tel;
		this.loginFail = loginFail;
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
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSubscription() {
		return subscription;
	}
	
	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getDelYN() {
		return delYN;
	}
	
	public void setDelYN(String delYN) {
		this.delYN = delYN;
	}
	
	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getModDate() {
		return modDate;
	}

	public void setModDate(String modDate) {
		this.modDate = modDate;
	}
	
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getLoginFail() {
		return loginFail;
	}

	public void setLoginFail(int loginFail) {
		this.loginFail = loginFail;
	}

}
