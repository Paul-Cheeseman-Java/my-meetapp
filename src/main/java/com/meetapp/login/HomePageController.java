package com.meetapp.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.meetapp.meetings.dao.MeetingDAO;


@Controller
public class HomePageController {
	
	@Autowired
	private MeetingDAO meetingDAO;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showHomePage(ModelMap model) {
		
		int allFace2Face = meetingDAO.countAllMeetingsForType("Face to Face");
		int allVidConf = meetingDAO.countAllMeetingsForType("Video Conf");
		int allVoiceConf = meetingDAO.countAllMeetingsForType("Voice Conf");
		int total = allFace2Face + allVidConf + allVoiceConf;
				
		model.addAttribute("allFace2Face", allFace2Face);
		model.addAttribute("allVidConf", allVidConf);
		model.addAttribute("allVoiceConf", allVoiceConf);
		model.addAttribute("total", total);
		
		return "home";
	}

	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String showHomePageAgain(ModelMap model) {
		
		int allFace2Face = meetingDAO.countAllMeetingsForType("Face to Face");
		int allVidConf = meetingDAO.countAllMeetingsForType("Video Conf");
		int allVoiceConf = meetingDAO.countAllMeetingsForType("Voice Conf");
		int total = allFace2Face + allVidConf + allVoiceConf;
				
		model.addAttribute("allFace2Face", allFace2Face);
		model.addAttribute("allVidConf", allVidConf);
		model.addAttribute("allVoiceConf", allVoiceConf);
		model.addAttribute("total", total);
		
		return "home";
	}
	
	
}
