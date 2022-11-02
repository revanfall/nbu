package com.example.nbu.rate

import com.example.nbu.currency.CurrencyService
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RateConfig {
    @Bean
    fun  commandLineRunner(rateRepository: RateRepository,
                           currencyService: CurrencyService,
                           rateService: RateService
    ) = CommandLineRunner{
        val rateList = mutableListOf<Rate>()

        for (i in currencyService.getCurrency()) {
            val rate = rateService.getRateFromNbu(i.currencyCode)
            println(rate[0])
            rateList.add(rate[0])
        }

        rateRepository.saveAll(rateList)
    }
}