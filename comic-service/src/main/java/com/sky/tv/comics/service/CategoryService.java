package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.CategoryDTO;
import java.util.List;

public interface CategoryService extends BaseService<CategoryDTO>{
  List<CategoryDTO> getAll();
}
