# Some important Spring Cloud modules base on resolving problems
### 1. API Gateway
There are many microservices so how can we manage the port and host and how the client can send 
request to a particular service ?
 
Answer: API Gateway is a central component bw client and back end microservices, that is pattern that can use to 
handle the client request and route that client request to appropriate microservice

### 2. ConfigService
We need to change the configuration in multiple microservices ? 

Answer: We need a central place where we can keep all the configuration files of all these microservices

### 3. Circuit Pattern
ms1 call to ms2 but ms2 is down, so ms1 continue call to ms2 so that's not good because wasting of our resource so 
we have limit the number of calls to the Microservice two whenever this Microservice two is down

### 4. Service Registry
- Service Registry and Discovery is important role because we most likely run multiple instances of services and we 
need a mechanism to call other serivice without hardcoding their hostname or port number
- Cloud environment service instances may come up and go down anytime. So we need some automatic service 
  registration and discovery mechanism - track all microservice and it's instances so we can use it to see what 
  microservices are up and down.

=> Spring Cloud Netflix Eureka


### 5. Distributer tracing
Identify the complete call hierarchy from start ton end.