package com.itsz.portfolio.service;

import com.google.common.annotations.VisibleForTesting;
import com.itsz.portfolio.datastore.PortfolioPosition;
import com.itsz.portfolio.datastore.PortfolioPositionDataStore;
import com.itsz.portfolio.entity.Security;
import com.itsz.portfolio.repository.SecurityRepository;
import com.itsz.portfolio.service.model.PortfolioSubscription;
import com.itsz.portfolio.service.model.SecurityPriceEvent;
import com.itsz.portfolio.util.PortfolioOutputUtil;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class PortfolioListener {

    private final SecurityRepository securityRepository;
    private final PortfolioPositionDataStore positionDataStore;

    public PortfolioListener(SecurityRepository securityRepository, PortfolioPositionDataStore positionDataStore) {
        this.securityRepository = securityRepository;
        this.positionDataStore = positionDataStore;
    }

    @EventListener
    public void handlePriceUpdate(SecurityPriceEvent event) {
        List<PortfolioSubscription> portfolioSubscriptions = securityRepository.findAll()
                .stream()
                .filter(positionDataStore.subscribed())
                .map(this::buildPortfolioSubscription)
                .toList();
       PortfolioOutputUtil.printOutPortfolio(portfolioSubscriptions);
    }

    @VisibleForTesting
    PortfolioSubscription buildPortfolioSubscription(Security security) {
        String identifier = security.getIdentifier();
        BigDecimal price = security.getCurrent();
        BigDecimal position = getPosition(identifier);
        BigDecimal marketValue = position.multiply(price);
        return new PortfolioSubscription(identifier, position, price, marketValue);
    }

    private BigDecimal getPosition(String identifier) {
        PortfolioPosition portfolioPosition = positionDataStore.getPosition(identifier);
        return portfolioPosition == null ? BigDecimal.ZERO : BigDecimal.valueOf(portfolioPosition.getPosition());
    }
}
