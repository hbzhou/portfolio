package com.itsz.portfolio.controller.dto;

import java.math.BigDecimal;

public class SecurityPriceUpdateDto {
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
