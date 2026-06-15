package com.itsz.portfolio.repository

import com.itsz.portfolio.entity.Security
import org.springframework.data.jpa.repository.JpaRepository

interface SecurityRepository : JpaRepository<Security, Long> {
    fun findByIdentifierStartsWith(identifier: String): List<Security>
}
