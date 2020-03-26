package com.meetapp.company.dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.meetapp.company.model.Company;
import com.meetapp.company.model.CompanyType;

@Repository
public class CompanyDAOImpl implements CompanyDAO{

	private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	public CompanyDAOImpl() {
		
	}

	@Override
	public List<Company> listCompanies(String username) {
		String sql = "SELECT * FROM company WHERE username = '" + username + "'";
		List<Company> listCompanies = jdbcTemplate.query(sql, new RowMapper<Company>() {

			public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
				Company aCompany = new Company();
	
				aCompany.setId(rs.getInt("id"));
				aCompany.setName(rs.getString("name"));
				aCompany.setCompanyType(rs.getInt("company_type"));
				return aCompany;
			}
			
		});
		Collections.sort(listCompanies);
		return listCompanies;
	}
	
	
	@Override
	public List<Company> listCompaniesStrCompanyType(String username) {
		String sql = "SELECT company.id, company.name, type FROM Company inner join company_type on company_type=company_type.id "
				+ "WHERE company.username = '" + username + "'";
		List<Company> listCompanies = jdbcTemplate.query(sql, new RowMapper<Company>() {

			public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
				Company aCompany = new Company();
				aCompany.setId(rs.getInt("id"));
				aCompany.setName(rs.getString("name"));
				aCompany.setCompanyTypeStr(rs.getString("type"));					
				return aCompany;
			}
			
		});
		Collections.sort(listCompanies);
		return listCompanies;
	}
	
	
	@Override
	public List<CompanyType> listCompanyTypes() {
		String sql = "SELECT * FROM company_type";
		List<CompanyType> listCompanyTypes = jdbcTemplate.query(sql, new RowMapper<CompanyType>() {

			public CompanyType mapRow(ResultSet rs, int rowNum) throws SQLException {
				CompanyType aCompanyType = new CompanyType();
	
				aCompanyType.setId(rs.getInt("id"));
				aCompanyType.setType(rs.getString("type"));
				
				return aCompanyType;
			}
			
		});
		Collections.sort(listCompanyTypes);		
		return listCompanyTypes;
	}
	
	
	@Override
	public void updateCompany(Company company) {
		String sql = "UPDATE Company SET name=?, company_type=? WHERE id=?";
		jdbcTemplate.update(sql, company.getName(), company.getCompanyType(), company.getId());
	}

	

	@Override
	public void insertCompany(Company Company, String username) {
		String sql = "INSERT INTO Company (name, company_type, username) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, Company.getName(), Company.getCompanyType(), username);
	}
	
	@Override
	public void deleteCompany(int CompanyId) {
		String sql = "DELETE FROM Company WHERE id=?";
		jdbcTemplate.update(sql, CompanyId);
	}

	

	@Override
	public List<String> getCompaniesUsed(String username) {
		String sql = "SELECT company.name FROM contact inner join company on contact.company_id = company.id "
				+ "WHERE contact.username = '" + username + "' GROUP BY company_id";
		List<String> companiesUsed = jdbcTemplate.query(sql, new RowMapper<String>() {

			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				return rs.getString("name");
			}
			
		});
		return companiesUsed;
	}
	


	@Override
	public Company getCompany(int CompanyId) {
		String sql = "SELECT * FROM Company WHERE id=" + CompanyId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Company>() {
			@Override
			public Company extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Company Company = new Company();
					Company.setId(rs.getInt("id"));
					Company.setName(rs.getString("name"));
					Company.setCompanyType(rs.getInt("company_type"));					
					return Company;
				}
				return null;
			}
		});
	}
	
	
	@Override
	public Company getCompany(String companyName) {
		String sql = "SELECT * FROM Company WHERE name='" + companyName +"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Company>() {
			@Override
			public Company extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Company Company = new Company();
					Company.setId(rs.getInt("id"));
					Company.setName(rs.getString("name"));
					Company.setCompanyType(rs.getInt("company_type"));					
					return Company;
				}
				return null;
			}
		});
	}
	
}
