package com.meetapp.contacts.dao;

import java.util.List;

import com.meetapp.contacts.model.Contact;


public interface ContactDAO {
	
	public void updateContact(Contact contact);
	
	public void insertContact(Contact contact, String username);
	
	public void deleteContact(int contactId);
	
	public Contact getContact(int contactId);
	
	public Contact getContact(String firstname, String lastname);
	
	public List<Contact> listContacts(String username);
}
