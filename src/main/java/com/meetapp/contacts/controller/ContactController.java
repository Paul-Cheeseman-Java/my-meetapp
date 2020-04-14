package com.meetapp.contacts.controller;

import java.security.Principal;
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


@Controller
public class ContactController {

	@Autowired
	private ContactDAO contactDAO;
	
	@Autowired
	private CompanyDAO companyDAO;

	
	@RequestMapping(value = "/contactList", method = RequestMethod.GET)
	public String contactList(Model model, Principal principal) {
		List<Contact> allContacts = contactDAO.listContacts(principal.getName());
		List<String> getContactsUsed = contactDAO.getContactsUsed(principal.getName());
		model.addAttribute("contactsUsed", getContactsUsed);
		model.addAttribute("contactList", allContacts);
		return "contactList";
	}

	
	@RequestMapping(value = "/newContact", method = RequestMethod.GET)
	public String newContact(Model model, Principal principal) {
		List<Company> currentCompaniesList = companyDAO.listCompanies(principal.getName());
		model.addAttribute("companiesList", currentCompaniesList);
		Contact newContact = new Contact();
		model.addAttribute("contact", newContact);
		model.addAttribute("title", "New Contact");
		model.addAttribute("buttontext", "Create Contact");
		return "contactForm";
	}
	
	
	@RequestMapping(value = "/newContact", method = RequestMethod.POST)
	public ModelAndView submitContact(ModelAndView model, Contact contact, Principal principal) {
		if(contactDAO.getContact(contact.getFirstName(), contact.getLastName()) != null) {
			ModelAndView modelAndView = new ModelAndView("redirect:/newContact");
			List<Company> currentCompaniesList = companyDAO.listCompanies(principal.getName());
			modelAndView.addObject("companiesList", currentCompaniesList);
			Contact newContact = new Contact();
			modelAndView.addObject("contact", newContact);
			modelAndView.addObject("title", "New Contact");
			modelAndView.addObject("buttontext", "Create Contact");
		    modelAndView.addObject("duplicateContact", true);
			return modelAndView;			
		}
		else {
			contactDAO.insertContact(contact, principal.getName());
			ModelAndView modelAndView = new ModelAndView("redirect:/contactList");

			//ModelAndView modelAndView = new ModelAndView("contactList");
			//List<Contact> allContacts = contactDAO.listContacts(principal.getName());
			//List<String> getContactsUsed = contactDAO.getContactsUsed(principal.getName());
			//model.addObject("contactsUsed", getContactsUsed);
			//modelAndView.addObject("contactList", allContacts);
			return modelAndView;			
		}
	}
	
	
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
	public ModelAndView updateContact(HttpServletRequest request, Model model, Contact contact, Principal principal) {
		
		int contactId = Integer.parseInt(request.getParameter("id"));
		String contactFirstNameInDB = contactDAO.getContact(contactId).getFirstName();
		String contactLastNameInDB = contactDAO.getContact(contactId).getLastName();
		String contactFirstNameFromForm = contact.getFirstName();
		String contactLastNameFromForm = contact.getLastName();
		
		
		// The name is being changed but the desired name is already in db
		if (!contactFirstNameInDB.equals(contactFirstNameFromForm) && 
				!contactLastNameInDB.equals(contactLastNameFromForm) &&
				(contactDAO.getContact(contactFirstNameFromForm, contactLastNameFromForm) != null)){
			
		    ModelAndView modelAndView = new ModelAndView("contactForm");
			List<Company> currentCompaniesList = companyDAO.listCompanies(principal.getName());
			modelAndView.addObject("contact", contact);
			modelAndView.addObject("companiesList", currentCompaniesList);
			modelAndView.addObject("title", "Existing Contact");
			modelAndView.addObject("buttontext", "Update Contact");
		    modelAndView.addObject("duplicateContact", true);
		    
		    return modelAndView;
		}
		else {
			contactDAO.updateContact(contact);
			return new ModelAndView("redirect:/contactList");			
		}
		
		
		
	}
	
	
}
