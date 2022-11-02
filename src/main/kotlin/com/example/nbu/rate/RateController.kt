package com.example.nbu.rate

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RateController(@Autowired val rateService: RateService) {

    @GetMapping("/")
    fun getRates():List<Rate> {
        return rateService.getRates()
    }


}