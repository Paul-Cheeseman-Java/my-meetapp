<%@ include file="common/header.jspf"%>
<%@ include file="common/navSignedOut.jspf"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<body>
  <section id="intro-none-homepage" class="clearfix">
  	<div class="container">
  		<div class="title">
  			<!-- ${title}  -->
  			<h1>Login</h1>
  		</div>
  		
	   	<div class="form text-center">
 			<form action="login" method="post">
       			<div class="form-group">
					<label for="username">Username</label>
   					<input type="text" id="username" name="username"/>
       			</div>
       			<div class="form-group">
            		<label for="lastName">Password:</label>
   					<input type="password" id="password" name="password"/>
       				<c:if test="${param.error != null}">
    					<div class="formErrorMsg">
	    					Invalid username and password
    					</div>
    				</c:if>
       			</div>
	    		<div class="text-center"><button class="btn btn-light" type="submit" >Log In</button></div>
    			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
    		</form>
		</div>
	</div>
 </section>

 <%@ include file="common/footer.jspf"%>