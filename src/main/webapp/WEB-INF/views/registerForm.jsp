<%@ include file="common/header.jspf"%>
<%@ include file="common/navSignedOut.jspf"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<body>
  <section id="intro-none-homepage" class="clearfix">
  	<div class="container">
  		<div class="title">
  			<!-- ${title}  -->
  			<h1>Registration</h1>
  		</div>
  		
	   	<div class="form text-center">
 			<form action="register" method="post">
       			<div class="form-group">
					<label for="username">Username</label>
   					<input type="text" id="username" name="username" pattern=".{4,20}" required="required" title="4 to 20 characters"/>
	       			<c:if test="${error != null}">
    					<div class="formErrorMsg">
	    					Username already taken
    					</div>
    				</c:if>
       			</div>
       			<div class="form-group">
            		<label for="lastName">Password:</label>
   					<input type="password" id="password" name="password" pattern=".{4,20}" required title="4 to 20 characters"/>
       			</div>
	    		<div class="text-center"><button class="btn btn-light" type="submit" >Register</button></div>
	    		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
    		</form>
		</div>
	</div>
 </section>

 <%@ include file="common/footer.jspf"%>