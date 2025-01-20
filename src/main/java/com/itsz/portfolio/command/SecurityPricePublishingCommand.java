package com.itsz.portfolio.command;

import com.itsz.portfolio.service.SecuritiesService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.math.BigDecimal;

@ShellComponent
public class SecurityPricePublishingCommand {

    private final SecuritiesService securitiesService;

    public SecurityPricePublishingCommand(SecuritiesService securitiesService) {
        this.securitiesService = securitiesService;
    }

    @ShellMethod(key = "publish", value = "publish stock price e.g. publish AAPL 120.00")
    public void publishPrice(
            @ShellOption(value = {"-i", "--identifier"}, defaultValue = "AAPL") String identifier, @ShellOption(value = {"-p", "--price"}, defaultValue = "120.00") BigDecimal price
    ) {
        securitiesService.receivePriceUpdate(identifier, price);
    }
}
