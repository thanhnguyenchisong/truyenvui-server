package com.sky.tv.comics.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfig {

	@Value("${max.thread:10}")
	private Integer maxThread;
}
