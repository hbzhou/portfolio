package com.itsz.portfolio.service.calculator

import com.itsz.portfolio.entity.Security
import com.itsz.portfolio.entity.SecurityType
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate

@Component
internal class CallOptionPriceCalculator : SecurityPriceCalculator {

    override fun calculate(security: Security, price: BigDecimal): BigDecimal {
        val years = (security.maturity!!.year - LocalDate.now().year).toDouble()
        return BigDecimal.valueOf(
            BlackScholesUtil.calculateCallOptionPrice(
                price.toDouble(), security.strike!!.toDouble(), years
            )
        ).setScale(2, RoundingMode.HALF_UP)
    }

    override fun getSecurityType(): SecurityType = SecurityType.CALL
}
