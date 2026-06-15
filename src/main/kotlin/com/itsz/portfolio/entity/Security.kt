package com.itsz.portfolio.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "securities")
class Security(
    var identifier: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    var type: SecurityType? = null,

    var current: BigDecimal? = null,
    var strike: BigDecimal? = null,
    var maturity: LocalDate? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
