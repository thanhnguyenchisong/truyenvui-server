package com.sky.tv.comics.anotation;

import com.sky.tv.comics.anotation.mesurement.LogExecutionTime;
import com.sky.tv.comics.entity.Comic;
import com.sky.tv.comics.entity.ComicAnalysis;
import com.sky.tv.comics.entity.PeriodTypeEnum;
import com.sky.tv.comics.exception.ResourceNotFoundException;
import com.sky.tv.comics.repository.ComicAnalysisRepo;
import com.sky.tv.comics.repository.ComicRepo;
import com.sky.tv.comics.service.ComicAnalysisService;
import com.sky.tv.comics.utils.DateServiceUtils;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AnalysisAspect {

    @Autowired
    private ComicAnalysisService  comicAnalysisService;

    @Around("@annotation(analysis)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint, Analysis analysis) throws Throwable {
        LocalDate today = LocalDate.now();
        LocalDate monday = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate mondayNext = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        Date startTime = Date.from(monday.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endTime = Date.from(mondayNext.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<ComicAnalysis> comicAnalyses = comicAnalysisService.getCAByStartDate(startTime);
        if(comicAnalyses.size() > 1) {
            log.warn("There are more than 1 ComicAnalysis record for this week");
        }
        ComicAnalysis comicAnalysis = comicAnalyses.stream().findFirst().orElse(null);
        if (comicAnalysis == null) {
            UUID comicID = (UUID) joinPoint.getArgs()[0];
            comicAnalysisService.create(comicID, startTime, endTime);
        }
        return joinPoint.proceed();
    }

}
