package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.ComicDTO;
import com.sky.tv.comics.entity.Comic;
import com.sky.tv.comics.exception.ComicBusinessException;
import com.sky.tv.comics.exception.ResourceNotFoundException;
import com.sky.tv.comics.mapper.AutoComicMapper;
import com.sky.tv.comics.repository.ComicRepository;
import com.sky.tv.comics.service.feignclient.EmployeeFeignClient;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ComicService {

    private final ComicRepository comicRepository;

    private final WebClient webClient;

    private final EmployeeFeignClient employeeFeignClient;

    public ComicDTO getComic(UUID id) {
        Comic comic = comicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ComicRepository.RESOURCE,
                        "id",
                        id.toString()
                ));
        return AutoComicMapper.MAPPER.mapToComicDto(comic);
    }

    public List<ComicDTO> getComics(List<UUID> ids) {
        List<Comic> comics = comicRepository.findAllById(ids);
        return comics.stream()
                .map(AutoComicMapper.MAPPER::mapToComicDto)
                .toList();
    }

    public void createComics(@Valid List<ComicDTO> comicDTOs) {
        List<Comic> comics = comicDTOs.stream()
                .map(AutoComicMapper.MAPPER::mapToComic)
                .toList();
        comicRepository.saveAll(comics);
    }

    public void updateComics(@Valid List<ComicDTO> comicDTOs) throws ComicBusinessException {
        List<UUID> ids = comicDTOs.stream().map(ComicDTO::getId).collect(Collectors.toList());
        List<Comic> comics = comicRepository.findAllById(ids);
        if (comics.size() != ids.size()) {
            throw new ComicBusinessException("Can't find out the entity with your DTOs");
        }
        List<Comic> comicsFromDTO = comicDTOs.stream()
                .map(AutoComicMapper.MAPPER::mapToComic)
                .toList();
        comicRepository.saveAll(comicsFromDTO);
    }

    //	@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultEmployee")
    //	@Retry(name =  "${spring.application.name}", fallbackMethod ="getDefaultEmployee")
    //	public String getTranslators(UUID id) {
    //		Object translator = webClient.get()
    //				.uri("http://localhost:8080/employeee/"+id)
    //				.retrieve()
    //				.bodyToMono(Object.class)
    //				.block();
    //
    ////		employeeFeignClient.getEmployee(id);
    //		return "thanh";
    //	}

    //	public String getDefaultEmployee(UUID id, Throwable e) {
    //		//which you would like to do in the fallback case.
    //		return "thanh fallback";
    //	}
}
