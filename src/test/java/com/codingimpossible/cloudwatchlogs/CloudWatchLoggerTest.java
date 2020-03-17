package com.codingimpossible.cloudwatchlogs;

import com.codingimpossible.cloudwatchlogs.logging.impl.CloudWatchLogger;
import com.codingimpossible.cloudwatchlogs.models.CustomMetric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CloudWatchLoggerTest {

    CloudWatchLogger cloudWatchLogger;

    @BeforeEach
    void init() {
        cloudWatchLogger = new CloudWatchLogger();
    }

    @Test
    void testLogEvent() {

        CustomMetric metric = new CustomMetric();
        metric.metricDimensionName = "ServerId";
        metric.metricDimensionValue = "server-02";
        metric.metricName = "billingExecutionTime";
        metric.metricValue = 132;

        String res = cloudWatchLogger.logMetric(metric);
        Assertions.assertNotNull(res);
        Assertions.assertEquals(res, "Success");
    }

}
