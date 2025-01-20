package com.itsz.portfolio.service.model;

import java.math.BigDecimal;

public class PortfolioSubscription {
    private String identifier;
    private BigDecimal quantity;
    private BigDecimal Price;
    private BigDecimal marketValue;

    public PortfolioSubscription(String identifier, BigDecimal quantity, BigDecimal price, BigDecimal marketValue) {
        this.identifier = identifier;
        this.quantity = quantity;
        Price = price;
        this.marketValue = marketValue;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal price) {
        Price = price;
    }

    public BigDecimal getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }
}

