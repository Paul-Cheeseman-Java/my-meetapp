<%@ include file="common/header.jspf"%>
<%@ include file="common/navSignedIn.jspf"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--  Below is for for the command bean interaction -->
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<body>
  <section id="intro-none-homepage" class="clearfix">
  	<div class="container">
  		<div class="title">
  			Company List
  		</div>
	<div class="generalText d-none d-md-table-cell"">
		When delete is red it means that the company still has contacts (and maybe meetings) associated with it, deleting the company will remove all contacts (and meetings) associated with that company. When delete is orange it means there are no associated records so only the company will be deleted. 
	</div>		
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Name</th>
				<th>Type</th>
				<th class="d-none d-md-table-cell">Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${companyList}" var="company">
				<c:set var="contains" value="false" />

				<tr>
					<td>${company.name}</td>
					<td>${company.companyTypeStr}</td>
					<td class="d-none d-md-table-cell">
						<a class="btn btn-light"	href="editCompany?id=${company.id}">Edit</a> 
						<c:forEach  items="${companiesUsed}" var="usedComp">
  							<c:if test="${usedComp eq company.name}">
    							<c:set var="contains" value="true" />
  							</c:if>
						</c:forEach>
						<c:choose>
							<c:when test="${contains eq true}">
								<a class="btn btn-danger" href="deleteCompany?id=${company.id}">Delete</a>
   							</c:when>
        					<c:otherwise>
								<a class="btn btn-warning" href="deleteCompany?id=${company.id}">Delete</a>
        					</c:otherwise>
   						</c:choose>

					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
  	<div class="d-none d-md-table-cell">
		<a class="btn btn-success" href="newCompany">Add</a>
	</div>

  	</div>
  </section>
 <%@ include file="common/footer.jspf"%>
