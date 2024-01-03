package com.sky.tv.comics.mapper;

import com.sky.tv.comics.dto.ComicDTO;
import com.sky.tv.comics.dto.GroupComicDTO;
import com.sky.tv.comics.entity.Category;
import com.sky.tv.comics.entity.Comic;
import com.sky.tv.comics.entity.GroupComic;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoGroupComicMapper extends AutoMapper<GroupComic, GroupComicDTO> {

    AutoGroupComicMapper MAPPER = Mappers.getMapper(AutoGroupComicMapper.class);

    @Override
    @Mapping(source = "categories", target = "categoryIDs", qualifiedByName = "categoryObjectToID" )
    GroupComicDTO toDTO(GroupComic e);
    @Named("categoryObjectToID")
    static Set<String> categoryObjectToID(Set<Category> categories) {
        return categories.stream().map(Category::getId).collect(Collectors.toSet());
    }
}
