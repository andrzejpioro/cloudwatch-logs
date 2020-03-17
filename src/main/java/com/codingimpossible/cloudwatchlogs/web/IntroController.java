package com.codingimpossible.cloudwatchlogs.web;

import com.codingimpossible.cloudwatchlogs.services.impl.PayPalBillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntroController {

    @Autowired
    private PayPalBillingService payPalBillingService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/bill")
    public String bill() throws InterruptedException {
        return payPalBillingService.chargeAccount("id1", "email@gmail.com", 12.00);
    }
}
