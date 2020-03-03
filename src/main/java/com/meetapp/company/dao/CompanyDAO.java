package com.meetapp.company.dao;

import java.util.List;

import javax.sql.DataSource;

import com.meetapp.company.model.Company;
import com.meetapp.company.model.CompanyType;

public interface CompanyDAO {


	public void updateCompany(Company company);
	
	public void insertCompany(Company company, String username);
	
	
	public void deleteCompany(int companyId);
	
	public Company getCompany(int companyId);
	
	public List<CompanyType> listCompanyTypes();

	public List<String> getCompaniesUsed(String username);
	
	public List<Company> listCompanies(String username);
	public List<Company> listCompaniesStrCompanyType(String username);

	
}
