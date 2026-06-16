package com.itsz.portfolio

import com.itsz.portfolio.command.SecurityPricePublishingCommand
import com.itsz.portfolio.config.PicocliRuntimeHints
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ImportRuntimeHints
import picocli.CommandLine
import picocli.CommandLine.Command
import java.math.BigDecimal

@SpringBootApplication
@ImportRuntimeHints(PicocliRuntimeHints::class)
@Command(name = "portfolio")
open class PortfolioApplication(
    private val publishCommand: SecurityPricePublishingCommand
) : Runnable, CommandLineRunner {

    override fun run() {} // picocli root: no-op when no subcommand given

    override fun run(vararg args: String) {
        if (args.firstOrNull() == "publish") {
            executePublish(args.drop(1))
            return
        }

        CommandLine(this)
            .addSubcommand("publish", publishCommand)
            .execute(*args)
    }

    private fun executePublish(args: List<String>) {
        var identifier = publishCommand.identifier
        var price = publishCommand.price

        var idx = 0
        while (idx < args.size) {
            when (val token = args[idx]) {
                "-i", "--identifier" -> {
                    val value = args.getOrNull(idx + 1)
                        ?: throw CommandLine.ParameterException(CommandLine(this), "Missing value for option '$token'")
                    identifier = value
                    idx += 2
                }

                "-p", "--price" -> {
                    val value = args.getOrNull(idx + 1)
                        ?: throw CommandLine.ParameterException(CommandLine(this), "Missing value for option '$token'")
                    price = value.toBigDecimalOrNull()
                        ?: throw CommandLine.ParameterException(CommandLine(this), "Invalid decimal value for option '$token': '$value'")
                    idx += 2
                }

                else -> {
                    throw CommandLine.ParameterException(CommandLine(this), "Unknown option: '$token'")
                }
            }
        }

        publishCommand.identifier = identifier
        publishCommand.price = price
        publishCommand.run()
    }
}

fun main(args: Array<String>) {
    runApplication<PortfolioApplication>(*args)
}
