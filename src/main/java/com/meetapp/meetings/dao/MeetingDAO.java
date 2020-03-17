package com.meetapp.meetings.dao;

import java.util.List;

import com.meetapp.meetings.model.Meeting;


public interface MeetingDAO {
	
	public void updateMeeting(Meeting Meeting);
	
	//Testing version of function
	public void insertMeeting(Meeting Meeting);
	
	public void insertMeeting(Meeting Meeting, String username);
	
	public void deleteMeeting(int MeetingId);
	
	public Meeting getMeeting(int MeetingId);
	
	public List<Meeting> listMeetings(String username);
}
