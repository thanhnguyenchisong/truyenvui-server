package com.sky.tv.comics.anotation.mesurement;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Slf4j
@Aspect
@Component
public class LogExecutionTimeAspect {

  @Around("@annotation(logExecutionTime)")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint, LogExecutionTime logExecutionTime)
      throws Throwable {
    long start = System.currentTimeMillis();
    Object proceed = joinPoint.proceed();
    long executionTime = System.currentTimeMillis() - start;
//		log.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");
    return proceed;
  }
}
