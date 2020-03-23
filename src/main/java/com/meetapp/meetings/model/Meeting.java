package com.meetapp.meetings.model;

import java.sql.Time;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


import org.springframework.format.annotation.DateTimeFormat;

import com.meetapp.company.model.Company;

public class Meeting  implements Comparable<Meeting> {

	private int id;
	private int contact_id;
	private int company_id;
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

	/*
	public void joinDateTimeMeetingStart(Date date, Time time) {
		String inputDate = date.toString();
		String inputDateWithSpace = inputDate.concat(" ");
		String inputTime = time.toString();
		String inputTimeNoMilli = inputTime.substring(0, inputTime.length() - 3);
		String dateTime = inputDateWithSpace.concat(inputTimeNoMilli);
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		this.setMeeting_start(LocalDateTime.parse(dateTime, myFormatObj));
	}
	*/
	
	public static LocalDateTime joinDateTime(Date date, Time time) {
		LocalDate localDate = date.toLocalDate();
		LocalTime localTime = time.toLocalTime();
		
		String inputDate = localDate.toString();
		String inputDateWithSpace = inputDate.concat(" ");
		String inputTime = localTime.toString();
		//String inputTimeNoMilli = inputTime.substring(0, inputTime.length() - 3);
		//String dateTime = inputDateWithSpace.concat(inputTimeNoMilli);
		String dateTime = inputDateWithSpace.concat(inputTime);
		
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		System.out.println("DateTime: " + LocalDateTime.parse(dateTime, myFormatObj));
		return LocalDateTime.parse(dateTime, myFormatObj);
	}
	
	
	/*
	public void joinDateTimeMeetingEnd(Date date, Time time) {
		
	}
	
	
	
	
	
	public static LocalDateTime convertStringToDate(String date) {
		//"13/03/2020, 20:14"
		String[] part = date.split(",");
		date = part[0]; 		
		part = date.split(" ");
		String time = part[1]; 
		
		//String str = "2016-03-04 11:30";
		String timeDate = date.concat(time);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(timeDate, formatter);
		return dateTime;
		
	}
	
	
	public static String convertDatetoBlackSlash(LocalDate date) {
		String dateStr = date.toString().replace('-','/');
		return dateStr;
	}
	*/

	
}
