package com.meetapp.meetings.dao;

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

import com.meetapp.meetings.model.Meeting;
import com.meetapp.meetings.model.MeetingType;

@Repository
public class MeetingDAOImpl implements MeetingDAO {

	private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	public MeetingDAOImpl() {
		
	}

	@Override
	public void updateMeeting(Meeting meeting) {
		String sql = "UPDATE Meeting SET contact_id=?, company_id=?, meeting_type=?, notes=?, location=?, meeting_start=?, meeting_end=? WHERE id=?";
		jdbcTemplate.update(sql, meeting.getContact_id(), meeting.getCompany_id(), meeting.getMeeting_type(), meeting.getNotes(), meeting.getLocation(), meeting.getMeeting_start(), meeting.getMeeting_end(), meeting.getId());
	}

	
	@Override
	public void insertMeeting(Meeting meeting, String username) {
		String sql = "INSERT INTO Meeting (contact_id, company_id, meeting_type, notes, meeting_start, meeting_end, username, location) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, meeting.getContact_id(), meeting.getCompany_id(), meeting.getMeeting_type(), meeting.getNotes(), meeting.getMeeting_start(), meeting.getMeeting_end(), username, meeting.getLocation());
	}

	

	@Override
	public void deleteMeeting(int MeetingId) {
		String sql = "DELETE FROM Meeting WHERE id=?";
		jdbcTemplate.update(sql, MeetingId);
	}

	@Override
	public List<Meeting> listMeetings(String username) {
		String sql = "SELECT * FROM Meeting WHERE username = '" + username + "'";
		List<Meeting> listMeeting = jdbcTemplate.query(sql, new RowMapper<Meeting>() {

			@Override
			public Meeting mapRow(ResultSet rs, int rowNum) throws SQLException {
				Meeting meeting = new Meeting();
				meeting.setId(rs.getInt("id"));
				meeting.setCompany_id(rs.getInt("company_id"));
				meeting.setContact_id(rs.getInt("contact_id"));
				meeting.setLocation(rs.getString("location"));
				meeting.setMeeting_type(rs.getInt("meeting_type"));
				meeting.setNotes(rs.getString("notes"));
				meeting.setMeeting_start(Meeting.joinDateTime(rs.getDate("meeting_start"), rs.getTime("meeting_start")));
				meeting.setMeeting_end(Meeting.joinDateTime(rs.getDate("meeting_end"), rs.getTime("meeting_end")));
				return meeting;
			}
		});
		Collections.sort(listMeeting);
		return listMeeting;
	}

	
	@Override
	public List<Meeting> listMeetings(String username, int meetingType) {
		String sql = "SELECT * FROM Meeting WHERE username = '" + username + "' AND meeting_type = '" +meetingType+ "'";
		List<Meeting> listMeeting = jdbcTemplate.query(sql, new RowMapper<Meeting>() {

			@Override
			public Meeting mapRow(ResultSet rs, int rowNum) throws SQLException {
				Meeting meeting = new Meeting();
				meeting.setId(rs.getInt("id"));
				meeting.setCompany_id(rs.getInt("company_id"));
				meeting.setContact_id(rs.getInt("contact_id"));
				meeting.setLocation(rs.getString("location"));
				meeting.setMeeting_type(rs.getInt("meeting_type"));
				meeting.setNotes(rs.getString("notes"));
				meeting.setMeeting_start(Meeting.joinDateTime(rs.getDate("meeting_start"), rs.getTime("meeting_start")));
				meeting.setMeeting_end(Meeting.joinDateTime(rs.getDate("meeting_end"), rs.getTime("meeting_end")));
				return meeting;
			}
		});
		Collections.sort(listMeeting);
		return listMeeting;
	}

	
	
	
	@Override
	public Meeting getMeeting(int meetingId) {
		String sql = "SELECT * FROM Meeting WHERE id=" + meetingId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Meeting>() {
			@Override
			public Meeting extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Meeting meeting = new Meeting();
					meeting.setId(rs.getInt("id"));
					meeting.setContact_id(rs.getInt("contact_id"));
					meeting.setCompany_id(rs.getInt("company_id"));
					meeting.setLocation(rs.getString("location"));
					meeting.setMeeting_type(rs.getInt("meeting_type"));
					meeting.setNotes(rs.getString("notes"));
					meeting.setMeeting_start(Meeting.joinDateTime(rs.getDate("meeting_start"), rs.getTime("meeting_start")));
					meeting.setMeeting_end(Meeting.joinDateTime(rs.getDate("meeting_end"), rs.getTime("meeting_end")));
					return meeting;
				}
				return null;
			}
		});
	}

	@Override
	public List<MeetingType> listMeetingTypes() {
		String sql = "SELECT * FROM meeting_type";
		List<MeetingType> listMeetingTypes = jdbcTemplate.query(sql, new RowMapper<MeetingType>() {

			public MeetingType mapRow(ResultSet rs, int rowNum) throws SQLException {
				MeetingType aMeetingType = new MeetingType();
	
				aMeetingType.setId(rs.getInt("id"));
				aMeetingType.setType(rs.getString("type"));
				
				return aMeetingType;
			}
			
		});
		Collections.sort(listMeetingTypes);		
		return listMeetingTypes;
	}
	

	@Override
	public List<Meeting> listUpcomingMeetings(String username, int range) {
		String sql = "SELECT * FROM Meeting WHERE username = '" + username + "' AND "
				+ "meeting.meeting_start >= CURDATE() AND meeting.meeting_start <= (CURDATE() + INTERVAL " + range + " DAY)";
		
		List<Meeting> listMeeting = jdbcTemplate.query(sql, new RowMapper<Meeting>() {

			@Override
			public Meeting mapRow(ResultSet rs, int rowNum) throws SQLException {
				Meeting meeting = new Meeting();
				meeting.setId(rs.getInt("id"));
				meeting.setCompany_id(rs.getInt("company_id"));
				meeting.setContact_id(rs.getInt("contact_id"));
				meeting.setLocation(rs.getString("location"));
				meeting.setMeeting_type(rs.getInt("meeting_type"));
				meeting.setNotes(rs.getString("notes"));
				meeting.setMeeting_start(Meeting.joinDateTime(rs.getDate("meeting_start"), rs.getTime("meeting_start")));
				meeting.setMeeting_end(Meeting.joinDateTime(rs.getDate("meeting_end"), rs.getTime("meeting_end")));
				return meeting;
			}
		});
		Collections.sort(listMeeting);
		return listMeeting;
	}

	@Override
	public List<Meeting> listUpcomingMeetings(String username) {
		String sql = "SELECT * FROM Meeting WHERE username = '" + username + "' AND "
				+ "meeting.meeting_start >= CURDATE()";
		
		List<Meeting> listMeeting = jdbcTemplate.query(sql, new RowMapper<Meeting>() {

			@Override
			public Meeting mapRow(ResultSet rs, int rowNum) throws SQLException {
				Meeting meeting = new Meeting();
				meeting.setId(rs.getInt("id"));
				meeting.setCompany_id(rs.getInt("company_id"));
				meeting.setContact_id(rs.getInt("contact_id"));
				meeting.setLocation(rs.getString("location"));
				meeting.setMeeting_type(rs.getInt("meeting_type"));
				meeting.setNotes(rs.getString("notes"));
				meeting.setMeeting_start(Meeting.joinDateTime(rs.getDate("meeting_start"), rs.getTime("meeting_start")));
				meeting.setMeeting_end(Meeting.joinDateTime(rs.getDate("meeting_end"), rs.getTime("meeting_end")));
				return meeting;
			}
		});
		Collections.sort(listMeeting);
		return listMeeting;
	}
	
	
	
	@Override
	public List<Meeting> listUpcomingMeetings(String username, int range, int meetingType) {
		String sql = "SELECT * FROM Meeting WHERE username = '" + username + "' AND "
				+ "meeting.meeting_start >= CURDATE() AND meeting_type = " +meetingType + " AND meeting.meeting_start <= (CURDATE() + INTERVAL " + range + " DAY)";
		List<Meeting> listMeeting = jdbcTemplate.query(sql, new RowMapper<Meeting>() {
			@Override
			public Meeting mapRow(ResultSet rs, int rowNum) throws SQLException {
				Meeting meeting = new Meeting();
				meeting.setId(rs.getInt("id"));
				return meeting;
			}
		});
		return listMeeting;
	}
	
	
	
	@Override
	public List<Meeting> listPastMeetings(String username, int range) {
		String sql = "SELECT * FROM Meeting WHERE username = '" + username + "' AND "
				+ "meeting.meeting_start <= CURDATE() AND meeting.meeting_start >= (CURDATE() + INTERVAL " + range + " DAY)";
		
		List<Meeting> listMeeting = jdbcTemplate.query(sql, new RowMapper<Meeting>() {

			@Override
			public Meeting mapRow(ResultSet rs, int rowNum) throws SQLException {
				Meeting meeting = new Meeting();
				meeting.setId(rs.getInt("id"));
				meeting.setCompany_id(rs.getInt("company_id"));
				meeting.setContact_id(rs.getInt("contact_id"));
				meeting.setLocation(rs.getString("location"));
				meeting.setMeeting_type(rs.getInt("meeting_type"));
				meeting.setNotes(rs.getString("notes"));
				meeting.setMeeting_start(Meeting.joinDateTime(rs.getDate("meeting_start"), rs.getTime("meeting_start")));
				meeting.setMeeting_end(Meeting.joinDateTime(rs.getDate("meeting_end"), rs.getTime("meeting_end")));
				return meeting;
			}
		});
		Collections.reverse(listMeeting);
		return listMeeting;
	}


	@Override
	public List<Meeting> listPastMeetings(String username) {
		String sql = "SELECT * FROM Meeting WHERE username = '" + username + "' AND meeting.meeting_start <= CURDATE()";
		
		List<Meeting> listMeeting = jdbcTemplate.query(sql, new RowMapper<Meeting>() {

			@Override
			public Meeting mapRow(ResultSet rs, int rowNum) throws SQLException {
				Meeting meeting = new Meeting();
				meeting.setId(rs.getInt("id"));
				meeting.setCompany_id(rs.getInt("company_id"));
				meeting.setContact_id(rs.getInt("contact_id"));
				meeting.setLocation(rs.getString("location"));
				meeting.setMeeting_type(rs.getInt("meeting_type"));
				meeting.setNotes(rs.getString("notes"));
				meeting.setMeeting_start(Meeting.joinDateTime(rs.getDate("meeting_start"), rs.getTime("meeting_start")));
				meeting.setMeeting_end(Meeting.joinDateTime(rs.getDate("meeting_end"), rs.getTime("meeting_end")));
				return meeting;
			}
		});
		Collections.reverse(listMeeting);
		
		return listMeeting;
	}

	
	
	@Override
	public List<Meeting> listAllPastMeetingsByMeetingType(String username, int meetingType) {
		String sql = "SELECT * FROM Meeting WHERE username = '" + username + "'AND "
				+ "meeting.meeting_start <= CURDATE() AND meeting_type = " +meetingType;
		
		List<Meeting> listMeeting = jdbcTemplate.query(sql, new RowMapper<Meeting>() {
			@Override
			public Meeting mapRow(ResultSet rs, int rowNum) throws SQLException {
				Meeting meeting = new Meeting();
				meeting.setId(rs.getInt("id"));
				meeting.setCompany_id(rs.getInt("company_id"));
				meeting.setContact_id(rs.getInt("contact_id"));
				meeting.setLocation(rs.getString("location"));
				meeting.setMeeting_type(rs.getInt("meeting_type"));
				meeting.setNotes(rs.getString("notes"));
				meeting.setMeeting_start(Meeting.joinDateTime(rs.getDate("meeting_start"), rs.getTime("meeting_start")));
				meeting.setMeeting_end(Meeting.joinDateTime(rs.getDate("meeting_end"), rs.getTime("meeting_end")));
				return meeting;
			}
		});
		Collections.reverse(listMeeting);
		return listMeeting;
	}

	
	
	
	
	
	@Override
	public List<Meeting> listPastMeetings(String username, int range, int meetingType) {
		String sql = "SELECT * FROM Meeting WHERE username = '" + username + "'AND "
				+ "meeting.meeting_start <= CURDATE() AND meeting_type = " +meetingType + "  AND meeting.meeting_start >= (CURDATE() + INTERVAL " + range + " DAY)";
		
		List<Meeting> listMeeting = jdbcTemplate.query(sql, new RowMapper<Meeting>() {
			@Override
			public Meeting mapRow(ResultSet rs, int rowNum) throws SQLException {
				Meeting meeting = new Meeting();
				meeting.setId(rs.getInt("id"));
				meeting.setCompany_id(rs.getInt("company_id"));
				meeting.setContact_id(rs.getInt("contact_id"));
				meeting.setLocation(rs.getString("location"));
				meeting.setMeeting_type(rs.getInt("meeting_type"));
				meeting.setNotes(rs.getString("notes"));
				meeting.setMeeting_start(Meeting.joinDateTime(rs.getDate("meeting_start"), rs.getTime("meeting_start")));
				meeting.setMeeting_end(Meeting.joinDateTime(rs.getDate("meeting_end"), rs.getTime("meeting_end")));
				return meeting;
			}
		});
		Collections.reverse(listMeeting);
		return listMeeting;
	}


	@Override
	public int countAllMeetingsForType(String meetingType) {
		String sql = "SELECT * FROM meeting inner join meeting_type on meeting.meeting_type = "
				+ "meeting_type.id WHERE meeting_type.type = '" + meetingType + "'";
	
		List<Meeting> listMeetings = jdbcTemplate.query(sql, new RowMapper<Meeting>() {
			@Override
			public Meeting mapRow(ResultSet rs, int rowNum) throws SQLException {
				Meeting meeting = new Meeting();
				meeting.setId(rs.getInt("meeting.id"));
				return meeting;
			}
		});
		return listMeetings.size();
	}

	
	
	
	
	
	
}
