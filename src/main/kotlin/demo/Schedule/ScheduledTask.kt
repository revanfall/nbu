package demo.Schedule

import demo.Service.CurrencyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ScheduledTask {

    @Autowired
    private lateinit var currencyService: CurrencyService

    @Scheduled(cron = "0 0 10 * * *")
    fun updateCurrencies(){
        currencyService.updateCurrenciesRates()
    }
}