package com.sky.tv.comics.mapper;

import com.sky.tv.comics.dto.ComicDTO;
import com.sky.tv.comics.entity.Category;
import com.sky.tv.comics.entity.Comic;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * That should have same field name for default mapping. In the case different in field name we have
 * {@link org.mapstruct.Mapping} for that method - with define source name and target name. That
 * will process with that annotation configuration
 */
@Mapper
public interface AutoComicMapper extends AutoMapper<Comic, ComicDTO> {
  AutoComicMapper MAPPER = Mappers.getMapper(AutoComicMapper.class);

  @Override
  @Mapping(source = "categories", target = "categoryIDs", qualifiedByName = "categoryObjectToID" )
  ComicDTO toDTO(Comic e);

  @Named("categoryObjectToID")
  static Set<String> categoryObjectToID(Set<Category> categories) {
    return categories.stream().map(Category::getId).collect(Collectors.toSet());
  }
}
