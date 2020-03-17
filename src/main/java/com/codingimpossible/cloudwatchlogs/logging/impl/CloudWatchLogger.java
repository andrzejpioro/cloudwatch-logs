package com.codingimpossible.cloudwatchlogs.logging.impl;

import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.cloudwatch.model.Dimension;
import com.amazonaws.services.cloudwatch.model.MetricDatum;
import com.amazonaws.services.cloudwatch.model.PutMetricDataRequest;
import com.codingimpossible.cloudwatchlogs.logging.MetricLogger;
import com.codingimpossible.cloudwatchlogs.models.CustomMetric;
import org.springframework.stereotype.Component;

@Component
public class CloudWatchLogger implements MetricLogger {

    private final AmazonCloudWatch cloudWatch;

    public CloudWatchLogger() {
        cloudWatch = AmazonCloudWatchClientBuilder.defaultClient();
    }

    @Override
    public String logMetric(CustomMetric metric) {
        PutMetricDataRequest request = new PutMetricDataRequest();

        Dimension dimension = new Dimension()
                .withName(metric.metricDimensionName)
                .withValue(metric.metricDimensionValue);

        MetricDatum data = new MetricDatum();
        data.withMetricName(metric.metricName)
                .withValue(metric.metricValue)
                .withDimensions(dimension);

        String metricNamespace = "/codingimpossible/billing-service";
        PutMetricDataRequest putMetricDataRequest = request.withNamespace(metricNamespace)
                .withMetricData(data);

        cloudWatch.putMetricData(putMetricDataRequest);

        return "Success";
    }

}
