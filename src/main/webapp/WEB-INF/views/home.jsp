<%@ include file="common/header.jspf"%>
<%@ include file="common/navSignedOut.jspf"%>
<body>
  <section id="intro" class="clearfix">
    <div class="container">

      <div class="intro-img">
        <img src=<c:url value="/resources/img/intro-img.svg"/> alt="" class="img-fluid">
      </div>

      <div class="intro-info">
        <h2>Managing your<br><span>meetings</span><br>making life easier!</h2>
        <div>
          <a href="#about" class="btn-get-started scrollto">Next 7 Days</a>
          <a href="#services" class="btn-services scrollto">Add a Meeting</a>
        </div>
      </div>

    </div>
  </section><!-- #intro -->


  <main id="main">
    <section id="about">
      <div class="container">

        <div class="row about-container">

          <div class="col-lg-6 content order-lg-1 order-2">
            <p>
              Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
            </p>

            <div class="icon-box wow fadeInUp">
              <div class="icon"><i class="fa fa-address-book-o"></i></div>
              <h4 class="title"><a href="">Store all contacts</a></h4>
              <p class="description">All your contacts stored in one place, linked to all associated previous and future meetings.</p>
            </div>

            <div class="icon-box wow fadeInUp" data-wow-delay="0.2s">
              <div class="icon"><i class="fa fa-handshake-o"></i></div>
              <h4 class="title"><a href="">Seal the deals!</a></h4>
              <p class="description">Never miss a meeting and retain information from them, to staying organised and ahead of the game helping you get those deal done.</p>
            </div>
            <div class="icon-box wow fadeInUp" data-wow-delay="0.4s">
              <div class="icon"><i class="fa fa-pencil-square-o"></i></div>
              <h4 class="title"><a href="">Store information from meetings</a></h4>
              <p class="description">Make and store notes from the meeting you've attended, helping you track progress of problems and accountability of issues.  </p>
            </div>

          </div>

          <div class="col-lg-6 background order-lg-2 order-1 wow fadeInUp">
            <img src=<c:url value="/resources/img/about-extra-2.svg"/> class="img-fluid" alt="">
          </div>
        </div>


      </div>
    </section><!-- #about -->

    <section id="meeting-stats" class="wow fadeIn">
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
