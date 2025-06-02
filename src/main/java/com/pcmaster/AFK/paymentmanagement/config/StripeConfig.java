package com.pcmaster.AFK.paymentmanagement.config;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {
    @Value("${stripe.api.key.secret}")
    private String apiKey;

    public StripeConfig(){
        Stripe.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }
}
