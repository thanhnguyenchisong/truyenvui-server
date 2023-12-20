package com.sky.tv.comics.repository.custom;

import com.sky.tv.comics.entity.ComicAnalysis;
import java.util.List;

public interface CustomComicViewRepo {
   List<ComicAnalysis> getComicAnalysisByView(String startDate, String endDate, int limit);

   List<ComicAnalysis> getComicAnalysisByLike(String startDate, String endDate, int limit);
}
