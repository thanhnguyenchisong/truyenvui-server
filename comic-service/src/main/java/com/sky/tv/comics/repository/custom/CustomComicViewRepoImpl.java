package com.sky.tv.comics.repository.custom;

import com.sky.tv.comics.entity.ComicAnalysis;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomComicViewRepoImpl implements CustomComicViewRepo {
    @Autowired
    private EntityManager entityManager;
    @Override
    public List<ComicAnalysis> getComicAnalysisByView(String startDate, String endDate, int limit) { //will test after
        String formQuery = "SELECT SUM(cv.numberRead) AS numberRead FROM ComicAnalysis cv WHERE cv.startDate > %s AND cv.endDate < %s GROUPING_BY(cv.comic) ORDER BY cv.numberRead";
        String query = String.format(formQuery, startDate, endDate);
        return entityManager.createQuery(query, ComicAnalysis.class).setMaxResults(limit).getResultList();
    }

    @Override
    public List<ComicAnalysis> getComicAnalysisByLike(String startDate, String endDate, int limit) {
        return null;
    }
}
