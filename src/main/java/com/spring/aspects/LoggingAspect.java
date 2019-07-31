package com.spring.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethods() {
    }

    @Pointcut("allLogEventMethods() && within(*.*File*Logger)")
    private void logEvenInsideFileLoggers() {
    }

    @Before("allLogEventMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before: "
                + joinPoint.getTarget().getClass().getSimpleName()
                + " " +
                joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut="allLogEventMethods()",
            returning="retVal")
    public void logAfter(Object retVal) {
        System.out.println("AFTER_RET: " + retVal);
    }

    @AfterThrowing(pointcut="allLogEventMethods()",
            throwing="ex")
    public void logAfterThrow(Throwable ex) {
        System.out.println("AFTER_THR: " + ex);
    }
}
