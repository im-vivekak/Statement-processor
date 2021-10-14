# Customer statement processor - Rabo report pro

### Introduction
This is a spring boot application used to validate customer statement using rest api.

API - request `POST /statement/api/validate` (Used for validating the statement)

API docs - `http://localhost:8080/swagger-ui/#/`

Health check - `http://localhost:8080/actuator/health`

### How to run ?

1. Use `mvn clean install` command to clean & build the package
2. Use `mvn spring-boot:run` command to run the application from terminal

### How to test ?
Using postman- Example curl request given below 

            curl --location 
            --request POST 'http://localhost:8080/statement/api/validate' 
            --header 'api-key: ItsTheApikeyTOConsumeThevalidateService' \
            --form 'file=@"/Users/vivek/Documents/personal/assesment/rabo/tryout/records.xml"
    
You can upload the file as mutltipart from data with name `file`.

### Improvements?
1. Add proper authentication to the API.
2. Add integration test suite.

