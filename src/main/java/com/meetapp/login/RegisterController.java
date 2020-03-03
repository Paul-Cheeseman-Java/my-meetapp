package com.meetapp.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.meetapp.appuser.model.AppUser;
import com.meetapp.appuser.dao.AppUserDAO;

@Controller
public class RegisterController {
	

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AppUserDAO appUserDAO;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
	public String doRegister(Model model) {
		
    	return "registerForm";
	 }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
	public String recieveRegister(Model model, HttpServletRequest request) {
    	String requestedUserName = request.getParameter("username");
		
		if (appUserDAO.getUser(requestedUserName) == null) {
	    	String encodedPassword  = passwordEncoder.encode(request.getParameter("password"));

			AppUser user = new AppUser();
			user.setUsername(requestedUserName);
			user.setPassword(encodedPassword);
			
			appUserDAO.insertUser(user);
			model.addAttribute("username", requestedUserName);
	    	return "registerSuccess";
		}
		else {
			model.addAttribute("error", "That username is taken, please choose another ");
	    	return "registerForm";
		}
	 }

}
