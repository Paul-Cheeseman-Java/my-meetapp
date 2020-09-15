package com.meetapp.meetings.model;

import java.sql.Time;
import java.text.DateFormat;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

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
	
	//Sort date/time so date is descending and time ascending within a given date range
	//That will mean most recent day entries at top and those entries ordered with soonest meeting first
	public int compareTo(Meeting meeting) {
        //LocalDateTime dateTime1 = this.meeting_start;
        //LocalDateTime dateTime2 = meeting.meeting_start;
        int compRes;
        
        LocalDate date1 = this.meeting_start.toLocalDate();
        LocalDate date2 = meeting.meeting_start.toLocalDate();
        
        LocalTime time1 = this.meeting_start.toLocalTime();
        LocalTime time2 = meeting.meeting_start.toLocalTime();        

        compRes = date1.compareTo(date2);
        if (compRes == 0) {
        	compRes = time1.compareTo(time2);
        }
        
        return compRes;
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
		
		System.out.println("Initial inputTime : " +time);
		
		LocalDate localDate = date.toLocalDate();

		
		//Daylight saving hack
		//if date before 25/10/20 do below, else don't
		LocalTime localTime = time.toLocalTime();
		//LocalDate DLSSwitch = LocalDate.of(2020, 10, 25);
		//if (localDate.isBefore(DLSSwitch)) {
		//	localTime = time.toLocalTime().minusHours(1);			
		//}
		
		//Use below when non-daylight saving 
		//LocalTime localTime = time.toLocalTime();
		String inputDate = localDate.toString();
		String inputDateWithSpace = inputDate.concat(" ");
		String inputTime = localTime.toString();
		String dateTime = inputDateWithSpace.concat(inputTime);

		System.out.println("Change to LocalTime: " +inputTime);
		
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		
		
				
		System.out.println("Returned Date: " +LocalDateTime.parse(dateTime, myFormatObj).toString());
		
		
		
		return LocalDateTime.parse(dateTime, myFormatObj);
	}
	
	
}
