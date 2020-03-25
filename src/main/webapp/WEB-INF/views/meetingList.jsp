<%@ include file="common/header.jspf"%>
<%@ include file="common/navSignedIn.jspf"%>

<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--  Below is for for the command bean interaction -->
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<body>
  <section id="intro-none-homepage" class="clearfix">
  	<div class="container">
  		<div class="title">
  			Meeting List
  		</div>
	<div class=generalText>
		When delete is red, it means that it still has contacts and/or meetings associated with it. Deleting the company now will remove
		all contacts and/or meetings associated with that company. When delete is green it means there are
		no associated records. 
	</div>		
	<table class="table table-striped">
		<thead>
			<tr id="whitTableHeading">
				<th>Date</th>
				<th>Start</th>
				<th>End</th>
				<th>Contact</th>
				<th>Company</th>
				<th>Location</th>
				<th class="d-none d-md-table-cell">Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${meetingList}" var="meeting">
				<c:set var="contains" value="false" />

				<tr>
					<td><javatime:format value="${meeting.meeting_start}" pattern="dd-MM-yyyy" /></td>
					<td><javatime:format value="${meeting.meeting_start}" pattern="hh:mm" /></td>
					<td><javatime:format value="${meeting.meeting_end}" pattern="hh:mm" /></td>
					<td>${meeting.contact_firstName}&#160;${meeting.contact_lastName}</td>
					<td>${meeting.company_name}</td>
					<td>${meeting.location}</td>
					<td><a type="button" class="btn btn-warning" href="/MeetApp/editMeeting?id=${meeting.id}">Edit</a> 
						<a type="button"class="btn btn-success" href="/MeetApp/deleteMeeting?id=${meeting.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
  	<div class="d-none d-md-table-cell">
		<a type="button" class="btn btn-success" href="/MeetApp/newMeeting">Add</a>
	</div>

  	</div>
  </section>
 <%@ include file="common/footer.jspf"%>
