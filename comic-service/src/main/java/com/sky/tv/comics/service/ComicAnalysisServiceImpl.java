package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.ComicAnalysisDTO;
import com.sky.tv.comics.entity.Comic;
import com.sky.tv.comics.entity.ComicAnalysis;
import com.sky.tv.comics.entity.PeriodTypeEnum;
import com.sky.tv.comics.exception.ResourceNotFoundException;
import com.sky.tv.comics.repository.ComicAnalysisRepo;
import com.sky.tv.comics.repository.ComicRepo;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.spi.Limit;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ComicAnalysisServiceImpl implements ComicAnalysisService {

  @Autowired
  private ComicAnalysisRepo comicAnalysisRepo;

  @Autowired
  private ComicRepo comicRepo;

  @Override
  public ComicAnalysis create(UUID comicID, Date startTime, Date endTime) {
    Comic comic =  comicRepo.findById(comicID).orElseThrow(() -> new ResourceNotFoundException("Comic", "id", comicID.toString()));
    ComicAnalysis comicAnalysis = new ComicAnalysis();
    comicAnalysis.setComic(comic);
    comicAnalysis.setPeriodType(PeriodTypeEnum.WEEK);
    comicAnalysis.setStartTime(startTime);
    comicAnalysis.setEndTime(endTime);
    return comicAnalysisRepo.save(comicAnalysis);
  }

  @Override
  public void updateViewNumber(ComicAnalysis comicAnalysis, int number) {
    int comicViewNumber = comicAnalysis.getComic().getNumberView();
    int analysisViewNumber = comicAnalysis.getNumberView();
    comicAnalysis.getComic().setNumberView(comicViewNumber + number);
    comicAnalysis.setNumberView(analysisViewNumber + number);
    comicAnalysisRepo.save(comicAnalysis);
  }

  @Override
  @Async
  public void preJobView(UUID comicID) {
    LocalDate today = LocalDate.now();
    LocalDate monday = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    LocalDate mondayNext = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
    Date startTime = Date.from(monday.atStartOfDay(ZoneId.systemDefault()).toInstant());
    Date endTime = Date.from(mondayNext.atStartOfDay(ZoneId.systemDefault()).toInstant());
    List<ComicAnalysis> comicAnalyses = getCAByStartDate(startTime);
    if(comicAnalyses.size() > 1) {
      log.warn("There are more than 1 ComicAnalysis record for this week");
    }
    ComicAnalysis comicAnalysis = comicAnalyses.stream().findFirst().orElse(null);
    if (comicAnalysis == null) {
      comicAnalysis = create(comicID, startTime, endTime);
    }
    updateViewNumber(comicAnalysis, 1);
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
