package com.example.nbu.rate

import com.example.nbu.currency.CurrencyService
import com.example.nbu.rate.exceptions.CurrencyNotFound
import com.example.nbu.rate.helper.RateResp
import com.github.kittinunf.fuel.httpGet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class RateService(@Autowired val rateRepository: RateRepository, @Autowired val currencyService: CurrencyService) {

    val BASE_NBU_CURRENCY_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode=";

    fun getRates():List<Rate>{
        return rateRepository.findAll()
    }

    fun getRate(alias: String):Rate {
        val optionalRate = rateRepository.findByAlias(alias.toUpperCase())[0]
        if (optionalRate.id == 0) {
            throw CurrencyNotFound("Currency $alias not found")
        }
        return optionalRate;
    }

    fun saveNewCurrency(alias: String){
        currencyService.setCurrency(alias)
        val rate = getRateFromNbu(alias.toUpperCase())
        rateRepository.save(rate[0])
    }

    fun getRateFromNbu(cc:String):List<Rate> {
        val URL = getRateFromNbuUrl(cc)
        var nbuRates = mutableListOf<Rate>()

        val (_,_,result) = URL.httpGet().responseObject(RateResp.Deserializer())

        val (nbuCurrencies,_) = result

        nbuCurrencies?.forEach { nbuRate -> nbuRates.add(Rate(nbuRate.cc,nbuRate.rate, LocalDate.now())) }
        return nbuRates
    }

    fun updateRates(){
        val rates = getRates()

        for (rate in rates){
            val newRate = getRateFromNbu(rate.alias)
            if (newRate.isNotEmpty()){
                rate.rate = newRate[0].rate
                rate.date = LocalDate.now()
            }
        }

        rateRepository.saveAll(rates)
    }

    private fun getRateFromNbuUrl(cc: String): String{
        return "$BASE_NBU_CURRENCY_URL$cc&json"
    }

}