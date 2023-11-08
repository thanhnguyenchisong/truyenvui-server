# Some important Spring Cloud modules base on resolving problems
### 1. Service Registry
`Motivation` : We have multiple microservices and it's instances so we need to be aware and manage that. How can we
request to a particular services and in the case one instance is down, how we can request to a available instances ?

`Solution`
- Service Registry and Discovery is important role because we most likely run multiple instances of services and we
  need a mechanism to call other serivice without hardcoding their hostname or port number
- Cloud environment service instances may come up and go down anytime. So we need some automatic service
  registration and discovery mechanism - track all microservice and it's instances so we can use it to see what
  microservices are up and down.

`Role`
- Keep track all running instance of service
- Allow services to discover each other by service name or ID
- Provide load balancing for instance in services. (in the case service call to each other internally)

=> **Spring Cloud Netflix Eureka**

`Technical`
- Eureka Server (Service Registry Server) creating
  - Add Eureka Server lib
  - Enable Eureka Server by @EnableEurekaServer annotation in main class.
  - Disable the Eureka Client add the configuration in .properties file
- Eureka Client (Your microservices) creating
  - Add Eureka Client lib
  - Enable Eureka Client by @EnableEurekaClient - without this step for Spring v3
  - Define the `spring.application.name=your service identification` of service client in configuration file
  - Registry service to Eureka Server by `eureka.instance.client.serverUrl.defaultZone=your eureka server link`


##### a. Load blancing with Eureka, OpenFeign and Spring Cloud LoadBalancer
`Motivation`: ms1 and ms2, both of them registry in EurekaServer and ms2 have 3 instances, ms1 call to ms2 by OpenFeign.
- When have ms2 instance down -> ms1 need to get from another instance
- When ms1 call many times to ms2 at sametime - that will share the request to make sure we have resource balance
  to avoid stuck

Spring Cloud LoadBalancing already in EurekaClient lib as a dependency.

`Solution`: LoadBalancer with Spring Cloud LoadBalancing

`Technical` :
- Config: @FeignClient(name = "your spring application name in your service which provides the endpoint")

### 2. API Gateway
`Motivation`:  There are many microservices so how can we manage the port and host and how the client can send 
request to a particular service ?

`Solution`
- Provide a unified interface for a set of microservices so that clients no need to know about all details of 
  microservices internals.
- API Gateway centralize cross-cutting concerns like security, monitoring, rate limiting

`Role`
- Route the request
- Load balancing
- Security

=> **Spring Cloud Gateway**

`Techinical`
- Add Spring Gateway lib
- Register API-Gateway as Eureka Client to Eureka Server (Service Registry) as other services.
- Configuring API Gateway Routes
- Using Spring Cloud Gateway to Automatically Create Routes

### 3. Config Service
`Motivation` : We have multiple microservices, and we can scale one microservice to multiple instances. Each of 
microservices have a configuration so when you change that configuration you need to restart all instances => That 
is not good idea.

`Solution`
- Provide a central configuration so no need to go inside each microservice for update the configuration
- No need to restart your instances after changed configuration of microservice.

=> **Spring Cloud Config**
`Role`
- Update the configuration

`Technical`
- Add Spring Cloud Config lib
- Register Config-Server as Eureka Client
- Setup Git location for Config Server
- Refactor your services to use Config Server
- Refresh configuration without restarting the instance by using `@RefreshScope and call spring-actuator refresh
 endpoint` by manually => That is not practical and viable when you have large number of instance.
- Refresh configuration automatically by `Spring Cloud Bus`.

#### a. Spring Cloud Bus for reload the configuration automatically
`Motivation` : Refresh the configuration automatically

`Solution`: Message Broker to talk all instances refresh configuration.

=> **Spring Cloud Bus** `TODO Read more on other benefit of Spring Cloud Bus`
`Role`
Provide /busrefresh actuator endpoint - we just call that for refresh config for all instances.

`Techinical`
- Add `spring-cloud-starter-bus-amqp` lib to your services ?
- install RabbitMQ
- config RabbitMQ in your services
- Test by change the config and call /busrefresh

All the service which have spring cloud bus will be aware of changing and refresh the config


### 4. Circuit Pattern
`Motivation`: When client -> ms1 -> ms2 -> ms3 -> ms4 dependencies. Some reason, ms4 down => ms3 can't work properly 
then ms3 down => ms2 down => ms1 down. So ms4 down infect other microservices as well

`Solution`
- Implement fallback mechanism that mean when ms4 down then ms3 will return some default response.
- Circuit breaker pattern when ms4 goes down then it won't allow ms3 to hit the request to ms4 continuously
- Retry mechanism
`Role:`
- `Close` acecept for connect, `Open` not accept for connect, `Half Open` allow limited number of calls
- Switching strategy: It's close but when the fail number over threshold (your config) => switch to `Open` 
  state => after a period time (your config 5s or 10s) => switch to `Half Open` => consider your number request (your 
  config) if those are success => switch to `Close` state else switch to `Open` state 
- Retry

=> **Spring Cloud Circuit Breaker**

`Technical`

- `CircuitBreaker`
  - `Circuit Breaker` implementation with `Resilience4j` in client service (microservice is requesting to target 
    microservice)
  - Add dependencies `Spring Cloud Circuit Breaker` and `Spring aop` which is used for logging information as well as 
    metrics information.
  - Using @CircuitBreaker annotation to a method which is calling to external service
  - Fallback method implementation
  - Add Circuit Breaker configuration in application.properties file
- `Retry`
  - Use @Retry annotation to method which calls to external service
  - FallBack method implementation
  - Add retry configuration in application.properties file
**Reference**: https://resilience4j.readme.io/docs/getting-started-6 and https://spring.io/projects/spring-cloud-circuitbreaker

### 5. Distribute tracing
`Motivation` How can we tracing full follow of request and all instance information as logging.

`Solution`
- Collect all information
`Role`

`Technical`
- Add libs sleuth and sleuth-zipkin


### 6. Sumarize steps to create a microservice
- Create Service by Spring boot
- Config Database
- Create JPA entity and repository
- Create Mapping
- Build controller rest api
- Build the calling external service
- Register service to service-registry
- Refactor to use Config-Server
- Use Spring Bus
- Optional - Config API Gateway manually if we disable auto
- Distribute tracing.



