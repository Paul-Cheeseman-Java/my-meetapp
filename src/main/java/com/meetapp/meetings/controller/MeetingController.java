package com.meetapp.meetings.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.meetapp.company.dao.CompanyDAO;
import com.meetapp.company.model.Company;
import com.meetapp.contacts.dao.ContactDAO;
import com.meetapp.contacts.model.Contact;
import com.meetapp.login.LoginController;


@Controller
public class MeetingController {

	@Autowired
	private ContactDAO contactDAO;
	
	@Autowired
	private CompanyDAO companyDAO;

	/*
	@RequestMapping(value = "/meetingList", method = RequestMethod.GET)
	public String contactList(Model model, Principal principal) {
		List<Contact> allmeetings = meetingDAO.listContacts(principal.getName());
		model.addAttribute("meetingList", allmeetings);
		return "meetingList";
	}

	
	@RequestMapping(value = "/newMeeting", method = RequestMethod.GET)
	public String newContact(Model model, Principal principal) {
		List<Company> currentCompaniesList = companyDAO.listCompanies(principal.getName());
		List<Contact> currentContactsList = contactDAO.listContacts(principal.getName());
		model.addAttribute("companiesList", currentCompaniesList);
		model.addAttribute("contactsList", currentContactsList);
		Meeting newMeeting = new Meeting();
		model.addAttribute("meeting", newMeeting);
		model.addAttribute("title", "New Contact");
		model.addAttribute("buttontext", "Create Contact");
		return "meetingForm";
	}
	
	
	@RequestMapping(value = "/newContact", method = RequestMethod.POST)
	public ModelAndView submitContact(ModelAndView model, Contact contact, Principal principal) {
		contactDAO.insertContact(contact, principal.getName());
		return new ModelAndView("redirect:/contactList");
	}
	
	
	@RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
	public ModelAndView deleteContact(HttpServletRequest request) {
		int contactId = Integer.parseInt(request.getParameter("id"));
		contactDAO.deleteContact(contactId);
		return new ModelAndView("redirect:/contactList");
	}
	
	
	@RequestMapping(value = "/editContact", method = RequestMethod.GET)
	public String editContact(HttpServletRequest request, Model model, Principal principal) {
		int contactId = Integer.parseInt(request.getParameter("id"));
		Contact contact = contactDAO.getContact(contactId);
		List<Company> currentCompaniesList = companyDAO.listCompanies(principal.getName());
		model.addAttribute("contact", contact);
		model.addAttribute("companiesList", currentCompaniesList);
		model.addAttribute("title", "Existing Contact");
		model.addAttribute("buttontext", "Update Contact");
		return "contactForm";
	}
	
	

	@RequestMapping(value = "/editContact", method = RequestMethod.POST)
	public ModelAndView updateContact(Model model, Contact contact) {
		contactDAO.updateContact(contact);
		return new ModelAndView("redirect:/contactList");
	}
	
	*/
}
