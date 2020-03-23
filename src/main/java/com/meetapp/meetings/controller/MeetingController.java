package com.meetapp.meetings.controller;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.meetapp.meetings.dao.MeetingDAO;
import com.meetapp.meetings.model.Meeting;
import com.meetapp.meetings.model.MeetingType;


@Controller
public class MeetingController {

	@Autowired
	private ContactDAO contactDAO;
	
	@Autowired
	private CompanyDAO companyDAO;

	@Autowired
	private MeetingDAO meetingDAO;
	
	@RequestMapping(value = "/meetingList", method = RequestMethod.GET)
	public String meetingList(Model model, Principal principal) {
		List<Meeting> allmeetings = meetingDAO.listMeetings(principal.getName());
		model.addAttribute("meetingList", allmeetings);
		return "meetingList";
	}

	//Global var not ideal...
	LocalDateTime submitDateTime = LocalDateTime.now();
	
	@RequestMapping(value = "/newMeeting", method = RequestMethod.GET)
	public String contactList(Model model, Principal principal) {
		
		//NEW MEETING WILL HAVE 'NOW()' FOR MEETING TIME, AMEND WILL HAVE DATES/TIMES (AND EVERYTHING ELSE) FROM DB 
		
		List<Company> currentCompaniesList = companyDAO.listCompanies(principal.getName());
		List<Contact> currentContactsList = contactDAO.listContacts(principal.getName());
		List<MeetingType> meetingTypesList = meetingDAO.listMeetingTypes();
		
		Meeting newMeeting = new Meeting();
		newMeeting.setMeeting_start(LocalDateTime.now());
		newMeeting.setMeeting_end(LocalDateTime.now());
		model.addAttribute("meeting", newMeeting);
		model.addAttribute("title", "New Meeting");
		model.addAttribute("buttontext", "Create Meeting");
		model.addAttribute("companiesList", currentCompaniesList);
		model.addAttribute("contactsList", currentContactsList);
		model.addAttribute("meetingTypesList", meetingTypesList);
		return "meetingForm";
	}
	

	
	
	@RequestMapping(value = "/newMeeting", method = RequestMethod.POST)
	public ModelAndView submitContact(Meeting meeting, Principal principal, HttpServletRequest request)
	{
		
		ModelAndView modelAndView = new ModelAndView("meetingForm");
		List<Company> currentCompaniesList = companyDAO.listCompanies(principal.getName());
		List<Contact> currentContactsList = contactDAO.listContacts(principal.getName());
		List<MeetingType> meetingTypesList = meetingDAO.listMeetingTypes();
		
		modelAndView.addObject("title", "New Meeting");
		modelAndView.addObject("buttontext", "Create Meeting");
		modelAndView.addObject("companiesList", currentCompaniesList);
		modelAndView.addObject("contactsList", currentContactsList);
		modelAndView.addObject("meetingTypesList", meetingTypesList);
		
		
	    //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    //String strDate= formatter.format(date);  

		
		if (meeting.getMeeting_start().isBefore(submitDateTime)) {
			//Not perfect, because if form is left for a while then a past date can be put in, but the error window is small
			System.out.println("In Past - reject!");
			modelAndView.addObject("meetingError", "Meeting in the past - Rejected");
			
		} else if (meeting.getMeeting_end().isBefore(meeting.getMeeting_start().plusMinutes(15))){
			System.out.println("Meeting Under 15 mins - reject!");
			modelAndView.addObject("meetingError", "Meeting under 15 mins - Rejected");
			
			
			//prevent a meeting being longer than 24 hours
			
		} else {
			//modelAndView.addObject("meetingError", "Inserted");
			meetingDAO.insertMeeting(meeting, principal.getName());
		}
		
		return modelAndView;
		

	}
	

	
	
	/*
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
