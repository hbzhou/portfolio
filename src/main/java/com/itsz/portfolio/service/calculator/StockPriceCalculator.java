package com.itsz.portfolio.service.calculator;

import com.itsz.portfolio.entity.Security;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
final class StockPriceCalculator implements SecurityPriceCalculator {
    @Override
    public BigDecimal calculate(Security security, BigDecimal price) {
        return price;
    }
}
