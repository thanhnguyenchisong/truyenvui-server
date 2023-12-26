package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.CategoryDTO;
import com.sky.tv.comics.entity.Category;
import com.sky.tv.comics.exception.ComicServiceBusinessException;
import com.sky.tv.comics.mapper.AutoCategoryMapper;
import com.sky.tv.comics.repository.CategoryRepo;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryRepo categoryRepo;

  @Override
  public List<CategoryDTO> getAll() {
    List<Category> categories = categoryRepo.findAll();
    return categories.stream()
        .map(AutoCategoryMapper.MAPPER::toDTO)
        .toList();
  }

  @Override
  public CategoryDTO get(UUID id) {
    return null;
  }

  @Override
  public List<CategoryDTO> get(List<UUID> ids) {
    return null;
  }

  @Override
  public void create(List<CategoryDTO> categoryDTOs) {
    List<Category> categories = categoryDTOs.stream().map(AutoCategoryMapper.MAPPER::toEntity).toList();
    categoryRepo.saveAll(categories);
  }

  @Override
  public void update(List<CategoryDTO> categoryDTOs) throws ComicServiceBusinessException {
    List<Category> categories = categoryDTOs.stream().map(AutoCategoryMapper.MAPPER::toEntity).toList();
    List<Category> resultFromDB = categoryRepo.findAllById(categories.stream().map(Category::getName).toList());
    if(resultFromDB.size() != categories.size()) throw new ComicServiceBusinessException("Can't found out ids in DB to update, please recheck");
    categoryRepo.saveAll(categories);
  }
}
