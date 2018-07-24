Product Manager
================

Brief Summary
The application provides two API's

GetProducts API
```
{
  "id": "(string) unique id of the event",
  "timestamp": "(timestamp) utc timestamp of the event",
  "products": [
    {
      "id": "(long) id of the product"
    }
  ]
}
```
example
--------

```
{
  "id": "1234",
  "timestamp": "2017-01-23T12:34:56.123456789Z",
  "products": [
    {
      "id": 2
    }
  ]
}
```

and

PutProducts API

```
{
  "id": "(string) unique id of the event",
  "timestamp": "(timestamp) utc timestamp of the event",
  "products": [
    {
      "id": "(long) id of the product",
      "name": "(string) name of the product",
      "quantity": "(integer) quantity of the product",
      "sale_amount": "(double) total sale amount"
    }
  ]
}
```
example
-------
```
{
  "id": "1234",
  "timestamp": "2017-01-23T12:34:56.123456789Z",
  "products": [
    {
    	"id":1,
      "name": "test1.4",
      "quantity": 1,
      "sale_amount": 23.2
    }
  ]
}
```
The Swagger Documentation can be found in 
localhost:8080/swagger-ui.html/


Dev Mode Or Production Mode
----------------------------

There are two Profiles in which service can run.
1) Dev Mode : In this mode, no authentication is required
2) Prod Mode: In this mode, first we have to authenticate by providing username/password. This mode uses JSON Web Token (JWT). The behaviour of the token can be controlled via following parameters : 
`-Djwt.header=authorization -Djwt.expires_in=300 -Djwt.secret=queenvictoria`. These parameters are present in `application.properties` also.
a) jwt.header := The Key Name by which token will be present in the header
b) jwt.expires_in := time in secs.
c) jwt.secret = The Secret Key


Steps for code execution

1) Pull the code
2) Create a Postgres db with the name `product-manager`
3) Copy contents from `data.sql` and run the queries

DevMode
To Run in Dev Mode
-------------------
mvn spring-boot:run -Dspring.datasource.url=jdbc:postgresql://localhost:5432/product-manager -Dspring.datasource.username=`<postgresusername>` -Dspring.datasource.password=`<passwd>`

ProductionMode
To Run in Production Mode
-------------------------
mvn spring-boot:run -Dspring.profiles.active=prod -Dspring.datasource.url=jdbc:postgresql://localhost:5432/product-manager -Dspring.datasource.username=`<postgresusername>` -Dspring.datasource.password=`<passwd>` -Djwt.header=authorization -Djwt.expires_in=300 -Djwt.secret=queenvictoria
