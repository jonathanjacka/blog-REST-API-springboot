# blog-REST-API-springboot - UNDER CONSTRUCTION
RESTful API for blog with posts, comments, auth using Java/Spring Boot

### To run: 
Clone the project to your local machine, and install the dependencies.

### Note 1: Requires creation of .env file
- This project uses environment variables. To run correctly, create a .env file in the root directory, and assign the following variables:
```
MYSQL_ROOT_PASSWORD=password1
```
This will be the password assigned to your mysql database that will run in a Docker container.  Your `docker-compose.yml` file is expecting it, 
and your `application.properties` currently requires it.  This password should only be exposed like this while running your database locally.  

### Note 2: Requires Docker
- Install Docker Desktop
- Make sure Docker Desktop is running
- Use Docker compose to start the container that holds your database:
  - In the root directory of the project, run the following command from your terminal: 
  ```
  docker compose up -d
  ```
  - the `-d` runs the container in detached mode, allowing you access to your terminal once the container is running
