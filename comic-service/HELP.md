# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.5/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.5/reference/htmlsingle/index.html#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.1.5/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)


### Actuator
- /conditions: show alls the auto configuration report, categorised into positiveMatches and negativeMatches
- /mapping: show all the @RequestMapping paths declared in the application
- /configprops: offers all the configuration properties defined by @ConfigurationProperties bean including your 
  configuration properties defined in the application.properties or YAML files.
- /metrics: show metrics about current application such as how much memory is using, free memory, size help used, 
  number thread used and so on.
- /evn: exposes all the properties from the Spring's configurableEnvironment interface, such as a list of active 
  profiles, application properties, system environment variable and so on.
- /threaddump: display running thread details along with it's JVM stack
- /loggers: view and config the log level of your application at runtime
- /shutdown: be used to gracefully shut down the application

# Communication
Spring provides 3 libs for communication bw service
- RestTemplate : synch
- WebClient : synch and asynch, that is a part of Webflux dependency
  - Flux that is a standard Publisher that represents 0 to N synchronous sequence values
  - Mono that is a standard Publisher that represents 0 to 1 synchronous sequence values
- Cloud OpenFeign




