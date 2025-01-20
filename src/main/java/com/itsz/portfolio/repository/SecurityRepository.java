package com.itsz.portfolio.repository;

import com.itsz.portfolio.entity.Security;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SecurityRepository extends JpaRepository<Security, Long> {
    List<Security> findByIdentifierStartsWith(String identifier);
}
