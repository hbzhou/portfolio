package com.itsz.portfolio.service.calculator;

import com.itsz.portfolio.entity.Security;
import com.itsz.portfolio.entity.SecurityType;

import java.math.BigDecimal;

public interface SecurityPriceCalculator {

    BigDecimal calculate(Security security, BigDecimal price);

    SecurityType getSecurityType();
}
