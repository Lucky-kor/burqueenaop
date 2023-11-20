package com.codestates.burgerqueenspring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LoggingAspectV2 {

  private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

  @Around("execution(public int com.codestates.burgerqueenspring..*.*(..)) || execution(public void com.codestates.burgerqueenspring..*.*(..))")
  public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();

    // 메서드 호출 전 로그
    log.info("⭐️ 메서드 호출️ ⭐");
    log.info("============= 메서드명 = {} =============", method.getName());

    // 파라미터 정보 로그
    Object[] args = joinPoint.getArgs();
    if(args.length <= 0) log.info("[파라미터] 없음");
    for (Object arg : args) {
      log.info("[파라미터 타입] = {}, [파라미터 값] = {}", arg.getClass().getSimpleName(), arg);
    }

    // 메서드 실행
    Object result = joinPoint.proceed();

    // 메서드 호출 후 로그
    log.info("⭐️ 메서드 호출 후 ⭐");
    log.info("============= 메서드명 = {} =============", method.getName());

    if (result != null) {
      log.info("[반환 값] = {}", result);
    }

    return result;
  }
}