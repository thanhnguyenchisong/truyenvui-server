package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.CategoryDTO;
import com.sky.tv.comics.exception.ComicServiceBusinessException;
import java.util.List;

public interface CategoryService extends BaseService<CategoryDTO>{
  public List<CategoryDTO> getAll();
}
