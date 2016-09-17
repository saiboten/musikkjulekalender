# musikkjulekalender
====================
This is the repo for musikkjulekalender, the annual music contest during christmas. For each day, a new door opens, which contains a music quiz question based on sound. The users guess which song it is.

## Setup/Running the project locally

* Clone the repo
* Install MongoDB (latest confirmed working version 2.6.4)
* Start MongoDB
* Create a file named application.properties - place it under src/main/resources/config. Let it have the following content:

`admin.username=admin
admin.password=YOUR PASSWORD HERE
facebook.secret=facebook secret
spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp`
* mvn sprint-boot:run
* Open localhost:8080
* To access admin pages to add new quiz questions, go to: localhost:8080/admin - username and password is found in application.properties.

## Technology

Front end:
* React
* JSP for html rendering
* Gulp
* browserify
* bootstrap

Back end:
* Spring MVC
* MongoDB
* Spring Boot
* Spring Security


## Contributing

Feel free to send pull requests!