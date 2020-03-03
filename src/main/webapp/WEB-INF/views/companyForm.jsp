<%@ include file="common/header.jspf"%>
<%@ include file="common/navSignedIn.jspf"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--  Below is for for the command bean interaction -->
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<body>
  <section id="intro-none-homepage" class="clearfix">
  	<div class="container">
  		<div class="title">
  			${title}
  		</div>


			<c:forEach items="${companyList}" var="company">
				<tr>
					<td>${company.name}</td>
					<td>${company.companyType}</td>
					<td class="d-none d-md-table-cell"><a type="button" class="btn btn-warning"
						href="/update-company?id=${company.id}">Edit</a> <a type="button"
						class="btn btn-danger" href="/delete-company?id=${company.id}">Delete</a>
					</td>
				</tr>
			</c:forEach>
 		
  		
    	<div class="form">
        	<div id="errormessage"></div>
            <form:form method="post" role="form" commandName="company">
            	<div class="form-row">
                	<div class="form-group col-md-6">
                    	<form:label path="name" for="name">Name:</form:label>
                    	<form:input path="name" type="text" name="name" class="form-control" id="name" placeholder="Company Name" data-rule="minlen:4" data-msg="Please enter at least 4 chars" maxlength="15"/>
                    	<div class="validation"></div>
                  	</div>
                </div>
                <div class="form-row">
                	<div class="form-group col-md-6">
             			<label>Select Company Type:</label>
             			<div>
             				<form:select id = "companyTypeList" path="companyType">
             				<c:forEach items="${companyTypesList}" var="theCompanyType">
												
								<!-- Setting the company to correct value on dropdown -->
             					<c:set var = "currentType" scope = "session" value = "${theCompanyType.id}"/>
             					<c:set var = "companyType" scope = "session" value = "${company.companyType}"/>
             												
             					<c:choose>
	           						<c:when test="${currentType==companyType}">
               							<option selected value = "${theCompanyType.id}">${theCompanyType.type}</option>            						
            						</c:when>
            				        <c:otherwise>
            				            <option value = "${theCompanyType.id}">${theCompanyType.type}</option>            						
            				        </c:otherwise>
                				</c:choose>
              				</c:forEach>
             				</form:select>
             			</div>  		
                	</div>
               	</div>
                <div class="text-center"><button class="btn btn-light" type="submit" title="${buttontext}">${buttontext}</button></div>
            </form:form>
		</div>
  	</div>
  </section>
 <%@ include file="common/footer.jspf"%>
