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
		if (companyDAO.getCompany(company.getName()) != null) {
		    ModelAndView modelAndView = new ModelAndView("companyForm");
			List<CompanyType> companyTypesList = companyDAO.listCompanyTypes();
			modelAndView.addObject("companyTypesList", companyTypesList);
			modelAndView.addObject("title", "Existing Company");
			modelAndView.addObject("buttontext", "Update Company");
		    modelAndView.addObject("company", company);
		    modelAndView.addObject("duplicateCompany", true);
			return modelAndView;
		} else {
			companyDAO.insertCompany(company, principal.getName());
		    ModelAndView modelAndView = new ModelAndView("companyList");
			List<Company> allcompanys = companyDAO.listCompaniesStrCompanyType(principal.getName());
			List<String> getCompaniesUsed = companyDAO.getCompaniesUsed(principal.getName());
			modelAndView.addObject("companyList", allcompanys);
			modelAndView.addObject("companiesUsed", getCompaniesUsed);
			return modelAndView;
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
		List<CompanyType> companiesTypesList = companyDAO.listCompanyTypes();
		model.addAttribute("company", company);
		model.addAttribute("companyTypesList", companiesTypesList);
		model.addAttribute("title", "Existing Company");
		model.addAttribute("buttontext", "Update Company");
		return "companyForm";
	}
	
	

	@RequestMapping(value = "/editCompany", method = RequestMethod.POST)
	public ModelAndView updatecompany(HttpServletRequest request, ModelMap model, Company company) {

		int companyId = Integer.parseInt(request.getParameter("id"));
		String companyNameInDB = companyDAO.getCompany(companyId).getName();
		String companyNameFromForm = company.getName();
	
		
		// The name is being changed but the desired name is already used in db
		if (!companyNameInDB.equals(companyNameFromForm) && (companyDAO.getCompany(companyNameFromForm) != null)){
		    ModelAndView modelAndView = new ModelAndView("companyForm");
			List<CompanyType> companyTypesList = companyDAO.listCompanyTypes();
			modelAndView.addObject("companyTypesList", companyTypesList);
			modelAndView.addObject("title", "Existing Company");
			modelAndView.addObject("buttontext", "Update Company");
		    modelAndView.addObject("company", company);
		    modelAndView.addObject("duplicateCompany", true);
			return modelAndView;
		}
		else {
			companyDAO.updateCompany(company);
			return new ModelAndView("redirect:/companyList");			
		}

	}
	
}
