package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.ComicDTO;
import com.sky.tv.comics.dto.response.BundlePagingResponse;
import com.sky.tv.comics.dto.request.GetComicPaging;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

public interface ComicService extends BaseService<ComicDTO, UUID> {

  /**
   *
   * @param quality
   * @return
   */
  List<ComicDTO> getComicPopular(int quality) throws ParseException;


  List<BundlePagingResponse<ComicDTO>> getComicPaging(GetComicPaging paging);

}
