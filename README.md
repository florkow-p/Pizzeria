# Pizzeria

Spring backend with microservice architecture and angular on frontend.

## Starting services

Every service is a Spring Boot project which you can simply start by using IDE or any other method of your choice. 
pizzeria-meal and pizzeria-order services require database and rabbitmq-server to run correctly so before starting project make sure to properly configure `application.yml` files and install rabbitmq. 
DB of my choice was postgres. There's no required order of starting services except for
pizzeria-discovery which has to be started as first one.
If you're going to start rabbitmq, backend and frontend locally you should be fine and the only thing to configure might be the database and smtp server properties.

To start angular-app open terminal in project folder and type `ng serve` in case of any problems visit [angular setup](https://angular.io/guide/setup-local).

List of services in recommended order:
1. pizzeria-discovery
2. pizzeria-gateway
3. pizzeria-meal
4. pizzeria-order
5. pizzeria-mail

Note that lombok and mapstruct are used which means additional plugins might be required. 
