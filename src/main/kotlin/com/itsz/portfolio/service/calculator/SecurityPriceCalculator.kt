package com.itsz.portfolio.service.calculator

import com.itsz.portfolio.entity.Security
import com.itsz.portfolio.entity.SecurityType
import java.math.BigDecimal

interface SecurityPriceCalculator {
    fun calculate(security: Security, price: BigDecimal): BigDecimal
    fun getSecurityType(): SecurityType
}
