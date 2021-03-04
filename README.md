# Cashier App

Cashier app - creates a user.

Please execute the following commands to run the app:

## Getting Started

These instructions should get this project working locally on your machine.

### Prerequisites

```
java 15 (jdk15+ installed 
gradle 6.8.3
```
### Installation

```
gradle build
```
### Running the app

```
gradle bootRun
```

This should have a web server on `http://localhost:8080`.

Send the following `POST` request via `curl`.

```
curl -X POST \
http://127.0.0.1:8080/register \
-H 'Content-Type: application/json' \
-d '{
"username": "BobFrench",
"password": "Password1",
"dob": "1980-02-21",
"paymentCardNumber": "349293081054422"
}'
```

#### Constrains for the request


- Username - alphanumeric, no spaces
- Password – min length 4, at least one upper case letter & number
- DoB (Date of Birth) - ISO 8601 format
- Payment Card Number – between 15 and 19 digits




