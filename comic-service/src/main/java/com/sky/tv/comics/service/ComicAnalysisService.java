package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.ComicAnalysisDTO;
import com.sky.tv.comics.entity.ComicAnalysis;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ComicAnalysisService {

  ComicAnalysis create(UUID comicID, Date startTime, Date endTime);

  void updateViewNumber(ComicAnalysis comicAnalysis, int number);

  void preJobView(UUID comicID);

  List<ComicAnalysis> getCAByStartDate(Date startDate);

  List<ComicAnalysisDTO> getComicAnalysisByView(String startDate, String endDate,
      int numberOfComic);

  List<ComicAnalysisDTO> getComicAnalysisByLike(String startDate, String endDate,
      int numberOfComic);
}
