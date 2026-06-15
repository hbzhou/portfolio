package com.itsz.portfolio.service

import com.itsz.portfolio.entity.SecurityType
import com.itsz.portfolio.repository.SecurityRepository
import com.itsz.portfolio.service.calculator.SecurityPriceCalculator
import com.itsz.portfolio.service.model.SecurityPriceEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class SecuritiesService(
    private val securityRepository: SecurityRepository,
    private val eventPublisher: ApplicationEventPublisher,
    priceCalculators: List<SecurityPriceCalculator>
) {
    private val calculatorMap: Map<SecurityType, SecurityPriceCalculator> =
        priceCalculators.associateBy { it.getSecurityType() }

    fun receivePriceUpdate(identifier: String, price: BigDecimal) {
        println("$identifier change to $price")
        val securities = securityRepository.findByIdentifierStartsWith(identifier)
            .onEach { security ->
                security.current = calculatorMap[security.type]!!.calculate(security, price)
            }
        securityRepository.saveAll(securities)
        eventPublisher.publishEvent(SecurityPriceEvent(identifier, price))
    }
}
