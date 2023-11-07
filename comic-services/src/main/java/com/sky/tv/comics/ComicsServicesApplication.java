package com.sky.tv.comics;

import com.sky.tv.comics.dto.ComicDTO;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ComicsServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComicsServicesApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() { //maybe we don't use that way
		return new ModelMapper();
	}


}
