package com.sky.tv.comics.anotation;

import com.sky.tv.comics.service.ComicAnalysisService;
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
        UUID comicID = (UUID) joinPoint.getArgs()[0];
        comicAnalysisService.preJobView(comicID);
        return joinPoint.proceed();
    }

}
