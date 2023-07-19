
# IBM Test Project

This is a demo project for a selection process at IBM. The project consists of a REST API for managing candidates in a hiring process.
## Technologies used

- Java 17
- Spring Boot
- Maven


## How to Run the Project

To run the project locally, follow the steps below:

1. Make sure you have Java 17 and Mave installed on your system.
2. Clone the repository:

```bash
  git clone https://github.com/BaldezPatrick/IBMterceirotest.git
```
3. Navigate to the project directory.
4. Build the project using Maven:
```bash
    mvn clean install 
```
5. Run the application:
```bash
    mvn spring-boot:run 
```
## How to Use the API

The API provides endpoints to manage candidates in the hiring process. Below are the available endpoints.

You can use cURL to interact with the API directly from your terminal. Follow the steps below.

#### Create candidate

```
    curl --location --request POST 'http://localhost:8080/api/v1/hiring/start' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "nome": "Some name"
    }'
```

#### Schedule interview

```
   curl --location --request POST 'http://localhost:8080/api/v1/hiring/schedule' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "codCandidato": 1
    }'
```
#### Disqualify Candidate

```
    curl --location --request POST 'http://localhost:8080/api/v1/hiring/disqualify' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "codCandidato": 1
    }'
```

#### Get candidate status

```
   curl --location --request GET 'http://localhost:8080/api/v1/hiring/status/candidate/1'
```

#### Approve candidate

```
    curl --location --request POST 'http://localhost:8080/api/v1/hiring/approve' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "codCandidato": 1
    }'
```

#### Get approved candidates 

```
    curl --location --request GET 'http://localhost:8080/api/v1/hiring/approved'
```