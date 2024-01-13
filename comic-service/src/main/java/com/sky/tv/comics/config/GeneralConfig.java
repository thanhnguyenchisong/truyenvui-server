package com.sky.tv.comics.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RefreshScope //refresh if there is the refresh calling from actuator
public class GeneralConfig {

  @Value("${max.thread:10}")
  private Integer maxThread;

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public WebClient webClient() {
    return WebClient.builder().build();
  }
}
