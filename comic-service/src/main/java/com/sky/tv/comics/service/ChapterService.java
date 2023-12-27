package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.ChapterDTO;
import com.sky.tv.comics.exception.BusinessException;
import java.util.List;
import java.util.UUID;

public interface ChapterService extends BaseService<ChapterDTO, UUID> {

  /**
   * Get all chapters by comic ID
   *
   * @param comicID
   * @return
   * @throws BusinessException
   */
  List<ChapterDTO> getAllByComic(UUID comicID);
}
