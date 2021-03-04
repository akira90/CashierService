# CashierService
Cashier Service in Sprint Boot.

Please execute the following commands to run the app:

## Instructions
```
gradle build
```

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

####The rules for the `JSON` object as follows:


- Username - alphanumeric, no spaces
- Password – min length 4, at least one upper case letter & number
- DoB (Date of Birth) - ISO 8601 format
- Payment Card Number – between 15 and 19 digits and cannot begin with **2020**.

