package com.itsz.portfolio.datastore

import com.itsz.portfolio.entity.Security
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component

@Component
class PortfolioPositionDataStore : InitializingBean {

    @Value("classpath:portfolio_position.csv")
    private lateinit var resource: Resource

    private lateinit var portfolioPositions: Map<String, PortfolioPosition>

    override fun afterPropertiesSet() {
        portfolioPositions = load()
    }

    private fun load(): Map<String, PortfolioPosition> =
        resource.inputStream.bufferedReader().useLines { lines ->
            lines.drop(1)
                .map { construct(it) }
                .associateBy { it.identifier }
        }

    fun subscribed(): (Security) -> Boolean =
        { security -> portfolioPositions.containsKey(security.identifier) }

    fun getPosition(identifier: String): PortfolioPosition? =
        portfolioPositions[identifier]

    companion object {
        fun construct(row: String): PortfolioPosition {
            val columns = row.split(",")
            return PortfolioPosition(columns[0], columns[1].toLong())
        }
    }
}
