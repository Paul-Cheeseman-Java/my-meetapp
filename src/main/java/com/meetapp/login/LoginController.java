package com.meetapp.login;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.meetapp.company.dao.CompanyDAO;
import com.meetapp.company.model.Company;
import com.meetapp.contacts.dao.ContactDAO;
import com.meetapp.meetings.dao.MeetingDAO;
import com.meetapp.meetings.model.Meeting;

@Controller
public class LoginController {
	
	@Autowired
	private MeetingDAO meetingDAO;
	
	@Autowired
	private CompanyDAO companyDAO;
	
	@Autowired
	private ContactDAO contactDAO;
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	    public String login() {
	        return "login";
	    }

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String showWelcomePage(ModelMap model, HttpServletRequest request) {
		
		System.out.println("Passed id GET: " + request.getParameter("id"));

		int range;
		List<Meeting> meetingList;
		if (request.getParameter("id") != null) {
			try {
				range = Integer.parseInt(request.getParameter("id"));
			}
			catch (NumberFormatException e)
			{
				range = 1;
			}
			if (range == 1 || range == 7 || range == 14 || range == 30) {
				meetingList = meetingDAO.listUpcomingMeetings(getLoggedInUserName(), range);									
			}
			else if (range == -1 || range == -5) {
				meetingList = meetingDAO.listPastMeetings(getLoggedInUserName(), range);									
			}
			else {
				meetingList = meetingDAO.listMeetings(getLoggedInUserName());									
			}
		}
		else {
			meetingList = meetingDAO.listUpcomingMeetings(getLoggedInUserName(), 1);					
		}
	
		for (Meeting meeting: meetingList) {
			meeting.setCompany_name(companyDAO.getCompany(meeting.getCompany_id()).getName());
			meeting.setContact_firstName(contactDAO.getContact(meeting.getContact_id()).getFirstName());
			meeting.setContact_lastName(contactDAO.getContact(meeting.getContact_id()).getLastName());
		}
		Meeting meeting = new Meeting();
		model.addAttribute("meeting", meeting);
		model.addAttribute("meetingList", meetingList);
		model.addAttribute("name", getLoggedInUserName());
		return "welcome";
	}

	private String getLoggedInUserName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}

	
	



	
	
	
	
	
}
