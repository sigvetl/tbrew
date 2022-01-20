https://two-dog.herokuapp.com/ <br><br>

Authenticated web page for Two Dog Brewery using the Spring Boot framework. <br>
MVC-architecture using Mybatis mappers and a Postgres database. <br>
View is static with use of html-templates with thymeleaf. <br>
The beer table is populated using a web-scraping script that scrapes all-grain brew packages from bryggselv.no available as an endpoint. <br>

Dependencies: <br>
&nbsp;&nbsp;&nbsp;&nbsp;  pip install bs4 <br>
&nbsp;&nbsp;&nbsp;&nbsp;  pip install flask <br>
&nbsp;&nbsp;&nbsp;&nbsp;  sdk install maven <br>

 
To run: <br>
The following scripts both loads a server, and you need one bash session for each script <br>
&nbsp;&nbsp;&nbsp;&nbsp;  navigate to directory: [path_to_directory]/twoDog/ <br>
&nbsp;&nbsp;&nbsp;&nbsp;  python3 scrape_bryggselv.py <br>
&nbsp;&nbsp;&nbsp;&nbsp;  mvn spring-boot:run <br>
  
