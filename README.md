# Pizzeria

Spring backend with microservice architecture and angular on frontend.

<p align="center">
  <img src="https://github.com/Patryk-F/Pizzeria/blob/master/docs/arch.jpg" height="550" width="700">
</p>

## Starting services

Every service is a Spring Boot project which you can simply start by using IDE or any other method of your choice. 
[pizzeria-meal](https://github.com/Patryk-F/Pizzeria/tree/master/pizzeria-meal) and [pizzeria-order](https://github.com/Patryk-F/Pizzeria/tree/master/pizzeria-order) 
services require database and rabbitmq-server to run correctly so before starting project make sure to properly configure `application.yml` files and install rabbitmq. 
DB of my choice was postgres but any other should be fine as well. There's no required order of starting services except for
[pizzeria-discovery](https://github.com/Patryk-F/Pizzeria/tree/master/pizzeria-discovery) which has to be started as first one.
If you're going to start rabbitmq, backend and frontend locally you should be fine and the only thing to configure might be the database and smtp server properties.

List of services in recommended order:
1. pizzeria-discovery
2. pizzeria-gateway
3. pizzeria-meal
4. pizzeria-order
5. pizzeria-mail

Note that lombok and mapstruct are used which means additional plugins might be required. 

## TODO
- docker
- config server
- circuit breaker
- ...
