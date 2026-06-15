package com.itsz.portfolio.service

import com.itsz.portfolio.datastore.PortfolioPositionDataStore
import com.itsz.portfolio.entity.Security
import com.itsz.portfolio.repository.SecurityRepository
import com.itsz.portfolio.service.model.PortfolioSubscription
import com.itsz.portfolio.service.model.SecurityPriceEvent
import com.itsz.portfolio.util.PortfolioOutputUtil
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class PortfolioListener(
    private val securityRepository: SecurityRepository,
    private val positionDataStore: PortfolioPositionDataStore
) {
    @EventListener
    fun handlePriceUpdate(event: SecurityPriceEvent) {
        val subscriptions = securityRepository.findAll()
            .filter(positionDataStore.subscribed())
            .map { buildPortfolioSubscription(it) }
        PortfolioOutputUtil.printOutPortfolio(subscriptions)
    }

    internal fun buildPortfolioSubscription(security: Security): PortfolioSubscription {
        val identifier = security.identifier!!
        val price = security.current ?: BigDecimal.ZERO
        val position = positionDataStore.getPosition(identifier)
            ?.let { BigDecimal.valueOf(it.position) } ?: BigDecimal.ZERO
        return PortfolioSubscription(identifier, position, price, position.multiply(price))
    }
}
