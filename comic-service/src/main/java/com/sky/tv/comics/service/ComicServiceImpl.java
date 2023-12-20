package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.ComicDTO;
import com.sky.tv.comics.dto.paging.PagingResponse;
import com.sky.tv.comics.entity.Comic;
import com.sky.tv.comics.entity.ComicAnalysis;
import com.sky.tv.comics.exception.ComicBusinessException;
import com.sky.tv.comics.exception.ResourceNotFoundException;
import com.sky.tv.comics.mapper.AutoComicMapper;
import com.sky.tv.comics.repository.ComicRepo;
import com.sky.tv.comics.repository.ComicViewRepo;
import com.sky.tv.comics.service.feignclient.EmployeeFeignClient;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ComicServiceImpl implements ComicService ,BaseService<ComicDTO> {

    private final ComicRepo comicRepository;

    private final ComicViewRepo comicViewRepo;

    private final WebClient webClient;

    private final EmployeeFeignClient employeeFeignClient;

    @Override
    public ComicDTO get(UUID id) {
        Comic comic = comicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ComicRepo.RESOURCE,
                        "id",
                        id.toString()
                ));
        return AutoComicMapper.MAPPER.mapToComicDto(comic);
    }

    public List<ComicDTO> get(List<UUID> ids) {
        List<Comic> comics = comicRepository.findAllById(ids);
        return comics.stream()
                .map(AutoComicMapper.MAPPER::mapToComicDto)
                .toList();
    }

    @Override
    public void create(@Valid List<ComicDTO> comicDTOs) {
        List<Comic> comics = comicDTOs.stream()
                .map(AutoComicMapper.MAPPER::mapToComic)
                .toList();
        comicRepository.saveAll(comics);
    }

    @Override
    public void update(@Valid List<ComicDTO> comicDTOs) throws ComicBusinessException {
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

    @Override
    public List<ComicDTO> getTopComicByLikeWeek(int numberOfComic) {
        return null;
    }

    @Override
    public List<ComicDTO> getRandomComicNew(int numberOfComic) {
        return null;
    }

    @Override
    public List<ComicDTO> getRandomLoveComic(int numberOfComic) {
        return null;
    }

    @Override
    public List<ComicDTO> getRandomAdventureComic(int comicOfComic) {
        return null;
    }

    @Override
    public List<ComicDTO> getEasternComic(int numberOfComic) {
        return null;
    }

    @Override
    public List<ComicDTO> getRandomComic(int numberOfComic) {
        return null;
    }

    @Override
    public PagingResponse<ComicDTO> getComicByCategories(int pageNumber, int pageSize, List<String> categories) {
        return null;
    }

    @Override
    public PagingResponse<ComicDTO> getComicByCategory(int pageNumber, int pageSize, String category) {
        return null;
    }

    @Override
    public PagingResponse<ComicDTO> getComics(int pageNumber, int pageSize) {
        return null;
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
