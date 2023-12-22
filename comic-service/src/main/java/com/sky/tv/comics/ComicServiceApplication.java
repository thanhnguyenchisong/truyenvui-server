package com.sky.tv.comics;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
@OpenAPIDefinition(
    info = @Info(
        title = "Comic REST API documentation",
        description = "Spring Boot REST API documentation",
        version = "v1.0",
        contact = @Contact(
            name = "Thanh",
            email = "thanhnguyenchisong@gmail.com",
            url = "Unknown"
        ),
        license = @License(
            name = "Apache 2.0"
        )
    ),
    externalDocs = @ExternalDocumentation(
        description = "Comic Management is one microservice in the big group of truyenvui project, ",
        url = "Unknown"
    )
)
public class ComicServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ComicServiceApplication.class, args);
  }

}
