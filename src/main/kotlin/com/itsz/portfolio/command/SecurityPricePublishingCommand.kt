package com.itsz.portfolio.command

import com.itsz.portfolio.service.SecuritiesService
import org.springframework.stereotype.Component
import picocli.CommandLine.Command
import picocli.CommandLine.Option
import java.math.BigDecimal

@Component
@Command(name = "publish", description = ["Publish stock price, e.g. publish -i AAPL -p 120.00"])
class SecurityPricePublishingCommand(
    private val securitiesService: SecuritiesService
) : Runnable {

    @Option(names = ["-i", "--identifier"], defaultValue = "AAPL", description = ["Stock identifier"])
    var identifier: String = "AAPL"

    @Option(names = ["-p", "--price"], defaultValue = "120.00", description = ["Stock price"])
    var price: BigDecimal = BigDecimal("120.00")

    override fun run() {
        securitiesService.receivePriceUpdate(identifier, price)
    }
}
