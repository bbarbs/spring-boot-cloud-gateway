# Introduction
Sample spring boot project which implement the spring cloud gateway for routing api's. For more info you can check it [here](https://cloud.spring.io/spring-cloud-gateway/) and for documentation you can access it [here](https://cloud.spring.io/spring-cloud-gateway/single/spring-cloud-gateway.html)

## Feature
* Spring cloud gateway
* Hystrix implementation

## Prerequisites
* consul

## Installation
Install first the consul agent in your local machine, you can download it [here](https://www.consul.io/downloads.html).

* Running Consul Agent
>consul agent -server -bootstrap-expect=1 -data-dir=consul-data -ui -bind=192.168.6.1(ip address of local machine)

You can check in http://localhost:8500/ui/ to determine if consul is up and running. Note: Before running the project ensure first that consul is running.

## Gateway Configuration
Sample spring cloud gateway configuration in properties. It can be set also in java like [here](https://cloud.spring.io/spring-cloud-gateway/).
```
spring:
  application:
    name: gateway-service
  cloud:
    gateway:
      routes:
      # =================================
      - id: customers-rest-api
        uri: lb://customer-service(sample customer service uri for consul or eureka) // http://localhost:8090(using the customer address)
        predicates:
        - Path=/customers/**
        filters:
        - name: Hystrix
          args:
            name: customerFallback
            fallbackUri: forward:/fallback
```
For Hystrix it uses fallbackuri which it defined in the controller like [this](https://github.com/bbarbs/spring-boot-cloud-gateway/blob/master/gateway/src/main/java/com/gateway/fallback/HystrixFallback.java).
<br/>
For more info regarding hystrix implementation you can check it [here](https://cloud.spring.io/spring-cloud-gateway/single/spring-cloud-gateway.html#_hystrix_gatewayfilter_factory)
