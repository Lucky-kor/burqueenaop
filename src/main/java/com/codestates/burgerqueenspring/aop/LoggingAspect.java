package com.codestates.burgerqueenspring.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

@Aspect
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(public int com.codestates.burgerqueenspring..*.*(..))")
    private void targetMethod(){}

    @Pointcut("execution(public void com.codestates.burgerqueenspring..*.*(..))")
    private void targetMethod2(){}

    @Before("targetMethod() || targetMethod2()")
    public void log(JoinPoint joinPoint) throws Throwable {

        // 메서드의 정보 불러오기
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        log.info("⭐️ 메서드 호출️ ⭐");
        log.info("============= 메서드명 = {} =============", method.getName());

        // 파라미터 정보 불러오기
        Object[] args = joinPoint.getArgs();
        if(args.length <= 0) log.info("[파라미터] 없음");
        for (Object arg : args) {
            log.info("[파라미터 타입] = {}, [파라미터 값] = {}", arg.getClass().getSimpleName(), arg);
        }
    }

    @AfterReturning(value = "targetMethod() || targetMethod2()", returning = "result")
    public void returningLog(JoinPoint joinPoint, int result) {

        // 메서드 정보 받아오기
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        log.info("⭐️ 메서드 호출 후 ⭐");
        log.info("============= 메서드명 = {} =============", method.getName());
        log.info("[반환 값] = {}원", result);
    }
}
