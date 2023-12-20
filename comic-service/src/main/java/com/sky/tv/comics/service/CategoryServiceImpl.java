package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.CategoryDTO;
import com.sky.tv.comics.entity.Category;
import com.sky.tv.comics.mapper.AutoCategoryMapper;
import com.sky.tv.comics.repository.CategoryRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryRepo categoryRepository;

  @Override
  public List<CategoryDTO> getAllCategory() {
    List<Category> categories = categoryRepository.findAll();
    return categories.stream()
        .map(AutoCategoryMapper.MAPPER::mapToCategoryDTO)
        .toList();
  }
}
