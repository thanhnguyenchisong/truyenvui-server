package com.sky.tv.comics.repository.custom;

import com.sky.tv.comics.entity.ComicAnalysis;
import com.sky.tv.comics.entity.custom.TopComicView;
import java.util.List;

public interface CustomComicAnalysisRepo {

  List<TopComicView> getComicAnalysisByView(String startDate, String endDate, int limit);

  List<ComicAnalysis> getComicAnalysisByLike(String startDate, String endDate, int limit);
}
