package com.itsz.portfolio.service.calculator;

import com.itsz.portfolio.entity.Security;
import com.itsz.portfolio.entity.SecurityType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Component
final class CallOptionPriceCalculator implements SecurityPriceCalculator {

    @Override
    public BigDecimal calculate(Security security, BigDecimal price) {
        int years = security.getMaturity().getYear() - LocalDate.now().getYear();
        return BigDecimal.valueOf(BlackScholesUtil.calculateCallOptionPrice(price.doubleValue(), security.getStrike().doubleValue(), years)).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public SecurityType getSecurityType() {
        return SecurityType.CALL;
    }


}
