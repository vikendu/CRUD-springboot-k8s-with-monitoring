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

OR just kubectl apply -f k8s/everything.yaml to get it up & running in a cluster.
Note: if deploying on a k8s cluster make sure to have kafka setup(Strimzi is cool)[https://strimzi.io/quickstarts/]

create a table for Audit Trace:

If everything went right the app should run at http://localhost:8080/users  