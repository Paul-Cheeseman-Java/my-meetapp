# MEETAPP

##### General info
This project a basic meeting management application which allows users to track/manage meetings (over 15 minutes) with contacts from different companies. It's been written using Java Spring (MVC), Bootstrap, CSS and MySQL, and it was developed to help me understand some of the fundamentals in developing and deploying a Spring MVC application.  

The site can be found on Heroku at https://demo-meetapp.herokuapp.com 

A dummy account is setup (with both username/pw as Test) which has associated data loaded to enable the sites functionality to seen without the need for any data entry. 

Once logged in, the displayed meeting schedule can be altered using the 'Schedule Range' dropdown button. Also a user can add new (or remove) companies, contacts and meetings.

Please be aware that the site may be slow to start and respond due to the hosting plan it is on.

##### Features
* The site is themed and device responsive.
* The site has functionality which shows users when it is safe to delete contacts and companies.
* The user interface is dynamic, the contacts/company drop-down menus are driven by user data.
* The homepage site stats reflect the entire dataset, a logged in users stats only reflects their specific data.
* The meetings (and stats) can be filtered based on various time ranges.
* The site uses Spring Security.

##### Technologies
Project is created with:
* Java
* Java Spring (MVC)
* Java JSP
* MySQL
* Bootstrap
* HTML/CSS

##### Credits
I built this application using the following resources which were developed by other people:
* [Tempusdominus](https://tempusdominus.github.io/bootstrap-4/) - JavaScript Date/Time picker.
* [Bootstrapmade](https://bootstrapmade.com/) - Bootstrap theme site.
* [Webapp Runner](https://github.com/heroku/webapp-runner) - Enables easier deployment of Java Web Applications to Heroku.