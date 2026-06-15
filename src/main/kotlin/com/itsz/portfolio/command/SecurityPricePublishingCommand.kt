package com.itsz.portfolio.command

import com.itsz.portfolio.service.SecuritiesService
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellOption
import java.math.BigDecimal

@ShellComponent
class SecurityPricePublishingCommand(
    private val securitiesService: SecuritiesService
) {
    @ShellMethod(key = ["publish"], value = "publish stock price e.g. publish AAPL 120.00")
    fun publishPrice(
        @ShellOption(value = ["-i", "--identifier"], defaultValue = "AAPL") identifier: String,
        @ShellOption(value = ["-p", "--price"], defaultValue = "120.00") price: BigDecimal
    ) {
        securitiesService.receivePriceUpdate(identifier, price)
    }
}
