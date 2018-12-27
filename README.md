[![BCH compliance](https://bettercodehub.com/edge/badge/Rolan2772/currency-converter?branch=master)](https://bettercodehub.com/)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=com.zooplus.sdc:currency-converter&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.zooplus.sdc:currency-converter)
[![Code Covarage](https://sonarcloud.io/api/project_badges/measure?project=com.zooplus.sdc:currency-converter&metric=coverage)](https://sonarcloud.io/dashboard?id=com.zooplus.sdc:currency-converter)
[![Build Status](https://travis-ci.org/Rolan2772/currency-converter.svg?branch=master)](https://travis-ci.org/Rolan2772/currency-converter)
[![Heroku](https://heroku-badge.herokuapp.com/?app=rolan-currency-converter&root=/swagger-ui.html&style=flat&svg=1)](https://rolan-currency-converter.herokuapp.com/swagger-ui.html)

# currency-converter
Protected currency converter application using a public currency converter API.
Project demonstrates external API integration and basic CI/CD flow.
All bages are clickable and leads to project analysis/build/deployment.

### Used tools
 - https://bettercodehub.com/ and https://sonarcloud.io/ - as code anaysing tools
 - https://travis-ci.org - as CI/CD tool
 - https://www.heroku.com/ - as deployment environment
 
### Architecture
 - Backend - Spring Boot application with REST endpoints
 - Frontend - Vue.js or Angular JS (not implemented yet)
 
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
```
git clone https://github.com/Rolan2772/currency-converter.git currency-converter
cd currency-converter
mvn clean install
java -jar target/currency-converter-0.0.1.jar -Dintegration.openExchangeRatesProperties.appId=\<Open Exchange Rates ID\>
```
