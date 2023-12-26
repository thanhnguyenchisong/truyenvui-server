package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.GroupComicDTO;
import com.sky.tv.comics.exception.ComicServiceBusinessException;
import java.util.List;

public interface GroupComicService {
    List<GroupComicDTO> getAll();

    void create(List<GroupComicDTO> groupComicDTOs);

    void update(List<GroupComicDTO> groupComicDTOs) throws ComicServiceBusinessException;
}
