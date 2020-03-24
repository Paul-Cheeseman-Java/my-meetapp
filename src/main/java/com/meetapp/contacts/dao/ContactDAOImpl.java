package com.meetapp.contacts.dao;

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

import com.meetapp.contacts.model.Contact;

@Repository
public class ContactDAOImpl implements ContactDAO {

	private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	public ContactDAOImpl() {
		
	}

	@Override
	public void updateContact(Contact contact) {
		String sql = "UPDATE contact SET first_name=?, last_name=?, email=?, company_id=?, phone=? WHERE id=?";
		jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getCompany(), contact.getPhone(), contact.getId());
	}

	@Override
	public void insertContact(Contact contact, String username) {
		String sql = "INSERT INTO contact (first_name, last_name, email, company_id, phone, username) VALUES (?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getCompany(), contact.getPhone(), username);
	}
	
	@Override
	public void deleteContact(int contactId) {
		String sql = "DELETE FROM contact WHERE id=?";
		jdbcTemplate.update(sql, contactId);
	}

	@Override
	public List<Contact> listContacts(String username) {
		String sql = "SELECT * FROM contact WHERE username = '" + username + "'";
		List<Contact> listContact = jdbcTemplate.query(sql, new RowMapper<Contact>() {

			@Override
			public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
				Contact aContact = new Contact();
				aContact.setId(rs.getInt("id"));
				aContact.setFirstName(rs.getString("first_name"));				
				aContact.setLastName(rs.getString("last_name"));
				aContact.setEmail(rs.getString("email"));
				aContact.setCompany(rs.getInt("company_id"));
				aContact.setPhone(rs.getString("phone"));
				return aContact;
			}
		});
		Collections.sort(listContact);
		return listContact;
	}

	
	@Override
	public Contact getContact(int contactId) {
		String sql = "SELECT * FROM contact WHERE id=" + contactId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Contact>() {
			@Override
			public Contact extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Contact contact = new Contact();
					contact.setId(rs.getInt("id"));
					contact.setFirstName(rs.getString("first_name"));
					contact.setLastName(rs.getString("last_name"));					
					contact.setEmail(rs.getString("email"));
					contact.setCompany(rs.getInt("company_id"));
					contact.setPhone(rs.getString("phone"));
					return contact;
				}
				return null;
			}
		});
	}

	
	@Override
	public Contact getContact(String firstName, String lastName) {
		String sql = "SELECT contact.first_name, contact.last_name FROM contact WHERE "
				+ "contact.first_name ='" +firstName+ "' AND contact.last_name = '" +lastName+ "'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Contact>() {
			@Override
			public Contact extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					//dummy object just to show row found
					Contact contact = new Contact();
					System.out.println("Contact Duplicated");
					return contact;
				}
				return null;
			}
		});
	}
	
	/* Lists Contacts which are associated with a meeting */
	@Override
	public List<String> getContactsUsed(String username) {
		String sql = "SELECT contact.first_name, contact.last_name FROM contact inner join meeting on meeting.contact_id = contact.id "
				+ "WHERE meeting.username = '" + username + "' GROUP BY contact_id;";
		
		List<String> contactsUsed = jdbcTemplate.query(sql, new RowMapper<String>() {

			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				return rs.getString("first_name") + rs.getString("last_name");
			}
			
		});
		return contactsUsed;
	}
	
	
}
