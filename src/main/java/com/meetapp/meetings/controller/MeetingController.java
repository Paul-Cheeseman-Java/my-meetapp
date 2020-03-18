package com.meetapp.meetings.controller;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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


@Controller
public class MeetingController {

	@Autowired
	private ContactDAO contactDAO;
	
	@Autowired
	private CompanyDAO companyDAO;

	@Autowired
	private MeetingDAO meetingDAO;
	
	/*
	@RequestMapping(value = "/meetingList", method = RequestMethod.GET)
	public String contactList(Model model, Principal principal) {
		List<Contact> allmeetings = meetingDAO.listContacts(principal.getName());
		model.addAttribute("meetingList", allmeetings);
		return "meetingList";
	}
	*/

	//Global var not ideal...
	LocalDateTime submitDateTime = LocalDateTime.now();
	
	@RequestMapping(value = "/newMeeting", method = RequestMethod.GET)
	public String contactList(Model model, Principal principal) {
		
		//NEW MEETING WILL HAVE 'NOW()' FOR MEETING TIME, AMEND WILL HAVE DATES/TIMES (AND EVERYTHING ELSE) FROM DB 
		
		/*
		List<Company> currentCompaniesList = companyDAO.listCompanies(principal.getName());
		List<Contact> currentContactsList = contactDAO.listContacts(principal.getName());
		model.addAttribute("companiesList", currentCompaniesList);
		model.addAttribute("contactsList", currentContactsList);
		*/
		Meeting newMeeting = new Meeting();
		newMeeting.setMeeting_start(LocalDateTime.now());
		model.addAttribute("meeting", newMeeting);
		model.addAttribute("title", "New Meeting");
		model.addAttribute("buttontext", "Create Meeting");
		//System.out.println("Test: " +newMeeting.getNotes());
		//System.out.println("Test: " +newMeeting.getMeeting_start());
		return "meetingForm";
	}
	

	
	
	@RequestMapping(value = "/newMeeting", method = RequestMethod.POST)
	public ModelAndView submitContact(ModelAndView model, Meeting meeting, Principal principal)
	{
		
		System.out.println("Submit: " + submitDateTime);
		System.out.println("Now: " + LocalDateTime.now());
		if (meeting.getMeeting_start().isBefore(submitDateTime)) {
			//Not perfect, as is form is left for a while then a past date can be put in, but the error window is small
			System.out.println("In Past - reject!");
		}

		//meetingDAO.insertMeeting(meeting);

		
		//Meeting test = meetingDAO.getMeeting(3);
		//System.out.println("Testing: " +test.getMeeting_start());

		//System.out.println("Testing /'s?: " +Meeting.convertDatetoBlackSlash(test.getMeeting_start().toLocalDate()));
		//System.out.println("Testing Date: " +test.getMeeting_start().toLocalDate());		
		//System.out.println("Testing Time: " +test.getMeeting_start().toLocalTime());
		
		return new ModelAndView("redirect:/newMeeting");
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
