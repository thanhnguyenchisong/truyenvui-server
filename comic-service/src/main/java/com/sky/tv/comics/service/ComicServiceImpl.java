package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.ComicDTO;
import com.sky.tv.comics.dto.request.GetComicPaging;
import com.sky.tv.comics.dto.request.GetTypeDTO;
import com.sky.tv.comics.dto.response.BundlePagingResponse;
import com.sky.tv.comics.entity.Category;
import com.sky.tv.comics.entity.Comic;
import com.sky.tv.comics.entity.GroupComic;
import com.sky.tv.comics.entity.custom.TopComic;
import com.sky.tv.comics.exception.BusinessException;
import com.sky.tv.comics.exception.ResourceNotFoundException;
import com.sky.tv.comics.mapper.AutoComicMapper;
import com.sky.tv.comics.repository.CategoryRepo;
import com.sky.tv.comics.repository.ComicAnalysisRepo;
import com.sky.tv.comics.repository.ComicRepo;
import com.sky.tv.comics.repository.GroupComicRepo;
import com.sky.tv.comics.service.feignclient.EmployeeFeignClient;
import com.sky.tv.comics.utils.DateServiceUtils;
import jakarta.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@AllArgsConstructor
@Service
public class ComicServiceImpl implements ComicService, BaseService<ComicDTO> {

    public static final String ALL = "ALL";

    private final ComicAnalysisRepo comicAnalysisRepo;

    private final ComicRepo comicRepo;

    private final GroupComicRepo groupComicRepo;

    private final CategoryRepo categoryRepo;

    private final WebClient webClient;

    private final EmployeeFeignClient employeeFeignClient;

    @Override
    public ComicDTO get(UUID id) {
        Comic comic = comicRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comic", "id", id.toString()));
        return AutoComicMapper.MAPPER.toDTO(comic);
    }

    public List<ComicDTO> get(List<UUID> ids) {
        List<Comic> comics = comicRepo.findAllById(ids);
        return comics.stream().map(AutoComicMapper.MAPPER::toDTO).toList();
    }

    @Override
    public void create(@Valid List<ComicDTO> comicDTOs) {
        Set<String> categoryIDs = new HashSet<>();
        for(ComicDTO comicDTO : comicDTOs) {
            categoryIDs.addAll(comicDTO.getCategoryIDs());
        }
        Map<String, Category> map = categoryRepo.findAllById(categoryIDs).stream().collect(Collectors.toMap(Category::getName, Function.identity()));
        List<Comic> comics = comicDTOs.stream().map(dto -> {
            Comic comic = AutoComicMapper.MAPPER.toEntity(dto);
            List<String> categoriesIDs = dto.getCategoryIDs();
            Set<Category> categories = categoriesIDs.stream().map(map::get).collect(Collectors.toSet());
            comic.setCategories(categories);
            return comic;
        }).toList();
        comicRepo.saveAll(comics);
    }

    @Override
    public void update(@Valid List<ComicDTO> comicDTOs) throws BusinessException {
        Map<UUID, ComicDTO> mapDTOs = comicDTOs.stream().collect(Collectors.toMap(ComicDTO::getId,
            Function.identity()));
        List<Comic> existingComics = comicRepo.findAllById(mapDTOs.keySet());
        if (existingComics.size() != mapDTOs.size()) {
            throw new BusinessException("Can't find out the entity with your DTOs");
        }
        List<Comic> comics = existingComics.stream().map(comic -> AutoComicMapper.MAPPER.toEntity(mapDTOs.get(comic.getId()), comic)).toList();

        comicRepo.saveAll(comics);
    }

    @Override
    public List<ComicDTO> getComicPopular(int quality) throws ParseException {
        String startDate = DateServiceUtils.fromDateToString(new Date(), TimeZone.getDefault());
        String endDate = DateServiceUtils.addDay(startDate, -7);
        List<TopComic> topComicViews = comicAnalysisRepo.getComicAnalysisByView(startDate, endDate, quality);
        List<Comic> comics = topComicViews.stream().map(TopComic::getComic).toList();
        return comics.stream().map(AutoComicMapper.MAPPER::toDTO).toList();
    }

    @Override
    public List<BundlePagingResponse<ComicDTO>> getComicPaging(GetComicPaging paging) {
        List<BundlePagingResponse<ComicDTO>> result = new ArrayList<>();
        if (paging.getTypeDTO() == GetTypeDTO.CATEGORY) {
            result = getComicPagingCategory(paging);
        } else if (paging.getTypeDTO() == GetTypeDTO.GROUP) {
            result = getComicPagingGroup(paging);
        }
        return result;
    }

    private List<BundlePagingResponse<ComicDTO>> getComicPagingCategory(GetComicPaging paging) {
        List<BundlePagingResponse<ComicDTO>> result;
        List<String> names = paging.getNames();
        if (names.size() == 1 && ALL.equals(names.get(0))) {
            List<Category> categories = categoryRepo.findAll();
            result = getComicByCategories(categories, paging.getPageSize(), paging.getPageNumber());
        } else {
            List<Category> categories = categoryRepo.findAllByNameIn(names);
            result = getComicByCategories(categories, paging.getPageSize(), paging.getPageNumber());
        }
        return result;
    }

    private List<BundlePagingResponse<ComicDTO>> getComicPagingGroup(GetComicPaging paging) {
        List<BundlePagingResponse<ComicDTO>> result = new ArrayList<>();
        List<String> names = paging.getNames();
        if (names.size() == 1 && ALL.equals(names.get(0))) {
            List<GroupComic> groups = groupComicRepo.findAll();
            for (GroupComic group : groups){
                List<Category> categories = new ArrayList<>(group.getCategories());
                List<BundlePagingResponse<ComicDTO>> comicResult = getComicByCategories(categories,
                                                                                        paging.getPageSize(),
                                                                                        paging.getPageNumber()
                );
                result.add(buildBundleGroupComic(comicResult, group.getName(), paging.getPageNumber(), paging.getPageSize()));
            }
        } else {
            List<GroupComic> groups = groupComicRepo.findAllByNameIn(names);
            for (GroupComic group : groups){
                List<Category> categories = new ArrayList<>(group.getCategories());
                List<BundlePagingResponse<ComicDTO>> comicResult = getComicByCategories(categories,
                                                                                        paging.getPageSize(),
                                                                                        paging.getPageNumber()
                );
                result.add(buildBundleGroupComic(comicResult, group.getName(), paging.getPageNumber(), paging.getPageSize()));
            }
        }
        return result;
    }


    private BundlePagingResponse<ComicDTO> buildBundleGroupComic(List<BundlePagingResponse<ComicDTO>> bundlePagingResponses,
                                                                 String name,
                                                                 int pageNumber,
                                                                 int pageSize
    ) {
        BundlePagingResponse<ComicDTO> result = new BundlePagingResponse<>();
      if (bundlePagingResponses == null || bundlePagingResponses.isEmpty()) {
        return result;
      }
        int count = 0;
        List<ComicDTO> comics = new ArrayList<>();
        int number = (int) Math.ceil((double) pageSize / bundlePagingResponses.size());
        for (BundlePagingResponse<ComicDTO> bundle : bundlePagingResponses){
            int end = Math.min(number, bundle.getContent().size());
            comics.addAll(bundle.getContent().subList(0, end));
            count += end;
        }
        result.setName(name);
        result.setPageNumber(pageNumber);
        result.setPageSize(count);
        result.setContent(comics);
        return result;
    }

    private List<BundlePagingResponse<ComicDTO>> getComicByCategories(List<Category> categories, int pageNumber, int pageSize) {
        List<BundlePagingResponse<ComicDTO>> pagingResponses = new ArrayList<>();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        for (Category category : categories){
            List<Comic> comics = comicRepo.findAllByCategories(category, pageable);
            List<ComicDTO> comicDTOS = comics.stream().map(AutoComicMapper.MAPPER::toDTO).toList();
            BundlePagingResponse<ComicDTO> pagingResponse = new BundlePagingResponse<>(category.getName());
            pagingResponse.setContent(comicDTOS);
            pagingResponse.setPageSize(pageSize);
            pagingResponse.setPageNumber(pageNumber);
            pagingResponses.add(pagingResponse);
        }
        return pagingResponses;
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
