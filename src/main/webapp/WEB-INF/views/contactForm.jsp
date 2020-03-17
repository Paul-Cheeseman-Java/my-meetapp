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
            <form method="post" role="form">
            
            	<div class="form-row">
                	<div class="form-group col-md-6">
                		<div class="input-group date" id="datetimepicker1" data-target-input="nearest">
                    		<input type="text" class="form-control datetimepicker-input" data-target="#datetimepicker1"/>
                    		<div class="input-group-append" data-target="#datetimepicker1" data-toggle="datetimepicker">
                        		<div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    		</div>
                		</div>
      				</div>
    			</div>
            </form>
		</div>
  	</div>
  </section>
 <%@ include file="common/footer.jspf"%>
