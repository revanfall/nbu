package com.example.nbu.rate

import com.example.nbu.currency.Currency
import com.example.nbu.rate.exceptions.CurrencyNotFound
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RateController(@Autowired val rateService: RateService) {

    @GetMapping("/")
    fun getRates():List<Rate> {
        return rateService.getRates()
    }

    @GetMapping("/{alias}")
    fun getRate(@PathVariable alias: String):ResponseEntity<Any> {
        try {
            return ResponseEntity.ok().body(rateService.getRate(alias))
        } catch (e:CurrencyNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
        }
    }

    @PostMapping("/")
    fun setNewCurrency(@RequestBody request: Currency):ResponseEntity<Any> {
        try {
            if(request.currencyCode.isBlank() || request.currencyCode.length > 3){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Currency code is blank or contains more than 3 symbols")
            }
            rateService.saveNewCurrency(request.currencyCode)
            return ResponseEntity.ok().build()
        }
        catch (e:Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }
}