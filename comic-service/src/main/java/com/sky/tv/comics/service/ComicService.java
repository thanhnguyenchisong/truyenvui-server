package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.ComicDTO;
import com.sky.tv.comics.dto.paging.PagingResponse;
import java.util.List;

public interface ComicService extends BaseService<ComicDTO> {

  /**
   * @param pageNumber
   * @param pageSize
   * @param categories
   * @return
   */
  PagingResponse<ComicDTO> getComicByCategories(int pageNumber, int pageSize,
      List<String> categories);

  /**
   * @param pageNumber
   * @param pageSize
   * @param category
   * @return
   */
  PagingResponse<ComicDTO> getComicByCategory(int pageNumber, int pageSize, String category);

  /**
   * @param pageNumber
   * @param pageSize
   * @return
   */
  PagingResponse<ComicDTO> getComics(int pageNumber, int pageSize);

}
