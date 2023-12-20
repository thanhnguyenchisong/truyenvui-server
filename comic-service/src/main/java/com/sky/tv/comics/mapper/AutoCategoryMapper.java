package com.sky.tv.comics.mapper;

import com.sky.tv.comics.dto.CategoryDTO;
import com.sky.tv.comics.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoCategoryMapper {

  AutoCategoryMapper MAPPER = Mappers.getMapper(AutoCategoryMapper.class);

  CategoryDTO mapToCategoryDTO(Category comic);

  Category mapToCategory(CategoryDTO comicDTO);
}
