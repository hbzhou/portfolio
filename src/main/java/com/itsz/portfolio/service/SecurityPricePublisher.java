package com.itsz.portfolio.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SecurityPricePublisher {

    private final ApplicationEventPublisher eventPublisher;

    public SecurityPricePublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishPrice(String identifier, BigDecimal price) {
        eventPublisher.publishEvent(new SecurityPriceEvent(identifier, price));
    }
}
