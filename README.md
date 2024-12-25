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

OR run it on Docker:
You need 1 mysql & 1 app container to run the app. In the same docker network.

1. mysql:
`docker run --name mysql-crud-app \  
  -e MYSQL_ROOT_PASSWORD=root \  
  -e MYSQL_DATABASE=user_db \  
  -e MYSQL_USER=mysql \  
  -e MYSQL_PASSWORD=root \  
  -v <persistent-volume>:/var/lib/mysql \  
  -p 3306:3306 \  
  -d mysql:latest`
  
2. web app:
`docker pull vikendu/crud-app:1.2.3`
`docker run -d -p 8080:8080 vikendu/crud-app:1.2.3`

OR just `docker compose up`  

create a table for Audit Trace:

`CREATE TABLE user_audits (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    action VARCHAR(50) NOT NULL,
    timestamp DATETIME NOT NULL,
    details TEXT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);`

If everything went right the app should run at http://localhost:8080/users  