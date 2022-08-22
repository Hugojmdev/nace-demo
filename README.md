# nace-demo



This is a sample Java / Maven / Spring Boot application to create a microservice 

## About the Service

The service is just a simple example of spring boot microservice that contains some REST endpoints examples. 
It uses a MySQL database to store the data. To be able to run the microservice properly you should run some steps previously.  

## MySQL steps 
- It's required to run the queries in the sql file: ```nace_demo_queries.sql```  located in ```resources``` folder.
- Make sure to change the path of you sample data csv file to your current sample data file.

## How to Run 
```
  mvn clean compile

  mvn spring-boot:run 
```


Once the application runs you should see something like this

```
2022-08-22 10:27:26.343  INFO 71200 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-08-22 10:27:26.350  INFO 71200 --- [           main] com.hgojm.nacedemo.NaceDemoApplication   : Started NaceDemoApplication in 3.956 seconds (JVM running for 4.282)
```

Then, it is ready to be used. To view and use the API you can access the following link: http://localhost:8080/swagger-ui/index.html. 
Or if you prefer you can use a postman collection called ```nace-demo.postman_collection.json``` located in the ```resources``` folder.
