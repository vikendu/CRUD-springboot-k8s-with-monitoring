To build app:
`mvn clean install`
`mvn spring-boot:run`

To build JAR:
`mvn package`
(build created in target dir)

To run:
`mvn spring:run`

To run JAR:
`java -jar target/crud-0.0.1-SNAPSHOT.jar`

just `docker compose up` to get mysql up & running

If everything went right the app should run at http://localhost:8080/users