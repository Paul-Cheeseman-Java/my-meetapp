<%@ include file="common/header.jspf"%>
<%@ include file="common/navSignedIn.jspf"%>
<body>
  <section>
    <div class="container">

		<h1>Welcome ${name}. You are now authenticated.</h1> 
    </div>

  </section>
    <section id="meeting-stats" class="wow fadeIn">
      <div class="container">
        <header class="section-header">
        	<h1>Welcome ${name}. You are now authenticated.</h1> 
          <h3>Your Meeting Stats</h3>
        </header>
        <div class="row counters">
          <div class="col-lg-3 col-6 text-center">
            <span data-toggle="counter-up">274</span>
            <p>Clients</p>
          </div>

          <div class="col-lg-3 col-6 text-center">
            <span data-toggle="counter-up">421</span>
            <p>Projects</p>
          </div>

          <div class="col-lg-3 col-6 text-center">
            <span data-toggle="counter-up">1,364</span>
            <p>Hours Of Support</p>
          </div>

          <div class="col-lg-3 col-6 text-center">
            <span data-toggle="counter-up">18</span>
            <p>Hard Workers</p>
          </div>
  
        </div>

      </div>
    </section>
  </main>

 <%@ include file="common/footer.jspf"%>
