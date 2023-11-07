package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.ComicDTO;
import com.sky.tv.comics.entity.Comic;
import com.sky.tv.comics.mapper.ComicMapper;
import com.sky.tv.comics.repository.ComicRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;

import java.util.List;
import java.util.stream.Collectors;

@Server
@AllArgsConstructor
public class ComicService {

	private final ComicRepository comicRepository;

	private final ComicMapper comicMapper;

	public void createComic(@Valid List<ComicDTO> comicDTOs) {
		List<Comic> comics = comicDTOs.stream().map(comicDTO -> comicMapper.map(comicDTO, Comic.class)).collect(Collectors.toList());
		comicRepository.saveAll(comics);
	}
}
