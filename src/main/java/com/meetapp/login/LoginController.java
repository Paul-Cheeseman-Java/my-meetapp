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
import com.meetapp.meetings.model.MeetingType;

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
		
		List<Meeting> vidConfMeetings;
		List<Meeting>voiceConfMeetings;
		List<Meeting>face2FaceMeetings;
		int totalMeetings = 0;
		
		int range;
		List<Meeting> meetingList;
		if (request.getParameter("id") == null) {
			range = 1;
		}
		else {
			try {
				range = Integer.parseInt(request.getParameter("id"));
			}
			catch (NumberFormatException e)
			{
				range = 1;
			}
		}
		if (range == 1 || range == 7 || range == 14 || range == 30) {
				meetingList = meetingDAO.listUpcomingMeetings(getLoggedInUserName(), range);
				//Not happy with hardcoded values.........
				face2FaceMeetings = meetingDAO.listUpcomingMeetings(getLoggedInUserName(), range, 1);
				vidConfMeetings = meetingDAO.listUpcomingMeetings(getLoggedInUserName(), range, 2);
				voiceConfMeetings = meetingDAO.listUpcomingMeetings(getLoggedInUserName(), range, 3);
		}
		else if (range == 10) {
			meetingList = meetingDAO.listUpcomingMeetings(getLoggedInUserName());
			//Not happy with hardcoded values.........
			face2FaceMeetings = meetingDAO.listUpcomingMeetings(getLoggedInUserName(), 1);
			vidConfMeetings = meetingDAO.listUpcomingMeetings(getLoggedInUserName(), 2);
			voiceConfMeetings = meetingDAO.listUpcomingMeetings(getLoggedInUserName(), 3);
		}
		
		else if (range == -1 || range == -5) {
			meetingList = meetingDAO.listPastMeetings(getLoggedInUserName(), range);
			//Not happy with hardcoded values.........
			face2FaceMeetings = meetingDAO.listPastMeetings(getLoggedInUserName(), range, 1);
			vidConfMeetings = meetingDAO.listPastMeetings(getLoggedInUserName(), range, 2);
			voiceConfMeetings = meetingDAO.listPastMeetings(getLoggedInUserName(), range, 3);
		}
		
		else if (range == -10) {
			meetingList = meetingDAO.listPastMeetings(getLoggedInUserName());
			//Not happy with hardcoded values.........
			face2FaceMeetings = meetingDAO.listPastMeetings(getLoggedInUserName(), 1);
			vidConfMeetings = meetingDAO.listPastMeetings(getLoggedInUserName(), 2);
			voiceConfMeetings = meetingDAO.listPastMeetings(getLoggedInUserName(), 3);
		}
		
		else {
			meetingList = meetingDAO.listMeetings(getLoggedInUserName());
			face2FaceMeetings = meetingDAO.listMeetings(getLoggedInUserName(), 1);
			vidConfMeetings = meetingDAO.listMeetings(getLoggedInUserName(), 2);
			voiceConfMeetings = meetingDAO.listMeetings(getLoggedInUserName(), 3);					
		}
		
		for (Meeting meeting: meetingList) {
			meeting.setCompany_name(companyDAO.getCompany(meeting.getCompany_id()).getName());
			meeting.setContact_firstName(contactDAO.getContact(meeting.getContact_id()).getFirstName());
			meeting.setContact_lastName(contactDAO.getContact(meeting.getContact_id()).getLastName());
		}
		
		Meeting meeting = new Meeting();
		
		model.addAttribute("face2FaceMeetings", face2FaceMeetings.size());
		model.addAttribute("vidConfMeetings", vidConfMeetings.size());
		model.addAttribute("voiceConfMeetings", voiceConfMeetings.size());
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
