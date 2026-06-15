package com.itsz.portfolio.repository

import com.itsz.portfolio.entity.Security
import com.itsz.portfolio.entity.SecurityType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.math.BigDecimal
import java.time.LocalDate

@DataJpaTest
class SecurityRepositoryTest {

    @Autowired
    private lateinit var securityRepository: SecurityRepository

    @BeforeEach
    fun setUp() {
        securityRepository.deleteAll()
    }

    @Test
    fun `find by identifier starts with returns matching securities`() {
        val security1 = Security("AAPL", SecurityType.STOCK, null, null, null)
        val security2 = Security("AAPL-OCT-2020-110-C", SecurityType.CALL, BigDecimal("110.0"), null, LocalDate.now().plusDays(30))
        val security3 = Security("GOOGL", SecurityType.STOCK, null, null, null)
        securityRepository.saveAll(listOf(security1, security2, security3))

        val result = securityRepository.findByIdentifierStartsWith("AAPL")

        assertEquals(2, result.size)
        assertTrue(result.any { it.identifier == "AAPL" })
        assertTrue(result.any { it.identifier == "AAPL-OCT-2020-110-C" })
    }

    @Test
    fun `find by identifier starts with returns empty when no matches`() {
        val security1 = Security("AAPL", SecurityType.STOCK, null, null, null)
        val security2 = Security("AAPL-OCT-2020-110-C", SecurityType.CALL, BigDecimal("110.0"), null, LocalDate.now().plusDays(30))
        securityRepository.saveAll(listOf(security1, security2))

        val result = securityRepository.findByIdentifierStartsWith("GOOGL")

        assertTrue(result.isEmpty())
    }

    @Test
    fun `find by identifier starts with returns empty when database is empty`() {
        val result = securityRepository.findByIdentifierStartsWith("AAPL")
        assertTrue(result.isEmpty())
    }
}
