package com.meetapp.meetings.model;

import java.sql.Time;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.springframework.format.annotation.DateTimeFormat;

public class Meeting  implements Comparable<Meeting> {

	private int id;
	private int contact_id;
	private int company_id;
	private String contact_firstName;
	private String contact_lastName;
	private String company_name;
	private String location;
	private int meeting_type;
	private String notes;
	
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm") private LocalDateTime meeting_start;
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm") private LocalDateTime meeting_end;	

	
	//Sort date/time so most recent meeting is at top 
	public int compareTo(Meeting meeting) {
        LocalDateTime dateTime1 = this.meeting_start;
        LocalDateTime dateTime2 = meeting.meeting_start;
        return dateTime1.compareTo(dateTime2);
	}
	
	
	public Meeting() {
		
	}

	
	public Meeting(int id, int contact_id, int meeting_type, String notes, LocalDateTime meeting_start, LocalDateTime meeting_end) {
		
	}	
	
	
	public Meeting(String contact_name, String contact_firstName, String contact_lastName, String location, String company_name, int meeting_type, String notes, LocalDateTime meeting_start, LocalDateTime meeting_end) {
		this.contact_firstName = contact_firstName;
		this.contact_lastName = contact_lastName;
		this.location = location;
		this.company_name = company_name;
		this.meeting_type = meeting_type;
		this.notes = notes;
		this.meeting_start = meeting_start;
		this.meeting_start = meeting_start;
	}	
	
	
	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}

	
	public String getContact_firstName() {
		return contact_firstName;
	}


	public void setContact_firstName(String contact_firstName) {
		this.contact_firstName = contact_firstName;
	}

	public String getContact_lastName() {
		return contact_lastName;
	}


	public void setContact_lastName(String contact_lastName) {
		this.contact_lastName = contact_lastName;
	}


	public String getCompany_name() {
		return company_name;
	}

	
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getContact_id() {
		return contact_id;
	}


	public void setContact_id(int contact_id) {
		this.contact_id = contact_id;
	}


	public int getCompany_id() {
		return company_id;
	}


	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}


	public int getMeeting_type() {
		return meeting_type;
	}


	public void setMeeting_type(int meeting_type) {
		this.meeting_type = meeting_type;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public LocalDateTime getMeeting_start() {
		return meeting_start;
	}


	public void setMeeting_start(LocalDateTime meeting_start) {
		this.meeting_start = meeting_start;
	}


	public LocalDateTime getMeeting_end() {
		return meeting_end;
	}


	public void setMeeting_end(LocalDateTime meeting_end) {
		this.meeting_end = meeting_end;
	}

	
	public static LocalDateTime joinDateTime(Date date, Time time) {
		LocalDate localDate = date.toLocalDate();
		LocalTime localTime = time.toLocalTime();
		
		String inputDate = localDate.toString();
		String inputDateWithSpace = inputDate.concat(" ");
		String inputTime = localTime.toString();
		String dateTime = inputDateWithSpace.concat(inputTime);
		
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return LocalDateTime.parse(dateTime, myFormatObj);
	}
	
	
}
