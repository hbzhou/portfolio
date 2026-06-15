package com.itsz.portfolio.util

import com.google.common.collect.HashBasedTable
import com.itsz.portfolio.service.model.PortfolioSubscription
import java.math.BigDecimal

object PortfolioOutputUtil {

    private const val PRICE_COLUMN = "Price"
    private const val QTY_COLUMN = "Qty"
    private const val MARKET_VALUE_COLUMN = "Value"

    private fun buildPortfolioTable(subscriptions: List<PortfolioSubscription>): HashBasedTable<String, String, BigDecimal> {
        val table = HashBasedTable.create<String, String, BigDecimal>()
        subscriptions.forEach { sub ->
            table.put(sub.identifier, PRICE_COLUMN, sub.price)
            table.put(sub.identifier, QTY_COLUMN, sub.quantity)
            table.put(sub.identifier, MARKET_VALUE_COLUMN, sub.marketValue)
        }
        return table
    }

    fun printOutPortfolio(subscriptions: List<PortfolioSubscription>) {
        val table = buildPortfolioTable(subscriptions)
        println("## Portfolio")
        System.out.printf("%-24s | %-8s | %-10s | %-6s%n", "Symbol", PRICE_COLUMN, QTY_COLUMN, MARKET_VALUE_COLUMN)
        for (rowKey in table.rowKeySet()) {
            val price = table.get(rowKey, PRICE_COLUMN)
            val qty = table.get(rowKey, QTY_COLUMN)
            val value = table.get(rowKey, MARKET_VALUE_COLUMN)
            System.out.printf("%-24s | %-8s | %-10s | %-6s%n", rowKey, price, qty, value)
        }
        val nav = subscriptions.fold(BigDecimal.ZERO) { acc, sub -> acc + sub.marketValue }
        System.out.printf("%-45s  %s%n", "#Total portfolio", nav)
    }
}
