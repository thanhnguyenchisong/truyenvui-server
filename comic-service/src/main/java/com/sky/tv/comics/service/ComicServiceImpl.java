package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.ComicDTO;
import com.sky.tv.comics.entity.GroupEnum;
import com.sky.tv.comics.dto.request.GetComicPaging;
import com.sky.tv.comics.dto.request.GetTypeDTO;
import com.sky.tv.comics.dto.response.BundlePagingResponse;
import com.sky.tv.comics.entity.Category;
import com.sky.tv.comics.entity.CategoryEnum;
import com.sky.tv.comics.entity.Comic;
import com.sky.tv.comics.entity.GroupComic;
import com.sky.tv.comics.entity.custom.TopComic;
import com.sky.tv.comics.exception.ComicServiceBusinessException;
import com.sky.tv.comics.exception.ResourceNotFoundException;
import com.sky.tv.comics.mapper.AutoComicMapper;
import com.sky.tv.comics.repository.CategoryRepo;
import com.sky.tv.comics.repository.ComicAnalysisRepo;
import com.sky.tv.comics.repository.ComicRepo;
import com.sky.tv.comics.repository.GroupComicRepo;
import com.sky.tv.comics.service.feignclient.EmployeeFeignClient;
import com.sky.tv.comics.utils.DateUtils;
import jakarta.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
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
    Comic comic = comicRepo.findById(id)
                           .orElseThrow(() -> new ResourceNotFoundException("Comic",
            "id",
            id.toString()
        ));
    return AutoComicMapper.MAPPER.mapToComicDto(comic);
  }

  public List<ComicDTO> get(List<UUID> ids) {
    List<Comic> comics = comicRepo.findAllById(ids);
    return comics.stream()
        .map(AutoComicMapper.MAPPER::mapToComicDto)
        .toList();
  }

  @Override
  public void create(@Valid List<ComicDTO> comicDTOs) {
    List<Comic> comics = comicDTOs.stream()
        .map(AutoComicMapper.MAPPER::mapToComic)
        .toList();
    comicRepo.saveAll(comics);
  }

  @Override
  public void update(@Valid List<ComicDTO> comicDTOs) throws ComicServiceBusinessException {
    List<UUID> ids = comicDTOs.stream().map(ComicDTO::getId).toList();
    List<Comic> comics = comicRepo.findAllById(ids);
    if (comics.size() != ids.size()) {
      throw new ComicServiceBusinessException("Can't find out the entity with your DTOs");
    }
    List<Comic> comicsFromDTO = comicDTOs.stream()
        .map(AutoComicMapper.MAPPER::mapToComic)
        .toList();
    comicRepo.saveAll(comicsFromDTO);
  }

  @Override
  public List<ComicDTO> getComicPopular(int quality) throws ParseException {
    String startDate = DateUtils.fromDateToString(new Date(), TimeZone.getDefault());
    String endDate = DateUtils.addDay(startDate, -7);
    List<TopComic> topComicViews = comicAnalysisRepo.getComicAnalysisByView(startDate,
                                                                            endDate,
                                                                            quality);
    List<Comic> comics = topComicViews.stream().map(TopComic::getComic).toList();
    return comics.stream().map(AutoComicMapper.MAPPER::mapToComicDto).toList();
  }

  @Override
  public List<BundlePagingResponse<ComicDTO>> getComicPaging(GetComicPaging paging) {
    List<BundlePagingResponse<ComicDTO>> result = new ArrayList<>();
    if(paging.getTypeDTO() == GetTypeDTO.CATEGORY) {
        result = getComicPagingCategory(paging);
    } else if(paging.getTypeDTO() == GetTypeDTO.GROUP) {
        result = getComicPagingGroup(paging);
    }
    return result;
  }

  private List<BundlePagingResponse<ComicDTO>> getComicPagingCategory (GetComicPaging paging) {
    List<BundlePagingResponse<ComicDTO>> result;
    List<String> names = paging.getNames();
    if (names.size() == 1 && ALL.equals(names.get(0))) {
      List<Category> categories = categoryRepo.findAll();
      result = getComicByCategories(categories, paging.getPageSize(), paging.getPageNumber());
    } else {
      List<CategoryEnum> categoryEnums = names.stream().map(CategoryEnum::valueOf).toList();
      List<Category> categories = categoryRepo.findAllByNameIn(categoryEnums);
      result = getComicByCategories(categories, paging.getPageSize(), paging.getPageNumber());
    }
    return result;
  }

  private List<BundlePagingResponse<ComicDTO>> getComicPagingGroup (GetComicPaging paging) {
    List<BundlePagingResponse<ComicDTO>> result = new ArrayList<>();
    List<String> names = paging.getNames();
    if (names.size() == 1 && ALL.equals(names.get(0))) {
      List<GroupComic> groups = groupComicRepo.findAll();
      for (GroupComic group : groups){
        List<Category> categories = new ArrayList<>(group.getCategories());
        List<BundlePagingResponse<ComicDTO>> comicResult = getComicByCategories(
            categories,
            paging.getPageSize(),
            paging.getPageNumber()
        );
        result.add(buildBundleGroupComic(comicResult, group.getName(), paging.getPageNumber(), paging.getPageSize()));
      }
    } else {
      List<GroupEnum> groupEnums = names.stream().map(GroupEnum::valueOf).toList();
      List<GroupComic> groups = groupComicRepo.findAllByNameIn(groupEnums);
      for (GroupComic group : groups){
        List<Category> categories = new ArrayList<>(group.getCategories());
        List<BundlePagingResponse<ComicDTO>> comicResult = getComicByCategories(
            categories,
            paging.getPageSize(),
            paging.getPageNumber()
        );
        result.add(buildBundleGroupComic(comicResult, group.getName(), paging.getPageNumber(), paging.getPageSize()));
      }
    }
    return result;
  }


  private BundlePagingResponse<ComicDTO> buildBundleGroupComic(List<BundlePagingResponse<ComicDTO>> bundlePagingResponses, GroupEnum name, int pageNumber, int pageSize) {
    BundlePagingResponse<ComicDTO> result = new BundlePagingResponse<>();
    if(bundlePagingResponses == null || bundlePagingResponses.isEmpty()) return result;
    int count = 0;
    List<ComicDTO> comics = new ArrayList<>();
    int number = (int) Math.ceil((double) pageSize / bundlePagingResponses.size());
    for (BundlePagingResponse<ComicDTO> bundle : bundlePagingResponses){
      int end = Math.min(number, bundle.getContent().size());
      comics.addAll(bundle.getContent().subList(0, end));
      count += end;
    }
    result.setName(name.name());
    result.setPageNumber(pageNumber);
    result.setPageSize(count);
    result.setContent(comics);
    return result;
  }

  private List<BundlePagingResponse<ComicDTO>> getComicByCategories(List<Category> categories, int pageNumber, int pageSize) {
    List<BundlePagingResponse<ComicDTO>> pagingResponses = new ArrayList<>();
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    for(Category category : categories) {
      List<Comic> comics =  comicRepo.findAllByCategories(category, pageable);
      List<ComicDTO> comicDTOS = comics.stream().map(AutoComicMapper.MAPPER::mapToComicDto).toList();
      BundlePagingResponse<ComicDTO> pagingResponse = new BundlePagingResponse<>(category.getName().name());
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
