# musikkjulekalender
====================
This is the repo for musikkjulekalender, the annual music contest during christmas. For each day, a new door opens, which contains a music quiz question based on sound. The users guess which song it is.

## Setup/Running the project locally

* Clone the repo
* Install and start Postgres
* See the `databasecreation.txt` for instructions of how to create the database tables.
* Create a file named application.properties - place it under src/main/resources/config. Let it have the following content:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/musikkjulekalender
spring.datasource.username=postgres
spring.datasource.password=INSERT_POSTGRES_PASSWORD
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.generate-ddl=true

logging.level.root=WARN
logging.level.no.saiboten.drumcalendar=DEBUG

keycloak.realm=musikkjulekalender
keycloak.realmKey=INSERT_REAL_KEY
keycloak.auth-server-url=http://mkj.no:8180/auth (example)
keycloak.ssl-required=external
keycloak.resource=musikkjulekalender
keycloak.credentials.secret=INSERT_CREDENTIAL_SECRET
keycloak.use-resource-role-mappings = false

keycloak.securityConstraints[0].securityCollections[1].name = admin stuff
keycloak.securityConstraints[0].securityCollections[1].authRoles[0] = admin
keycloak.securityConstraints[0].securityCollections[1].patterns[0] = /admin*

# Below is for local development
spring.resources.chain.enabled=false
spring.resources.chain.cache=false
spring.resources.cache-period=0
spring.cache.type=none
spring.devtools.livereload.enabled=true
spring.devtools.livereload.port=35729
```

* mvn sprint-boot:run
* Open localhost:8080
* To log in - you need a Keycloak server set up correctly.
  * Download Keycloak 2.2.1
  * Run bin/standalone.sh (or .bat for windows) -Djboss.socket.binding.port-offset=100
    * This will launch keycloak on port 8180.
  * Open localhost:8180 - Go to admin panel, create admin user.
  * Create realm "musikkjulekalender". Click through settings and set things up!

## Technology

Front end:
* React
* Thymeleaf for html rendering
* Gulp
* browserify
* bootstrap

Back end:
* Spring MVC
* Postgres
* Spring Boot
* Spring Security
* Keycloak Client Adapters

External
* Keycloak

## Plans ahead
* Switch to webpack
* Remove bootstrap - create all CSS some other way.

## Contributing

Feel free to send pull requests!