package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.ComicAnalysisDTO;
import com.sky.tv.comics.entity.Comic;
import com.sky.tv.comics.entity.ComicAnalysis;
import com.sky.tv.comics.entity.PeriodTypeEnum;
import com.sky.tv.comics.exception.ResourceNotFoundException;
import com.sky.tv.comics.repository.ComicAnalysisRepo;
import com.sky.tv.comics.repository.ComicRepo;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.hibernate.query.spi.Limit;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComicAnalysisServiceImpl implements ComicAnalysisService {

  @Autowired
  private ComicAnalysisRepo comicAnalysisRepo;

  @Autowired
  private ComicRepo comicRepo;

  @Override
  public void create(UUID comicID, Date startTime, Date endTime) {
    Comic comic =  comicRepo.findById(comicID).orElseThrow(() -> new ResourceNotFoundException("Comic", "id", comicID.toString()));
    ComicAnalysis comicAnalysis = new ComicAnalysis();
    comicAnalysis.setComic(comic);
    comicAnalysis.setPeriodType(PeriodTypeEnum.WEEK);
    comicAnalysis.setStartTime(startTime);
    comicAnalysis.setEndTime(endTime);
    comicAnalysisRepo.save(comicAnalysis);
  }

  @Override
  public void updateViewNumber(ComicAnalysis comicAnalysis, int number) {
    Comic comic =  comicRepo.findById(comicID).orElseThrow(() -> new ResourceNotFoundException("Comic", "id", comicID.toString()));
    ComicAnalysis comicAnalysis = new ComicAnalysis();
    comicAnalysis.setComic(comic);
    comicAnalysis.setPeriodType(PeriodTypeEnum.WEEK);
    comicAnalysis.setStartTime(startTime);
    comicAnalysis.setEndTime(endTime);
    comicAnalysisRepo.save(comicAnalysis);
  }

  @Override
  public List<ComicAnalysis> getCAByStartDate(Date startDate) {
    return comicAnalysisRepo.findByStartTimeGreaterThanEqualOrderByStartTime(startDate);
  }

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
