package com.meetapp.meetings.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.meetapp.company.dao.CompanyDAO;
import com.meetapp.company.model.Company;
import com.meetapp.contacts.dao.ContactDAO;
import com.meetapp.contacts.model.Contact;
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
		List<Meeting> currentMeetingsList = meetingDAO.listMeetings(principal.getName());
		List<MeetingType> meetingTypesList = meetingDAO.listMeetingTypes();
		
		modelAndView.addObject("title", "New Meeting");
		modelAndView.addObject("buttontext", "Create Meeting");
		modelAndView.addObject("companiesList", currentCompaniesList);
		modelAndView.addObject("contactsList", currentContactsList);
		modelAndView.addObject("meetingTypesList", meetingTypesList);

		boolean canAddMeeting = true;
		
		if (meeting.getMeeting_start().isBefore(submitDateTime)) {
			//Not perfect, because if form is left for a while then a past date can be put in, but the error window is small
			modelAndView.addObject("meetingError", "Meeting in the past - Rejected");
			canAddMeeting = false;
			
		} else if (meeting.getMeeting_end().isBefore(meeting.getMeeting_start().plusMinutes(15))){
			modelAndView.addObject("meetingError", "Meeting under 15 mins - Rejected");
			canAddMeeting = false;
		
		} else if (meeting.getMeeting_end().isAfter(meeting.getMeeting_start().plusHours(12))){
			modelAndView.addObject("meetingError", "Meetings of 12 hours or over not permitted - Rejected");
			canAddMeeting = false;
			
		} else {
			
			for (Meeting existingMeeting : currentMeetingsList) {
				if (
					//Is requested meeting start between start and end time of existing meeting?
					(meeting.getMeeting_start().isAfter(existingMeeting.getMeeting_start()) &&
				     meeting.getMeeting_start().isBefore(existingMeeting.getMeeting_end())) ||
					//OR requested meeting end between start and end time of existing meeting?				   					
					(meeting.getMeeting_end().isAfter(existingMeeting.getMeeting_start()) &&
				     meeting.getMeeting_end().isBefore(existingMeeting.getMeeting_end())) ||
					//OR requested meeting over-arching the existing meeting?				   					
					(meeting.getMeeting_start().isBefore(existingMeeting.getMeeting_start()) &&
					 meeting.getMeeting_end().isAfter(existingMeeting.getMeeting_end()))) {

					canAddMeeting = false;
					modelAndView.addObject("meetingError", "Meeting already booked at that time range - Rejected");
				}
			}
		} 
		if (canAddMeeting){
			meetingDAO.insertMeeting(meeting, principal.getName());
		
			modelAndView = new ModelAndView("meetingList");
			List<Meeting> allmeetings = meetingDAO.listMeetings(principal.getName());
			for (Meeting aMeeting: allmeetings) {
				aMeeting.setCompany_name(companyDAO.getCompany(aMeeting.getCompany_id()).getName());
				aMeeting.setContact_firstName(contactDAO.getContact(aMeeting.getContact_id()).getFirstName());
				aMeeting.setContact_lastName(contactDAO.getContact(aMeeting.getContact_id()).getLastName());
			}
			modelAndView.addObject("meetingList", allmeetings);
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
