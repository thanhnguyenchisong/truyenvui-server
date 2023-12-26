package com.sky.tv.comics.mapper;

public interface AutoMapper<Entity, DTO> {
    DTO toDTO(Entity e);
    Entity toEntity(DTO dto);
}
