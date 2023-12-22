package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.CategoryDTO;
import com.sky.tv.comics.exception.ComicServiceBusinessException;
import java.util.List;

public interface CategoryService {

  List<CategoryDTO> getAllCategory();

  void createCategories(List<CategoryDTO> categoryDTOs);

  void updateCategories(List<CategoryDTO> categoryDTOs) throws ComicServiceBusinessException;
}
