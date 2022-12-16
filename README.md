# restaurants

API documentation provided by swagger: <a href="https://restaurants-matthewkevinhardy.up.railway.app/">restaurants-matthewkevinhardy.up.railway.app</a>
<br>
<br>
Sys_admin username and password: <b>sa / password</b>
<br>
<br>
Secured with JWT token.  To obtain token post username/password JSON to: /api/v1/authenticate<br>
<br>
or<br>
<br>
curl -X POST "http://localhost:8080/api/v1/authenticate" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"password\": \"password\", \"username\": \"sa\"}"