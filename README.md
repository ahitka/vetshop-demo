# vetshop-demo

https://github.com/ahitka/vetshop-demo

Simple backend vetshop demo. Contains 3 types of endpoints - product, user, order. 

Public endpoints are those for product and user. Order endpoints are behind jwt token authorization - first you need to register user, 

then get authorization token with /authenticate endpoint. Use obtained token in Authorization header as shown below. There are 5 products 

added, you can see them by calling service or by logging to H2 database.

Project is configured through github actions to build and deploy docker image to Heroku on every push to master branch.


-----

https://ahitka-vetshop-demo.herokuapp.com/h2-console

JDBC URL: jdbc:h2:mem:vetshopdb

User Name: sa

----
products

GET https://ahitka-vetshop-demo.herokuapp.com/api/products

GET https://ahitka-vetshop-demo.herokuapp.com/api/products/product/1

GET https://ahitka-vetshop-demo.herokuapp.com/api/products?search=price>20,price<100

GET https://ahitka-vetshop-demo.herokuapp.com/api/products?search=name:Krm*,price>10,price<100

----
user

POST https://ahitka-vetshop-demo.herokuapp.com/register

Content-Type: application/json

{ "username": "A",   "password": "A",   "email": "a@a.a" }

POST https://ahitka-vetshop-demo.herokuapp.com/authenticate

Content-Type: application/json

{ "username": "A",   "password": "A"}

----
orders

POST https://ahitka-vetshop-demo.herokuapp.com/api/orders/create

Content-Type: application/json

Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBIiwiZXhwIjoxNTk3MTU3Mzc0LCJpYXQiOjE1OTcxMzkzNzR9.ZPdWSQH3L3o6YftM9m6AzcpLTEXDubRoBqez94tFRmo

{
  "items": [
    {
      "productId": 1,
      "count": 2
    },
    {
      "productId": 3,
      "count": 4
    }
  ]
}

GET https://ahitka-vetshop-demo.herokuapp.com/api/orders/

Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBIiwiZXhwIjoxNTk3MTY5NjUwLCJpYXQiOjE1OTcxNTE2NTB9.pFg2L7sAA6HAh3V-xTNxA2ablqG_hQmaAQynFENVr7w

----