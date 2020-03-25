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
		for (Meeting meeting: allmeetings) {
			meeting.setCompany_name(companyDAO.getCompany(meeting.getCompany_id()).getName());
			meeting.setContact_firstName(contactDAO.getContact(meeting.getContact_id()).getFirstName());
			meeting.setContact_lastName(contactDAO.getContact(meeting.getContact_id()).getLastName());
		}
		model.addAttribute("meetingList", allmeetings);
		return "meetingList";
	}

	//Global var not ideal...
	LocalDateTime submitDateTime = LocalDateTime.now();
	
	@RequestMapping(value = "/newMeeting", method = RequestMethod.GET)
	public String newMeeting(Model model, Principal principal) {
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
	public ModelAndView submitMeeting(Meeting meeting, Principal principal, HttpServletRequest request)
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
	
		if (meeting.getMeeting_start().isBefore(submitDateTime)) {
			//Not perfect, because if form is left for a while then a past date can be put in, but the error window is small
			//System.out.println("In Past - reject!");
			modelAndView.addObject("meetingError", "Meeting in the past - Rejected");
			
		} else if (meeting.getMeeting_end().isBefore(meeting.getMeeting_start().plusMinutes(15))){
			//System.out.println("Meeting Under 15 mins - reject!");
			modelAndView.addObject("meetingError", "Meeting under 15 mins - Rejected");
			
			//prevent a meeting being longer than 24 hours
			
		} else {
			//modelAndView.addObject("meetingError", "Inserted");
			meetingDAO.insertMeeting(meeting, principal.getName());
		}
		
		return modelAndView;
		

	}
	

	
	
	@RequestMapping(value = "/deleteMeeting", method = RequestMethod.GET)
	public ModelAndView deleteMeeting(HttpServletRequest request) {
		int meetingId = Integer.parseInt(request.getParameter("id"));
		meetingDAO.deleteMeeting(meetingId);
		return new ModelAndView("redirect:/meetingList");
	}
	

	@RequestMapping(value = "/editMeeting", method = RequestMethod.GET)
	public ModelAndView editMeeting(HttpServletRequest request, Principal principal) {
		int meetingId = Integer.parseInt(request.getParameter("id"));
		Meeting meeting = meetingDAO.getMeeting(meetingId);
		ModelAndView modelAndView = new ModelAndView("meetingForm");
		List<Company> currentCompaniesList = companyDAO.listCompanies(principal.getName());
		List<Contact> currentContactsList = contactDAO.listContacts(principal.getName());
		List<MeetingType> meetingTypesList = meetingDAO.listMeetingTypes();

		modelAndView.addObject("meeting", meeting);
		modelAndView.addObject("companiesList", currentCompaniesList);
		modelAndView.addObject("contactsList", currentContactsList);
		modelAndView.addObject("meetingTypesList", meetingTypesList);
		modelAndView.addObject("title", "Existing Meeting");
		modelAndView.addObject("buttontext", "Update Meeting");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/editMeeting", method = RequestMethod.POST)
	public ModelAndView updateMeeting(Model model, Meeting meeting) {
		meetingDAO.updateMeeting(meeting);
		return new ModelAndView("redirect:/meetingList");
	}
}
