<%@ include file="common/header.jspf"%>
<%@ include file="common/navSignedIn.jspf"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--  Below is for for the command bean interaction -->
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<body>
  <section id="intro-none-homepage" class="clearfix">
  	<div class="container">
  		<div class="title">
  			Contact List 
  		</div>

	<table class="table table-striped">
		<thead>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th class="d-none d-sm-table-cell">Email</th>
				<th class="d-none d-md-table-cell">Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${contactList}" var="contact">
				<tr>
					<td>${contact.firstName}</td>
					<td>${contact.lastName}</td>
					<!-- 
					<td><fmt:formatDate pattern="dd/MM/yyyy"
							value="${todo.targetDate}" /></td>
					-->
					<td class="d-none d-sm-table-cell">${contact.email}</td>
					<td class="d-none d-md-table-cell"><a type="button" class="btn btn-warning"
						href="/MeetApp/editContact?id=${contact.id}">Edit</a> <a type="button"
						class="btn btn-danger" href="/MeetApp/deleteContact?id=${contact.id}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
  	<div class="d-none d-md-table-cell">
		<a type="button" class="btn btn-success" href="/MeetApp/newContact">Add</a>
	</div>
  	</div>
  </section>
 <%@ include file="common/footer.jspf"%>
