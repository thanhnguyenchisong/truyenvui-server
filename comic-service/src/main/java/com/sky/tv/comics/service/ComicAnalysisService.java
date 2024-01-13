package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.ComicAnalysisDTO;
import com.sky.tv.comics.entity.ComicAnalysis;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ComicAnalysisService {

  void create(UUID comicID, Date startTime, Date endTime);

  List<ComicAnalysis> getCAByStartDate(Date startDate);

  List<ComicAnalysisDTO> getComicAnalysisByView(String startDate, String endDate,
      int numberOfComic);

  List<ComicAnalysisDTO> getComicAnalysisByLike(String startDate, String endDate,
      int numberOfComic);
}
