package com.meetapp.login;

import java.util.List;
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

@Controller
public class HomePageController {
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showHomePage(ModelMap model) {
		
		return "home";
	}

}
