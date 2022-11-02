package com.example.nbu.rate.schedule

import com.example.nbu.rate.RateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
class UpdateRatesTask {


    @Autowired
    private lateinit var rateService: RateService

    @Scheduled(cron = "0 12  * * *", zone = "Europe/Kiev")
    fun updateRates(){
        rateService.updateRates()
    }
}