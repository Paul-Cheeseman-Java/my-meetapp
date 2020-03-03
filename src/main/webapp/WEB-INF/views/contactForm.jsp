<%@ include file="common/header.jspf"%>
<%@ include file="common/navSignedIn.jspf"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--  Below is for for the command bean interaction -->
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<body>
  <section id="intro-none-homepage" class="clearfix">
  	<div class="container">
  		<div class="title">
  			${title}
  		</div>

 		
    	<div class="form">
        	<div id="errormessage"></div>
            <form:form method="post" role="form" commandName="contact">
            	<div class="form-row">
                	<div class="form-group col-md-6">
                    	<form:label path="firstName" for="firstname">First Name:</form:label>
                    	<form:input path="firstName" type="text" name="firstName" class="form-control" id="firstName" placeholder="Contacts First Name" data-rule="minlen:4" data-msg="Please enter at least 4 chars" maxlength="15"/>
                    	<form:errors path="firstName" class="formErrorMsg" />
                    	<div class="validation"></div>
                  	</div>
                  	<div class="form-group col-md-6">
                    	<form:label path="lastName" for="lastName">Last Name:</form:label>
                    	<form:input path="lastName" type="text" name="lastName" class="form-control" id="lastName" placeholder="Contacts Last Name" data-rule="minlen:4" data-msg="Please enter at least 4 chars" maxlength="25" />
                	    <div class="validation"></div>
                	</div>
           		</div>
                <div class="form-row">
                	<div class="form-group col-md-6">
                	    <form:label path="email" for="email">Email:</form:label>
                    	<form:input path="email" type="email" class="form-control" name="email" id="email" placeholder="Contacts Email" data-rule="email" data-msg="Please enter a valid email" maxlength="30" />
                    	<div class="validation"></div>
                	</div>
                	<div class="form-group col-md-6">
                	    <form:label path="phone" for="phone">Phone:</form:label>
                  		<form:input path="phone" type="text" class="form-control" name="phone" id="phone" placeholder="Contacts Phone No." data-rule="minlen:11" data-msg="Please enter at least 11 digits"  maxlength="15"/>
                  		<div class="validation"></div>
                	</div>
                </div>
                <div class="form-row">
                	<div class="form-group col-md-6">
             			<label>Select Company:</label>
             			
             			
             			<c:choose>
    						<c:when test="${empty companiesList}">
								<div class="formErrorMsg"> Please add a company before adding contact</div>
					    	</c:when>    
    						<c:otherwise>
             					<div>
             						<form:select id = "companyDropdownList" path="company">
             						<c:forEach items="${companiesList}" var="company">
               							<option value = "${company.id}">${company.name}</option>
              						</c:forEach>
             						</form:select>
             					</div>  							
					    	</c:otherwise>
						</c:choose>
                	</div>
               	</div>
                <div class="text-center"><button class="btn btn-light <c:if test="${empty companiesList}">disabled</c:if>" type="submit" title="${buttontext}">${buttontext}</button></div>
            </form:form>
		</div>
  	</div>
  </section>
 <%@ include file="common/footer.jspf"%>
