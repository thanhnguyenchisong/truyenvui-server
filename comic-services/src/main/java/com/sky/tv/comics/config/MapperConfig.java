package com.sky.tv.comics.config;

import com.sky.tv.comics.mapper.ComicMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class just for create the mapper bean
 * The ModelMapper just help us save time for mapping 1-1 with same field name, if you have other case you need add your
 * mapping strategy in your mapper class. Example ComicMapper
 */
@Configuration
public class MapperConfig {

	@Bean("comicMapper")
	public ComicMapper comicMapper() {
		return new ComicMapper();
	}
}
