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
					<td><javatime:format value="${meeting.meeting_start}" pattern="HH:mm" /></td>
					<td><javatime:format value="${meeting.meeting_end}" pattern="HH:mm" /></td>
					<td>${meeting.contact_firstName}&#160;${meeting.contact_lastName}</td>
					<td>${meeting.company_name}</td>
					<td>${meeting.location}</td>
					<td><a class="btn btn-light" href="editMeeting?id=${meeting.id}">Edit</a> 
						<a class="btn btn-warning" href="deleteMeeting?id=${meeting.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
  	<div class="d-none d-md-table-cell">
		<a class="btn btn-light" href="newMeeting">Add</a>
	</div>

  	</div>
  </section>
 <%@ include file="common/footer.jspf"%>
