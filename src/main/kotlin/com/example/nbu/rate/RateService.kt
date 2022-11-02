package com.example.nbu.rate

import com.example.nbu.rate.helper.RateResp
import com.github.kittinunf.fuel.httpGet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class RateService(@Autowired val rateRepository: RateRepository) {

    val BASE_NBU_CURRENCY_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode=";

    fun getRates():List<Rate>{
        return rateRepository.findAll()
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