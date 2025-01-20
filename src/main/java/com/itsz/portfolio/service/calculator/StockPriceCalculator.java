package com.itsz.portfolio.service.calculator;

import com.itsz.portfolio.entity.Security;
import com.itsz.portfolio.entity.SecurityType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
final class StockPriceCalculator implements SecurityPriceCalculator {
    @Override
    public BigDecimal calculate(Security security, BigDecimal price) {
        return price;
    }

    @Override
    public SecurityType getSecurityType() {
        return SecurityType.STOCK;
    }
}
