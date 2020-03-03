package com.meetapp.company.model;

import com.meetapp.contacts.model.Contact;

public class Company implements Comparable<Company>{
	private int id;
	private String name;
	private int companyType;
	private String companyTypeStr;
	
	public Company() {
	}

	public Company(int id, String name, int companyType) {
		this.id = id;
		this.name = name;
		this.companyType = companyType;
	}

	public Company(int id, String name, String companyTypeStr) {
		this.id = id;
		this.name = name;
		this.companyTypeStr = companyTypeStr;
	}
	
	
	public int compareTo(Company company) {
        String name1 = this.name;
        String name2 = company.name;
        return name1.compareTo(name2);
	}
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCompanyType() {
		return companyType;
	}

	public void setCompanyType(int companyType) {
		this.companyType = companyType;
	}

	public String getCompanyTypeStr() {
		return companyTypeStr;
	}

	public void setCompanyTypeStr(String companyTypeStr) {
		this.companyTypeStr = companyTypeStr;
	}
	
}
