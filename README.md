# Spring Security JWT

# Stack
![](https://img.shields.io/badge/java_11-✓-blue.svg)
![](https://img.shields.io/badge/spring_boot-✓-blue.svg)
![](https://img.shields.io/badge/spring_security-✓-blue.svg)
![](https://img.shields.io/badge/jwt-✓-blue.svg)


# How to setup and run
1. Ensure you have [JDK-11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) and [Maven](https://maven.apache.org)

2. Clone this repository

3. Install dependencies and create artifacts
```
$ mvn clean install
```

4. Run the spring boot application
```
$ mvn spring-boot:run
```

5. Send POST request to `/authenticate` endpoint to get JWT token
```
$ curl -X POST http://localhost:8080/authenticate -H 'Content-Type: application/json' -d '{"username": "foo","password": "bar"}'
```

6. Make GET request to `/hello` endpoint with JWT token as Header parameter
```
$ curl -X GET http://localhost:8080/hello -H 'Authorization: Bearer <JWT>' -H 'Content-Type: application/json'
```
