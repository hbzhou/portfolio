package com.itsz.portfolio.repository;

import com.itsz.portfolio.entity.Security;
import com.itsz.portfolio.entity.SecurityType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class SecurityRepositoryTest {

    @Autowired
    private SecurityRepository securityRepository;

    @BeforeEach
    public void setUp() {
        securityRepository.deleteAll();
    }

    @Test
    public void testFindByIdentifierStartsWith() {
        Security security1 = new Security("AAPL", SecurityType.STOCK,  null, null, null);
        Security security2 = new Security("AAPL-OCT-2020-110-C",  SecurityType.CALL, new BigDecimal("110.0"), null, LocalDate.now().plusDays(30));
        Security security3 = new Security("GOOGL",  SecurityType.STOCK, null, null, null);
        securityRepository.saveAll(List.of(security1, security2, security3));

        List<Security> result = securityRepository.findByIdentifierStartsWith("AAPL");

        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(s -> s.getIdentifier().equals("AAPL")));
        assertTrue(result.stream().anyMatch(s -> s.getIdentifier().equals("AAPL-OCT-2020-110-C")));
    }

    @Test
    public void testFindByIdentifierStartsWith_NoMatches() {
        Security security1 = new Security("AAPL", SecurityType.STOCK, null, null, null);
        Security security2 = new Security("AAPL-OCT-2020-110-C",  SecurityType.CALL, new BigDecimal("110.0"), null, LocalDate.now().plusDays(30));
        securityRepository.saveAll(List.of(security1, security2));

        List<Security> result = securityRepository.findByIdentifierStartsWith("GOOGL");

        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindByIdentifierStartsWith_EmptyDatabase() {
        List<Security> result = securityRepository.findByIdentifierStartsWith("AAPL");
        assertTrue(result.isEmpty());
    }
}