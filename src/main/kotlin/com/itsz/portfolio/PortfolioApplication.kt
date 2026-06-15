package com.itsz.portfolio

import com.itsz.portfolio.command.SecurityPricePublishingCommand
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import picocli.CommandLine
import picocli.CommandLine.Command

@SpringBootApplication
@Command(name = "portfolio")
open class PortfolioApplication(
    private val publishCommand: SecurityPricePublishingCommand
) : Runnable, CommandLineRunner {

    override fun run() {} // picocli root: no-op when no subcommand given

    override fun run(vararg args: String) {
        CommandLine(this)
            .addSubcommand("publish", publishCommand)
            .execute(*args)
    }
}

fun main(args: Array<String>) {
    runApplication<PortfolioApplication>(*args)
}
