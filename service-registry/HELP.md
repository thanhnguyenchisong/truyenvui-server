# Read Me First
The following was discovered as part of building this project:

* The original package name 'com.sky.tv.service-registry' is invalid and this project uses 'com.sky.tv.serviceregistry' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.5/maven-plugin/reference/html/#build-image)
* [Eureka Server](https://docs.spring.io/spring-cloud-netflix/docs/current/reference/html/#spring-cloud-eureka-server)

### Guides
The following guides illustrate how to use some features concretely:

* [Service Registration and Discovery with Eureka and Spring Cloud](https://spring.io/guides/gs/service-registration-and-discovery/)

### CREATE A SERVICE REGISTRY
- Add @EnnableEurekaService anotation
- Disable Eureka Server as Eureka Client in config
- Lunch
- Registering your service as Eureka Client
- Multiple Instances of your service

### Deployment
1. Build the image

`docker build -t service-registry:1.0 .`
2. Run container

`docker run -d --hostname=service-registry --network=comic-network -p 8886:8886 --name=service-registry service-registry:1.0`

