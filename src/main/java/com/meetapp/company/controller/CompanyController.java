package com.meetapp.company.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.meetapp.company.dao.CompanyDAO;
import com.meetapp.company.model.Company;
import com.meetapp.company.model.CompanyType;



@Controller
public class CompanyController {

	@Autowired
	private CompanyDAO companyDAO;
	
	
	
	@RequestMapping(value = "/companyList", method = RequestMethod.GET)
	public String companyList(Model model, Principal principal) {
		List<Company> allcompanys = companyDAO.listCompaniesStrCompanyType(principal.getName());
		
		List<String> getCompaniesUsed = companyDAO.getCompaniesUsed(principal.getName());
		
		/*
		for (String test : getCompaniesUsed) {
			System.out.println("This company is used: " +test);
		}
		*/
		model.addAttribute("companyList", allcompanys);
		model.addAttribute("companiesUsed", getCompaniesUsed);
		return "companyList";
	}

	
	
	
	@RequestMapping(value = "/newCompany", method = RequestMethod.GET)
	public String newcompany(Model model) {
		List<CompanyType> companyTypesList = companyDAO.listCompanyTypes();
		model.addAttribute("companyTypesList", companyTypesList);
		Company newCompany = new Company();
		model.addAttribute("company", newCompany);
		model.addAttribute("title", "New Company");
		model.addAttribute("buttontext", "Create Company");
		return "companyForm";
	}
	
	
	@RequestMapping(value = "/newCompany", method = RequestMethod.POST)
	public ModelAndView submitcompany(ModelMap model, Company company, Principal principal) {

		//If company names = companyNameExists = sorry, name taken, return to edit page (rather than list page)
		if (companyDAO.getCompany(company.getName()) != null) {
			model.addAttribute("duplicateCompany", true);
			return new ModelAndView("redirect:/newCompany", model);

		} else {
			companyDAO.insertCompany(company, principal.getName());
	        return new ModelAndView("redirect:/companyList");
		}
	}
	
	

	
	@RequestMapping(value = "/deleteCompany", method = RequestMethod.GET)
	public ModelAndView deletecompany(HttpServletRequest request) {
		int companyId = Integer.parseInt(request.getParameter("id"));
		companyDAO.deleteCompany(companyId);
		return new ModelAndView("redirect:/companyList");
	}
	
	
	@RequestMapping(value = "/editCompany", method = RequestMethod.GET)
	public String editcompany(HttpServletRequest request, Model model) {
		int companyId = Integer.parseInt(request.getParameter("id"));
		Company company = companyDAO.getCompany(companyId);
		company.getCompanyTypeStr();
		List<CompanyType> companiesTypesList = companyDAO.listCompanyTypes();
		model.addAttribute("company", company);
		model.addAttribute("companyTypesList", companiesTypesList);
		model.addAttribute("title", "Existing Company");
		model.addAttribute("buttontext", "Update Company");
		return "companyForm";
	}
	
	

	@RequestMapping(value = "/editCompany", method = RequestMethod.POST)
	public ModelAndView updatecompany(HttpServletRequest request, Model model, Company company) {
		//If company names = companyNameExists = sorry, name taken, return to edit page (rather than list page)

		int companyId = Integer.parseInt(request.getParameter("id"));
		String companyNameInDB = companyDAO.getCompany(companyId).getName();
		String companyNameFromForm = company.getName();
		
		//company currentName
		//if currentName == newName, do nothing
		//if 

		
		
		companyDAO.updateCompany(company);
		return new ModelAndView("redirect:/companyList");
	}
	
}
