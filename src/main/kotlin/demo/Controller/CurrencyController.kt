package demo.Controller

import demo.Service.CurrencyService
import demo.Service.CurrencyBO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@RestController
class CurrencyController {

    @Autowired
    private lateinit var currencyService: CurrencyService

    @GetMapping("/all")
    fun getCurrencyRates(): List<CurrencyBO>
    {
        currencyService.updateCurrenciesRates()
        return currencyService.getRates()
    }

    @GetMapping("/{alias}")
    fun getRateByAlias(@PathVariable alias: String): ResponseEntity<Any>{
        try{
            return ResponseEntity.ok().body(currencyService.getRate(alias))
        }
        catch (e: Exception){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.message)
        }
    }
}
