package com.meetapp.meetings.model;

public class MeetingType implements Comparable<MeetingType> {

	private int id;
	private String type;

	public MeetingType() {
	}

	public int compareTo(MeetingType meetingType) {
        String type1 = this.type;
        String type2 = meetingType.type;
        return type1.compareTo(type2);
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
