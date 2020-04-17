<%@ include file="common/header.jspf"%>
<%@ include file="common/navSignedOut.jspf"%>
<body>
  <section id="intro" class="clearfix">
    <div class="container">

      <div class="intro-img">
        <img src=<c:url value="/resources/img/intro-img.svg"/> alt="Meetings on a board" class="img-fluid">
      </div>

      <div class="intro-info">
        <h2>Managing your<br><span>meetings</span><br>making life easier!</h2>
        <div>
          <a href="welcome" class="btn-get-started scrollto">Sign In</a>
          <a href="register" class="btn-services scrollto">Sign Up</a>
        </div>
      </div>

    </div>
  </section>


  <main id="main">
    <section id="about">
      <div class="container">

        <div class="row about-container">

          <div class="col-lg-6 content order-lg-1 order-2">
            <p>
              Welcome to MeetApp! A meeting management system that enables you to manage your contacts and the meetings you have with them.   
            </p>

            <div class="icon-box wow fadeInUp">
              <div class="icon"><i class="fa fa-address-book-o"></i></div>
              <h4 class="title"><a href="">Contacts and Meetings in one place</a></h4>
              <p class="description">All your contacts and meetings stored in one place, providing a one stop shop for time management.</p>
            </div>

            <div class="icon-box wow fadeInUp" data-wow-delay="0.2s">
              <div class="icon"><i class="fa fa-pencil-square-o"></i></div>
              <h4 class="title"><a href="">Store information from meetings</a></h4>
              <p class="description">Make and retains notes from the meeting you've attended, helping you track progress of problems and accountability of issues.  </p>
            </div>

            <div class="icon-box wow fadeInUp" data-wow-delay="0.4s">
              <div class="icon"><i class="fa fa-handshake-o"></i></div>
              <h4 class="title"><a href="">Seal the deals!</a></h4>
              <p class="description">MeetApp ensures you stay organised and ahead of the game, helping you perform professionally and get those deal done.</p>
            </div>

          </div>

          <div class="col-lg-6 background order-lg-2 order-1 wow fadeInUp">
            <img src=<c:url value="/resources/img/about-extra-2.svg"/> class="img-fluid" alt="Meetings being made">
          </div>
        </div>


      </div>
    </section>

    <section id="meeting-stats" class="meeting-stats wow fadeIn">
      <div class="container">
        <header class="section-header">
          <h3>Meetings Managed by MeetApp</h3>
        </header>
        <div class="row counters">
          <div class="col-lg-3 col-6 text-center">
            <span data-toggle="counter-up">${allFace2Face}</span>
            <p>Face to Face</p>
          </div>

          <div class="col-lg-3 col-6 text-center">
            <span data-toggle="counter-up">${allVidConf}</span>
            <p>Video Conferences</p>
          </div>

          <div class="col-lg-3 col-6 text-center">
            <span data-toggle="counter-up">${allVoiceConf}</span>
            <p>Voice Conferences</p>
          </div>

          <div class="col-lg-3 col-6 text-center">
            <span data-toggle="counter-up">${total}</span>
            <p>Total</p>
          </div>
  
        </div>

      </div>
    </section>
  </main>

 <%@ include file="common/footer.jspf"%>
