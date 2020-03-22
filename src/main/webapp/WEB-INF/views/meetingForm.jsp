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
            <form:form method="post" role="form" commandName="meeting">
            
            	<div class="form-row">
                	<div class="form-group col-md-3">
                		<form:label path="meeting_start" for="meeting_start">Meeting Start: </form:label>
                		<div class="input-group date" id="datetimepicker1" data-target-input="nearest">
	                    	<form:input path="meeting_start" type="text" pattern="^([1-9]|([012][0-9])|(3[01]))\/([0]{0,1}[1-9]|1[012])\/\d\d\d\d [012]{0,1}[0-9]:[0-6][0-9]$" name="meeting_start" class="form-control datetimepicker-input" id="meeting_start"  data-target="#datetimepicker1" placeholder="dd/mm/yyyy hh:mm" required="required"/>
    	                	<form:errors path="meeting_start" class="formErrorMsg" />
        	            	<div class="validation"></div>
                    		<div class="input-group-append" data-target="#datetimepicker1" data-toggle="datetimepicker">
                        		<div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    		</div>
                		</div>
      				</div>

               	<div class="form-group col-md-6">
             			<label>Select Company:</label>
             			<c:choose>
    						<c:when test="${empty companiesList}">
								<div class="formErrorMsg"> Please add a company before adding meeting</div>
					    	</c:when>    
    						<c:otherwise>
             					<div>
             						<form:select id = "companyDropdownList" path="company_id">
             						<c:forEach items="${companiesList}" var="company">
               							<option value = "${company.id}">${company.name}</option>
              						</c:forEach>
             						</form:select>
             					</div>  							
					    	</c:otherwise>
						</c:choose>
                	</div>
               </div>
               
               
            	<div class="form-row">
                	<div class="form-group col-md-3">
                		<form:label path="meeting_end" for="meeting_end">Meeting End: </form:label>
                		<div class="input-group date" id="datetimepicker2" data-target-input="nearest">
	                    	<form:input path="meeting_end" type="text" pattern="^([1-9]|([012][0-9])|(3[01]))\/([0]{0,1}[1-9]|1[012])\/\d\d\d\d [012]{0,1}[0-9]:[0-6][0-9]$" name="meeting_end" class="form-control datetimepicker-input" id="meeting_end"  data-target="#datetimepicker2" placeholder="dd/mm/yyyy hh:mm" required="required"/>
    	                	<form:errors path="meeting_end" class="formErrorMsg" />
        	            	<div class="validation"></div>
                    		<div class="input-group-append" data-target="#datetimepicker2" data-toggle="datetimepicker">
                        		<div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    		</div>
                		</div>
      				</div>
      				
      				<div class="form-group col-md-6">
             			<label>Select Contact:</label>
             			<c:choose>
    						<c:when test="${empty contactsList}">
								<div class="formErrorMsg"> Please add a contact before adding a meeting</div>
					    	</c:when>    
    						<c:otherwise>
             					<div>
             						<form:select id = "contactDropdownList" path="contact_id">
             						<c:forEach items="${contactsList}" var="contact">
               							<option value = "${contact.id}">${contact.firstName}&#160;${contact.lastName}</option>
              						</c:forEach>
             						</form:select>
             					</div>  							
					    	</c:otherwise>
						</c:choose>
                	</div>
               </div>
               
                <div class="text-center"><button class="btn btn-light <c:if test="${(empty companiesList || empty contactsList)}">disabled</c:if>" type="submit" title="${buttontext}">${buttontext}</button></div>
               
            </form:form>		





		</div>
  	</div>
  </section>
 <%@ include file="common/footer.jspf"%>
