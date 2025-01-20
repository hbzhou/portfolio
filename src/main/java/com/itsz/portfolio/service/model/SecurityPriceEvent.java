package com.itsz.portfolio.service.model;

import java.math.BigDecimal;

public class SecurityPriceEvent {

    private String identifier;
    private BigDecimal price;

    public SecurityPriceEvent(String identifier, BigDecimal price) {
        this.identifier = identifier;
        this.price = price;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
