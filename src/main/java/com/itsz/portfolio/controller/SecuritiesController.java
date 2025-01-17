package com.itsz.portfolio.controller;

import com.itsz.portfolio.entity.Security;
import com.itsz.portfolio.repository.SecurityRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/securities")
public class SecuritiesController {
    private final SecurityRepository securityRepository;

    public SecuritiesController(SecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }

    @GetMapping
    public List<Security> listSecurities(){
        return securityRepository.findAll();
    }
}
