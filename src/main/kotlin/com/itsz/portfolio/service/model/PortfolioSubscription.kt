package com.itsz.portfolio.service.model

import java.math.BigDecimal

data class PortfolioSubscription(
    val identifier: String,
    val quantity: BigDecimal,
    val price: BigDecimal,
    val marketValue: BigDecimal
)
