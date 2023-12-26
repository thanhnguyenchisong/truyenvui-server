package com.sky.tv.comics.mapper;

import com.sky.tv.comics.dto.GroupComicDTO;
import com.sky.tv.comics.entity.GroupComic;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoGroupComicMapper extends AutoMapper<GroupComic, GroupComicDTO> {

    AutoGroupComicMapper MAPPER = Mappers.getMapper(AutoGroupComicMapper.class);
}
