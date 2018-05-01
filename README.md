//Project title
Simple Web Service with CRUD functionality

//Build Status
Build passed

//Tools and technology used
Eclipse, Maven, JAX RS, Heroku, PostGre, GitHub

//App Deployed at
https://personwebservice26.herokuapp.com

//Operations supported
GET - https://personwebservice26.herokuapp.com/people  -> Returns List(if exists)
GET - https://personwebservice26.herokuapp.com/people/{id} -> Returns single record(if exists)
POST - https://personwebservice26.herokuapp.com/people [People]  -> Returns Http Status code with People object
PUT - https://personwebservice26.herokuapp.com/people/{id} -> Returns modified single record(if exists)
DELETE - https://personwebservice26.herokuapp.com/people/{id} -> Removes single record (if exists)

//Curl commands
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET https://personwebservice26.herokuapp.com/people/1

curl -H "Content-Type: application/json" -H "Accept: application/json" -X POST -d '{"name":"Ruchi ","age":28,"dob":"04-25-1990","email":"ruchi@gmail.com"}' https://personwebservice26.herokuapp.com/rest/people