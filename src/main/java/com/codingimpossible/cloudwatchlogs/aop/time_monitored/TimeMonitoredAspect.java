package com.codingimpossible.cloudwatchlogs.aop.time_monitored;

import com.codingimpossible.cloudwatchlogs.logging.impl.CloudWatchLogger;
import com.codingimpossible.cloudwatchlogs.models.CustomMetric;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeMonitoredAspect {

    @Autowired
    private CloudWatchLogger cloudWatchLogger;

    @Around("@annotation(TimeMonitored)")
    public Object monitorExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();

        logExecutionTime(endTime - startTime);

        return proceed;
    }

    private void logExecutionTime(long time) {
        CustomMetric executionTimeMetric = new CustomMetric();
        executionTimeMetric.metricDimensionName = "ServerId";
        executionTimeMetric.metricDimensionValue = "server-01";
        executionTimeMetric.metricName = "billingExecutionTime";
        executionTimeMetric.metricValue = time;

        cloudWatchLogger.logMetric(executionTimeMetric);
    }

}
