package com.sky.tv.comics.service;

import com.sky.tv.comics.constant.ValidationMessageEnum;
import com.sky.tv.comics.dto.GroupComicDTO;
import com.sky.tv.comics.entity.Category;
import com.sky.tv.comics.entity.GroupComic;
import com.sky.tv.comics.exception.BusinessException;
import com.sky.tv.comics.exception.ResourceNotFoundException;
import com.sky.tv.comics.mapper.AutoGroupComicMapper;
import com.sky.tv.comics.repository.CategoryRepo;
import com.sky.tv.comics.repository.GroupComicRepo;
import com.sky.tv.comics.service.validation.CrudBusinessValidator;
import jakarta.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Transactional
public class GroupComicServiceImpl implements GroupComicService {

  private final GroupComicRepo groupComicRepo;
  private final CategoryRepo categoryRepo;
  private final CrudBusinessValidator validator;

  @Override
  public List<GroupComicDTO> getAll() {
    return groupComicRepo.findAll().stream().map(AutoGroupComicMapper.MAPPER::toDTO).toList();
  }

  @Override
  public GroupComicDTO get(String id) {
    GroupComic groupComic = groupComicRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Group Comic", "id", id));
    return AutoGroupComicMapper.MAPPER.toDTO(groupComic);
  }

  @Override
  public List<GroupComicDTO> get(List<String> ids) {
    return groupComicRepo.findAllById(ids).stream().map(AutoGroupComicMapper.MAPPER::toDTO).toList();
  }

  @Override
  public void create(List<GroupComicDTO> groupComicDTOs) throws BusinessException {
    validExistingObject(groupComicDTOs);
    Map<String, Category> mapRelation = getRelation(groupComicDTOs);
    Set<GroupComic> groupComics = getGroupComics(groupComicDTOs, mapRelation);
    groupComicRepo.saveAll(groupComics);
  }

  private void validExistingObject(List<GroupComicDTO> groupComicDTOs) {
    Set<String> names = groupComicDTOs.stream().map(GroupComicDTO::getName)
        .collect(Collectors.toSet());
    List<GroupComic> groupComics = groupComicRepo.findAllById(names);
    validator.validate(new HashSet<>(groupComics));
  }

  private Map<String, Category> getRelation(List<GroupComicDTO> groupComicDTOs) {
    Set<String> relationIDs = new HashSet<>();
    for (GroupComicDTO groupComicDTO : groupComicDTOs) {
      relationIDs.addAll(groupComicDTO.getCategoryIDs());
    }
    List<Category> categories = categoryRepo.findAllById(relationIDs);
    validator.validate(relationIDs, new HashSet<>(categories));
    return categories.stream().collect(Collectors.toMap(Category::getName, Function.identity()));
  }

  private Set<GroupComic> getGroupComics(List<GroupComicDTO> groupComicDTOs,
      Map<String, Category> mapRelation) {
    return groupComicDTOs.stream().map(groupComicDTO -> {
      GroupComic groupComic = AutoGroupComicMapper.MAPPER.toEntity(groupComicDTO);
      Set<String> categoryIDs = groupComicDTO.getCategoryIDs();
      Set<Category> categories = categoryIDs.stream().map(mapRelation::get)
          .collect(Collectors.toSet());
      groupComic.setCategories(categories);
      return groupComic;
    }).collect(Collectors.toSet());
  }

  /**
   * Support category
   *
   * @param groupComicDTOs group comic DTO
   * @throws BusinessException exception
   */
  @Override
  public void update(List<GroupComicDTO> groupComicDTOs) throws BusinessException {
    Map<String, GroupComicDTO> groupComicDTOMap = groupComicDTOs.stream().collect(
        Collectors.toMap(GroupComicDTO::getName, Function.identity())
    );
    Set<String> keys = groupComicDTOMap.keySet();
    Set<GroupComic> existingGroupComics = new HashSet<>(groupComicRepo.findAllById(keys));
    validator.validate(
        () -> keys.size() == existingGroupComics.size(),
        ValidationMessageEnum.NOT_EXIST.getMessage()
    );
    Map<String, Category> map = getRelation(groupComicDTOs);
    Set<GroupComic> groupComics = existingGroupComics.stream().map(groupComic -> {
      GroupComicDTO groupComicDTO = groupComicDTOMap.get(groupComic.getName());
      groupComic = AutoGroupComicMapper.MAPPER.toEntity(groupComicDTO, groupComic);
      Set<Category> categories =
          groupComicDTO.getCategoryIDs().stream().map(map::get).collect(Collectors.toSet());
      groupComic.setCategories(categories);
      return groupComic;
    }).collect(Collectors.toSet());
    groupComicRepo.saveAll(groupComics);
  }
}
