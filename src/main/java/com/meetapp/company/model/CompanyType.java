package com.meetapp.company.model;

public class CompanyType implements Comparable<CompanyType>{
	private int id;
	private String type;

	public CompanyType() {
	}

	public CompanyType(int id, String type) {
		this.id = id;
		this.type = type;
	}

	
	public int compareTo(CompanyType companyType) {
        String type1 = this.type;
        String type2 = companyType.type;
        return type1.compareTo(type2);
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
