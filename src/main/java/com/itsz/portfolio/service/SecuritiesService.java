package com.itsz.portfolio.service;

import com.itsz.portfolio.entity.Security;
import com.itsz.portfolio.repository.SecurityRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SecuritiesService {
    private final SecurityRepository securityRepository;
    private final SecurityPricePublisher pricePublisher;

    public SecuritiesService(SecurityRepository securityRepository, SecurityPricePublisher pricePublisher) {
        this.securityRepository = securityRepository;
        this.pricePublisher = pricePublisher;
    }

    public List<Security> listSecurities() {
        return securityRepository.findAll();
    }

    public void receivePriceUpdate(String identifier, BigDecimal price) {
        List<Security> securities = securityRepository.findByIdentifierStartsWith(identifier)
                        .stream()
                .map()


        pricePublisher.publishPrice(identifier, price);
    }
}
