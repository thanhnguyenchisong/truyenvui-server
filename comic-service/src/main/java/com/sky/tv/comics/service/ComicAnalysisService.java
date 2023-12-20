package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.ComicAnalysisDTO;
import java.util.List;

public interface ComicAnalysisService {

  List<ComicAnalysisDTO> getComicAnalysisByView(String startDate, String endDate,
      int numberOfComic);

  List<ComicAnalysisDTO> getComicAnalysisByLike(String startDate, String endDate,
      int numberOfComic);
}
