package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.GroupComicDTO;
import com.sky.tv.comics.exception.BusinessException;
import java.util.List;

public interface GroupComicService extends BaseService<GroupComicDTO, String> {
    List<GroupComicDTO> getAll();
}
