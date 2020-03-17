package com.codingimpossible.cloudwatchlogs.services.impl;

import com.codingimpossible.cloudwatchlogs.aop.time_monitored.TimeMonitored;
import com.codingimpossible.cloudwatchlogs.services.BillingService;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PayPalBillingService implements BillingService {

    @Override
    @TimeMonitored
    public String chargeAccount(String accountId, String accountEmail, double amount) throws InterruptedException {
        Random random = new Random();
        int min = 1;
        int max = 100;
        Thread.sleep(random.nextInt((max - min) + 1) + min);
        return "Success: Your account: " + accountEmail + " was charged " + amount + " $";
    }

}
