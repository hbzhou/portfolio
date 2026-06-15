package com.itsz.portfolio.service.calculator

import com.itsz.portfolio.entity.Security
import com.itsz.portfolio.entity.SecurityType
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
internal class StockPriceCalculator : SecurityPriceCalculator {
    override fun calculate(security: Security, price: BigDecimal): BigDecimal = price
    override fun getSecurityType(): SecurityType = SecurityType.STOCK
}
