package com.itsz.portfolio.controller;

import com.itsz.portfolio.controller.dto.SecurityPriceUpdateDto;
import com.itsz.portfolio.entity.Security;
import com.itsz.portfolio.service.SecuritiesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/securities")
public class SecuritiesController {
    private final SecuritiesService securitiesService;

    public SecuritiesController(SecuritiesService securitiesService) {
        this.securitiesService = securitiesService;
    }

    @GetMapping
    public List<Security> listSecurities() {
        return securitiesService.listSecurities();
    }

    @PutMapping("/{identifier}/price")
    public void receivePriceUpdate(@PathVariable String identifier, @RequestBody SecurityPriceUpdateDto request) {
        securitiesService.receivePriceUpdate(identifier, request.getPrice());
    }
}
