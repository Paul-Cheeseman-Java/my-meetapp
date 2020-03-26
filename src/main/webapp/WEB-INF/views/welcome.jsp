<%@ include file="common/header.jspf"%>
<%@ include file="common/navSignedIn.jspf"%>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<body>
  <section>
    <div class="container">

		<h1>Welcome ${name}</h1> 
    </div>

  </section>
    <section id="meeting-stats" class="wow fadeIn">
      <div class="container">
        <header class="section-header">
        	<h1 id="welcomeHeader">Welcome ${name}</h1> 
          <h3>Your Meeting Schedule</h3>
        </header>
        
        <div class="dropdown show">
  			<a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Schedule Range</a>
			<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
    			<a class="dropdown-item" href="/MeetApp/welcome?id=1">Today</a>
    			<a class="dropdown-item" href="/MeetApp/welcome?id=7">Next 7 days</a>
    			<a class="dropdown-item" href="/MeetApp/welcome?id=14">Next 14 days</a>
    			<a class="dropdown-item" href="/MeetApp/welcome?id=30">Next 30 days</a>
    			<a class="dropdown-item" href="/MeetApp/welcome?id=0">All</a>
    			<div class="dropdown-divider"></div>
    			<a class="dropdown-item" href="/MeetApp/welcome?id=-1">Yesterday</a>
    			<a class="dropdown-item" href="/MeetApp/welcome?id=-5">Last 5 Days</a>
  			</div>
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
					</tr>
			</c:forEach>
		</tbody>
	</table>
        
        
        
        
        <div class="row counters">
          <div class="col-lg-3 col-6 text-center">
            <span data-toggle="counter-up">${face2FaceMeetings}</span>
            <p>Face to Face</p>
          </div>

          <div class="col-lg-3 col-6 text-center">
            <span data-toggle="counter-up">${vidConfMeetings}</span>
            <p>Video Conferences</p>
          </div>

          <div class="col-lg-3 col-6 text-center">
            <span data-toggle="counter-up">${voiceConfMeetings}</span>
            <p>Voice Conferences</p>
          </div>

          <div class="col-lg-3 col-6 text-center">
            <span data-toggle="counter-up">${face2FaceMeetings+vidConfMeetings+voiceConfMeetings}</span>
            <p>Total</p>
          </div>
  
        </div>

      </div>
    </section>
  </main>

 <%@ include file="common/footer.jspf"%>
