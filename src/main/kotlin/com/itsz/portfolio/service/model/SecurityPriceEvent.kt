package com.itsz.portfolio.service.model

import java.math.BigDecimal

data class SecurityPriceEvent(
    val identifier: String,
    val price: BigDecimal
)
