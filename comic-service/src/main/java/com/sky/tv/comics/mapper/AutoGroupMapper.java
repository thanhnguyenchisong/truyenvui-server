package com.sky.tv.comics.mapper;

import com.sky.tv.comics.dto.GroupComicDTO;
import com.sky.tv.comics.entity.GroupComic;
import org.mapstruct.factory.Mappers;

public interface AutoGroupMapper {

    AutoGroupMapper MAPPER = Mappers.getMapper(AutoGroupMapper.class);

    GroupComicDTO mapToGroupComicDto(GroupComic groupComic);

    GroupComic mapToGroupComic(GroupComicDTO groupComicDTO);

}
