package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.ChapterDTO;
import com.sky.tv.comics.exception.ComicServiceBusinessException;
import java.util.List;
import java.util.UUID;

public interface ChapterService {

  /**
   * Get all chapters by comic ID
   *
   * @param comicID
   * @return
   * @throws ComicServiceBusinessException
   */
  List<ChapterDTO> getAllChapter(UUID comicID);

  void createChapters(List<ChapterDTO> chapterDTOs);

  void updateChapters(List<ChapterDTO> chapterDTOs) throws ComicServiceBusinessException;
}
