package com.itsz.portfolio.util;

import com.google.common.collect.HashBasedTable;
import com.itsz.portfolio.service.model.PortfolioSubscription;

import java.math.BigDecimal;
import java.util.List;

public class PortfolioOutputUtil {
    private static final String SYMBOL_COLUMN = "Symbol";
    private static final String PRICE_COLUMN = "Price";
    private static final String QTY_COLUMN = "Qty";
    private static final String MARKET_VALUE_COLUMN = "Value";

    private PortfolioOutputUtil() {
    }

    private  static HashBasedTable<String, String, BigDecimal> buildPortfolioTable(List<PortfolioSubscription> portfolioSubscriptions){
        HashBasedTable<String, String, BigDecimal> portfolioTable = HashBasedTable.create();
        portfolioSubscriptions.forEach(subscription -> {
            String identifier = subscription.getIdentifier();
            portfolioTable.put(identifier, PRICE_COLUMN, subscription.getPrice());
            portfolioTable.put(identifier, QTY_COLUMN, subscription.getQuantity());
            portfolioTable.put(identifier, MARKET_VALUE_COLUMN,subscription.getMarketValue());
        });
        return portfolioTable;
    }

    public static void printOutPortfolio(List<PortfolioSubscription> portfolioSubscriptions){
        HashBasedTable<String, String, BigDecimal> portfolioTable = buildPortfolioTable(portfolioSubscriptions);
        System.out.println("## Portfolio");
        System.out.printf("%-24s | %-8s | %-10s | %-6s%n", SYMBOL_COLUMN, PRICE_COLUMN, QTY_COLUMN, MARKET_VALUE_COLUMN);
        for (String rowKey : portfolioTable.rowKeySet()) {
            BigDecimal price = portfolioTable.get(rowKey, PRICE_COLUMN);
            BigDecimal qty = portfolioTable.get(rowKey, QTY_COLUMN);
            BigDecimal value = portfolioTable.get(rowKey, MARKET_VALUE_COLUMN);
            System.out.printf("%-24s | %-8s | %-10s | %-6s%n", rowKey, price, qty, value);
        }
        System.out.printf("%-45s  %s", "Total portfolio", "50000.00");
    }


}
