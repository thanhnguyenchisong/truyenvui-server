package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.ComicDTO;
import com.sky.tv.comics.dto.response.BundlePagingResponse;
import com.sky.tv.comics.dto.response.PagingResponse;
import com.sky.tv.comics.dto.request.GetComicPaging;
import java.text.ParseException;
import java.util.List;

public interface ComicService extends BaseService<ComicDTO> {

  /**
   *
   * @param quality
   * @return
   */
  List<ComicDTO> getPopular(int quality) throws ParseException;


  List<BundlePagingResponse<ComicDTO>> getComicPaging(GetComicPaging paging);

}
