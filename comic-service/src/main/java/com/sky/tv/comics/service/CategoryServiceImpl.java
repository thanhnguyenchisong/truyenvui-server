package com.sky.tv.comics.service;

import com.sky.tv.comics.constant.ValidationMessageEnum;
import com.sky.tv.comics.dto.CategoryDTO;
import com.sky.tv.comics.entity.Category;
import com.sky.tv.comics.exception.BusinessException;
import com.sky.tv.comics.exception.ResourceNotFoundException;
import com.sky.tv.comics.mapper.AutoCategoryMapper;
import com.sky.tv.comics.repository.CategoryRepo;
import com.sky.tv.comics.service.validation.CrudBusinessValidator;
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
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepo categoryRepo;
  private final CrudBusinessValidator validator;

  @Override
  public List<CategoryDTO> getAll() {
    List<Category> categories = categoryRepo.findAll();
    return categories.stream().map(AutoCategoryMapper.MAPPER::toDTO).toList();
  }

  @Override
  public CategoryDTO get(String id) {
    Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
    return AutoCategoryMapper.MAPPER.toDTO(category);
  }

  @Override
  public List<CategoryDTO> get(List<String> ids) {
    return categoryRepo.findAllById(ids).stream().map(AutoCategoryMapper.MAPPER::toDTO).toList();
  }

  @Override
  public void create(List<CategoryDTO> categoryDTOs) {
    Set<String> categoryIDs = categoryDTOs.stream().map(CategoryDTO::getName).collect(Collectors.toSet());
    Set<Category> existingCategory = new HashSet<>(categoryRepo.findAllById(categoryIDs));
    validator.validate(existingCategory::isEmpty, ValidationMessageEnum.EXIST.getMessage());

    List<Category> categories = categoryDTOs.stream().map(AutoCategoryMapper.MAPPER::toEntity).toList();
    categoryRepo.saveAll(categories);
  }

  /**
   * Update comics Note: No support update relationship with other objects.
   *
   * @param categoryDTOs DTO
   * @throws BusinessException Catch the not found the entity from DTO
   */
  @Override
  public void update(List<CategoryDTO> categoryDTOs) throws BusinessException {
    Map<String, CategoryDTO> mapCategoryDTO = categoryDTOs.stream().collect(Collectors.toMap(CategoryDTO::getName, Function.identity()));
    Set<Category> categoriesExist = new HashSet<>(categoryRepo.findAllById(mapCategoryDTO.keySet()));
    validator.validate(() -> mapCategoryDTO.size() == categoriesExist.size(), ValidationMessageEnum.NOT_EXIST.getMessage());
    List<Category> categories = categoriesExist.stream()
        .map(category -> AutoCategoryMapper.MAPPER.toEntity(mapCategoryDTO.get(category.getName()), category)).toList();
    categoryRepo.saveAll(categories);
  }
}
