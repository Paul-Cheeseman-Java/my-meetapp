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

    	<div class="form">
            <form:form method="post" commandName="company">
            	<div class="form-row">
                	<div class="form-group offset-md-3 col-md-6">
                    	<form:label path="name" for="name">Name:</form:label>
                    	<form:input path="name" type="text" name="name" class="form-control" id="name" placeholder="Company Name" pattern=".{4,25}" required="required" title="4 to 25 characters"/>
    					<c:if test="${duplicateCompany == true}">
        					<div class="formErrorMsg"> That company is already registered</div>
    					</c:if>
    					
                  	</div>
                </div>
                <div class="form-row">
                	<div class="form-group offset-md-3 col-md-6">
           				<form:label path="companyType">Company Type:</form:label></br>
           				<form:select id = "companyTypeList" path="companyType" class="selectpicker">
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
