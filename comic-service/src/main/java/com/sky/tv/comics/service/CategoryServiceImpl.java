package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.CategoryDTO;
import com.sky.tv.comics.entity.Category;
import com.sky.tv.comics.exception.ComicServiceBusinessException;
import com.sky.tv.comics.mapper.AutoCategoryMapper;
import com.sky.tv.comics.repository.CategoryRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryRepo categoryRepo;

  @Override
  public List<CategoryDTO> getAllCategory() {
    List<Category> categories = categoryRepo.findAll();
    return categories.stream()
        .map(AutoCategoryMapper.MAPPER::mapToCategoryDTO)
        .toList();
  }

  @Override
  public void createCategories(List<CategoryDTO> categoryDTOs) {
    List<Category> categories = categoryDTOs.stream().map(AutoCategoryMapper.MAPPER::mapToCategory).toList();
    categoryRepo.saveAll(categories);
  }

  @Override
  public void updateCategories(List<CategoryDTO> categoryDTOs) throws ComicServiceBusinessException {
    List<Category> categories = categoryDTOs.stream().map(AutoCategoryMapper.MAPPER::mapToCategory).toList();
    List<Category> resultFromDB = categoryRepo.findAllById(categories.stream().map(Category::getId).toList());
    if(resultFromDB.size() != categories.size()) throw new ComicServiceBusinessException("Can't found out ids in DB to update, please recheck");
    categoryRepo.saveAll(categories);
  }
}
