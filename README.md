[![BCH compliance](https://bettercodehub.com/edge/badge/Rolan2772/currency-converter?branch=master)](https://bettercodehub.com/)
[![Quality Gate](https://sonarcloud.io/api/badges/gate?key=com.zooplus.sdc%3Acurrency-converter)](https://sonarcloud.io/dashboard?id=com.zooplus.sdc%3Acurrency-converter)
[![Build Status](https://travis-ci.org/Rolan2772/currency-converter.svg?branch=master)](https://travis-ci.org/Rolan2772/currency-converter)
[![Heroku](https://heroku-badge.herokuapp.com/?app=rolan-currency-converter&root=/swagger-ui.html)](https://rolan-currency-converter.herokuapp.com/swagger-ui.html)

# currency-converter
Protected currency converter application using a public currency converter API.
Project demonstrates external API integration and basic CI/CD flow.
All bages are clickable and leads to project analysis/build/deployment.

### Used tools
 - https://bettercodehub.com/ and https://sonarcloud.io/ - as code anaysing tools
 - https://travis-ci.org - as CI/CD tool
 - https://www.heroku.com/ - as deployment environment
 
### App endpoints
 - Login: POST https://rolan-currency-converter.herokuapp.com/login?username=Rolan&password=test
 - Logout: GET https://rolan-currency-converter.herokuapp.com/logout
 - Documented API: https://rolan-currency-converter.herokuapp.com/swagger-ui.html
 - Monitoring endpoints: https://rolan-currency-converter.herokuapp.com/actuator/health
 - Available monitoring endpoints: [Spring Boot Actuator Endpoints](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html)
 
### User scenarios:
 - sign up -> get exchange rate -> logout
 - login -> get history exchange rate -> logout
 
### Build and run instructions
 - Build application : mvn clean install
 - Run Application: java -jar target/currency-converter-0.0.1.jar -Dintegration.openExchangeRatesProperties.appId=<Open Exchange Rates ID>
 
