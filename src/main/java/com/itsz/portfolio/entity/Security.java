package com.itsz.portfolio.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "securities")
public class Security {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identifier;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private SecurityType type;

    private BigDecimal current;

    private BigDecimal strike;

    private LocalDate maturity;

    public Security() {
    }

    public Security(String identifier, SecurityType type, BigDecimal current, BigDecimal strike, LocalDate maturity) {
        this.identifier = identifier;
        this.type = type;
        this.current = current;
        this.strike = strike;
        this.maturity = maturity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public SecurityType getType() {
        return type;
    }

    public void setType(SecurityType type) {
        this.type = type;
    }

    public BigDecimal getCurrent() {
        return current;
    }

    public void setCurrent(BigDecimal current) {
        this.current = current;
    }

    public BigDecimal getStrike() {
        return strike;
    }

    public void setStrike(BigDecimal strike) {
        this.strike = strike;
    }

    public LocalDate getMaturity() {
        return maturity;
    }

    public void setMaturity(LocalDate maturity) {
        this.maturity = maturity;
    }
}
