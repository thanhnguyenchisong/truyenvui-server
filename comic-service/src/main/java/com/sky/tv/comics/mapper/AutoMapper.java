package com.sky.tv.comics.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

public interface AutoMapper<Entity, DTO> {
    DTO toDTO(Entity e);

    @Mapping(target = "createTime", ignore = true)
    Entity toEntity(DTO dto);

    @Mapping(target = "createTime", ignore = true)
    Entity toEntity(DTO dto, @MappingTarget Entity entity);
}
