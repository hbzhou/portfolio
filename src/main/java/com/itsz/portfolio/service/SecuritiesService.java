package com.itsz.portfolio.service;

import com.itsz.portfolio.entity.Security;
import com.itsz.portfolio.entity.SecurityType;
import com.itsz.portfolio.repository.SecurityRepository;
import com.itsz.portfolio.service.calculator.SecurityPriceCalculator;
import com.itsz.portfolio.service.model.SecurityPriceEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SecuritiesService {
    private final SecurityRepository securityRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final Map<SecurityType, SecurityPriceCalculator> priceCalculators;

    public SecuritiesService(SecurityRepository securityRepository, ApplicationEventPublisher eventPublisher, List<SecurityPriceCalculator> priceCalculators) {
        this.securityRepository = securityRepository;
        this.eventPublisher = eventPublisher;
        this.priceCalculators = priceCalculators.stream().collect(Collectors.toMap(SecurityPriceCalculator::getSecurityType, Function.identity()));
    }


    public void receivePriceUpdate(String identifier, BigDecimal price) {
        System.out.printf("%s change to %s\n", identifier, price);
        List<Security> securities = securityRepository.findByIdentifierStartsWith(identifier)
                .stream()
                .peek(security -> mutatePrice(security, price))
                .toList();
        securityRepository.saveAll(securities);
        eventPublisher.publishEvent(new SecurityPriceEvent(identifier, price));
    }

    private void mutatePrice(Security security, BigDecimal price) {
        BigDecimal calculatedPrice = priceCalculators.get(security.getType()).calculate(security, price);
        security.setCurrent(calculatedPrice);
    }

}
