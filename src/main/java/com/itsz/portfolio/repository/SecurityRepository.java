package com.itsz.portfolio.repository;

import com.itsz.portfolio.entity.Security;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityRepository extends JpaRepository<Security, Long> {
}
