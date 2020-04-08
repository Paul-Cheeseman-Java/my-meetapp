<%@ include file="common/header.jspf"%>
<%@ include file="common/navSignedIn.jspf"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--  Below is for for the join function -->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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


					<td class="d-none d-md-table-cell">
						<a type="button" class="btn btn-warning"	href="editContact?id=${contact.id}">Edit</a>

						<c:set var="contains" value="false" />
						<c:forEach  items="${contactsUsed}" var="usedContact">
							<!-- If the concatenated first/last name of the contact is not associated 
							     with any meetings, show green, else show red -->
							<c:set var = "joinedContactName" value = "${contact.firstName}${contact.lastName}" />
  							<c:if test="${usedContact eq joinedContactName}">
    							<c:set var="contains" value="true" />
  							</c:if>
						</c:forEach>
						<c:choose>
							<c:when test="${contains eq true}">
								<a type="button"class="btn btn-danger" href="deleteContact?id=${contact.id}">Delete</a>
							</c:when>
       						<c:otherwise>
								<a type="button"class="btn btn-success" href="deleteContact?id=${contact.id}">Delete</a>
       						</c:otherwise>
						</c:choose>
						
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
  	<div class="d-none d-md-table-cell">
		<a type="button" class="btn btn-success" href="newContact">Add</a>
	</div>
  	</div>
  </section>
 <%@ include file="common/footer.jspf"%>
