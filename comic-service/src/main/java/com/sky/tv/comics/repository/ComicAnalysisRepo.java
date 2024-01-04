package com.sky.tv.comics.repository;

import com.sky.tv.comics.entity.ComicAnalysis;
import com.sky.tv.comics.repository.custom.CustomComicAnalysisRepo;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.hibernate.query.spi.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComicAnalysisRepo extends JpaRepository<ComicAnalysis, UUID>, CustomComicAnalysisRepo {

    List<ComicAnalysis> findByStartTimeGreaterThanEqualOrderByStartTime(Date startTime);
}
