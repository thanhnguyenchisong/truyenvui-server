package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.ComicDTO;
import com.sky.tv.comics.entity.Comic;
import com.sky.tv.comics.repository.ComicRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@Server
@AllArgsConstructor
public class ComicService {
	private final ComicRepository comicRepository;
	private final ModelMapper modelMapper;

	public void createComic(ComicDTO comicDTO) {
		Comic comic = modelMapper.map(comicDTO, Comic.class);
	}
}
