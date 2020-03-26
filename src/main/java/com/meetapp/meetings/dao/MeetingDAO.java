package com.meetapp.meetings.dao;

import java.util.List;

import com.meetapp.meetings.model.Meeting;
import com.meetapp.meetings.model.MeetingType;


public interface MeetingDAO {
	
	public void updateMeeting(Meeting Meeting);
	
	public void insertMeeting(Meeting Meeting, String username);
	
	public void deleteMeeting(int MeetingId);
	
	public Meeting getMeeting(int MeetingId);
	
	public List<MeetingType> listMeetingTypes();
	
	public List<Meeting> listMeetings(String username);

	public int listMeetings(String username, int range);
	
	public List<Meeting> listUpcomingMeetings(String username, int range);
	
	public List<Meeting> listPastMeetings(String username, int range);
	
	public int listUpcomingMeetings(String username, int range, int meetype);
	
	public int listPastMeetings(String username, int range, int meetype);
	

	
}
