package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.ComicDTO;
import com.sky.tv.comics.entity.Comic;
import com.sky.tv.comics.exception.ResourceNotFoundException;
import com.sky.tv.comics.mapper.AutoComicMapper;
import com.sky.tv.comics.repository.ComicRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Server
@AllArgsConstructor
public class ComicService {

	private final ComicRepository comicRepository;

	public void createComic(@Valid List<ComicDTO> comicDTOs) {
		List<Comic> comics = comicDTOs
				.stream()
				.map(AutoComicMapper.MAPPER::mapToComic)
				.collect(Collectors.toList());
		comicRepository.saveAll(comics);
	}

	public ComicDTO getComicById(UUID id) {
		Comic comic = comicRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ComicRepository.RESOURCE, "id", id.toString()));
		return AutoComicMapper.MAPPER.mapToUserDto(comic);
	}
}
