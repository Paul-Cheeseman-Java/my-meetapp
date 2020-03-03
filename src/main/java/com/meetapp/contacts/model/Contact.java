package com.meetapp.contacts.model;

import org.hibernate.validator.constraints.NotEmpty;

public class Contact implements Comparable<Contact> {
	private int id;
	
	@NotEmpty
	private String firstName;
	
	private String lastName;
	private String email;
	private int company;
	private String phone;

	public Contact() {
	}


	public int compareTo(Contact contact) {
        String ln1 = this.lastName;
        String ln2 = contact.lastName;

        int res = ln1.compareTo(ln2);
        if (res != 0) {
           return res;
        } else {
            String fn1 = this.firstName;
            String fn2 = contact.firstName;
           return fn1.compareTo(fn2);
        }
	}
	
	
	//Read into App from DB
	public Contact(int id, String firstName, String lastName, String email, int company, String phone) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.company = company;
		this.phone = phone;
	}

	//Create in App to save to DB
	public Contact(String firstName, String lastName, String email, int company, String phone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.company = company;
		this.phone = phone;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCompany() {
		return company;
	}

	public void setCompany(int company) {
		this.company = company;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
