package com.codingimpossible.cloudwatchlogs.services;

public interface BillingService {

    String chargeAccount(String accountId, String accountEmail, double amount) throws InterruptedException;

}
