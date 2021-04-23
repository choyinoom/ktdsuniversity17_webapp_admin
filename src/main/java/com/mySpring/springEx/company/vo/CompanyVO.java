package com.mySpring.springEx.company.vo;

import org.springframework.stereotype.Component;

@Component("companyVO")
public class CompanyVO {
	private String id;
	private String name;
	private String regDate;
	private String modDate;
	private String contractName;
	private String managerPhone;
	private String managerEmail;
	private String contractStat;
	private String contractType;
	private String homePage;
	private String address;
	private String contractAgree;
	private String companyTel;
	
	public CompanyVO() {
		
	}
	
	public CompanyVO(String id, String name, String regDate, String modDate, String contractName,
			String managerPhone, String managerEmail, String contractStat, String contractType, String homePage, String address, String contractAgree, String companyTel) {
		this.id = id;
		this.name = name;
		this.regDate = regDate;
		this.modDate = modDate;
		this.contractName = contractName;
		this.managerPhone = managerPhone;
		this.managerEmail = managerEmail;
		this.contractStat = contractStat;
		this.contractType = contractType;
		this.homePage = homePage;
		this.address = address;
		this.contractAgree = contractAgree;
		this.companyTel = companyTel;
	}

	public String getid() {
		return id;
	}
	
	public void setid(String id) {
		this.id = id;
	}
	
	public String getname() {
		return name;
	}
	
	public void setname(String name) {
		this.name = name;
	}
	
	public String getRegDate() {
		return regDate;
	}
	
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	public String getModDate() {
		return modDate;
	}
	
	public void setModDate(String modDate) {
		this.modDate = modDate;
	}
	
	public String getContractName() {
		return contractName;
	}
	
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	
	public String getManagerPhone() {
		return managerPhone;
	}
	
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	
	public String getManagerEmail() {
		return managerEmail;
	}
	
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	
	public String getContractStat() {
		return contractStat;
	}
	
	public void setContractStat(String contractStat) {
		this.contractStat = contractStat;
	}
	
	public String getContractType() {
		return contractType;
	}
	
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	
	public String getContractAgree() {
		return contractAgree;
	}

	public void setContractAgree(String contractAgree) {
		this.contractAgree = contractAgree;
	}

	public String getCompanyTel() {
		return companyTel;
	}

	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}
	
	
}
