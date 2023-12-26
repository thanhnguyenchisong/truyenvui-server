package com.sky.tv.comics.repository.extend;

import com.sky.tv.comics.entity.ComicAnalysis;
import com.sky.tv.comics.entity.custom.TopComic;
import java.util.List;

public interface CustomComicAnalysisRepo {

    List<TopComic> getComicAnalysisByView(String startDate, String endDate, int limit);

    List<ComicAnalysis> getComicAnalysisByLike(String startDate, String endDate, int limit);
}
