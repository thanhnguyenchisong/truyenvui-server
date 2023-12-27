package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.ChapterDTO;
import com.sky.tv.comics.exception.BusinessException;
import java.util.List;
import java.util.UUID;

public interface ChapterService {

  /**
   * Get all chapters by comic ID
   *
   * @param comicID
   * @return
   * @throws BusinessException
   */
  List<ChapterDTO> getAll(UUID comicID);

  void create(List<ChapterDTO> chapterDTOs);

  void update(List<ChapterDTO> chapterDTOs) throws BusinessException;
}
