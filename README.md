# Spring Boot REST API: Blog 
RESTful API for blog with posts, comments, authorization/authentication using Java/Spring Boot
---
## *Branch `main` is setup to work with a PostgreSQL database, for deployment to Heroku*

### To run locally: 
   - Clone the project to your local machine, and install the dependencies.

   - Make sure your Docker container with your database is running (see below - *Note 2*)

   - From the command line, navigate to root directory of the project, and enter the following:
```
./mvnw spring-boot:run
```
   - If you have maven locally installed, then run instead:
```
mvn spring-boot:run
```
Alternatively, use Intellij IDEA and simply run the application from within the IDE.

### API documentation:
   - To view the Swagger-UI API documentation once the application is up and running, got to (http://localhost:8080/swagger-ui/index.html)

### Note 1: Requires use of environment variables

  - __Required environment variables:__
     - your `application.properties` file requires the use of the following variables:
        - POSTGRES_USER
        - POSTGRES_PASSWORD
        - JWT_SECRET
      - Either enter these directly into the `application.properties` file by replacing the respective variable name with same plaintext, or associate the variables correctly using a `.env` file.  Alternatively, you can easily [set these variables directly](https://www.jetbrains.com/help/objc/add-environment-variables-and-program-arguments.html) through the Intellij IDEA IDE.
        
   - __Use of .env file for Docker__ 
      - The `docker-compose.yml` file will look for a `.env` as well, specifically for the `POSTGRES_PASSWORD`.  Make sure this file is created in the root directory of the project. This will be the password assigned to your mysql database that will run in a Docker container.
         - __*NOTE:*__ POSTGRES_PASSWORD must match exactly environment variable of the same name in the `application.properties` file.
           
            - This will be the password assigned to your mysql database that will run in a Docker container.  Your `docker-compose.yml` file is expecting it, and your `application.properties` currently requires it.   



### Note 2: Requires Docker
- Install Docker Desktop
- Make sure Docker Desktop is running
- Use Docker compose to start the container that holds your database:
  - In the root directory of the project, run the following command from your terminal: 
  ```
  docker compose up -d
  ```
  - the `-d` runs the container in detached mode, allowing you access to your terminal once the container is running
