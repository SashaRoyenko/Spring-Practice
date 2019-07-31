package com.spring.aspects;

import com.spring.Logger.EventLogger;
import com.spring.beans.Event;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ConsoleLoggerLimitAspect {

    private final int MAX_COUNT;

    private final EventLogger OTHER_LOGGER;

    private int currentCount = 0;

    public ConsoleLoggerLimitAspect(int MAX_COUNT, EventLogger OTHER_LOGGER) {
        this.MAX_COUNT = MAX_COUNT;
        this.OTHER_LOGGER = OTHER_LOGGER;
    }

    @Around("consoleLoggerMethods() && args(evt)")
    public void aroundLogEvent(ProceedingJoinPoint jp, Event evt){
        if(currentCount < MAX_COUNT){
            System.out.println("ConsoleEventLogger max count hasn't reached...");
            currentCount++;
            try {
                jp.proceed(new Object[]{evt});
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        else {
            System.out.println("ConsoleEventLogger max count has been reached.");
            OTHER_LOGGER.logEvent(evt);
        }
    }
}
