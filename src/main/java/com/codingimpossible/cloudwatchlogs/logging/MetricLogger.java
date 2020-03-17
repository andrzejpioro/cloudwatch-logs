package com.codingimpossible.cloudwatchlogs.logging;

import com.codingimpossible.cloudwatchlogs.models.CustomMetric;

public interface MetricLogger {

    String logMetric(CustomMetric event);

}
