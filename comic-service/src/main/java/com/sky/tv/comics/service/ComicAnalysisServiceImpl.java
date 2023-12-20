package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.ComicAnalysisDTO;
import com.sky.tv.comics.repository.ComicViewRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComicAnalysisServiceImpl implements ComicAnalysisService {

  @Autowired
  private ComicViewRepo comicAnalysisRepo;

  @Override
  public List<ComicAnalysisDTO> getComicAnalysisByView(String startDate, String endDate,
      int numberOfComic) {
    return null;
  }

  @Override
  public List<ComicAnalysisDTO> getComicAnalysisByLike(String startDate, String endDate,
      int numberOfComic) {
    return null;
  }
}
