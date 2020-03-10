package com.meetapp.meetings.dao;

import java.util.List;

import com.meetapp.contacts.model.Contact;


public interface MeetingDAO {
	
	public void updateContact(Contact contact);
	
	public void insertContact(Contact contact, String username);
	
	public void deleteContact(int contactId);
	
	public Contact getContact(int contactId);
	
	public List<Contact> listContacts(String username);
}
