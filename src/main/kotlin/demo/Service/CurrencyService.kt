package demo.Service

import demo.CurrencyRepository.CurrencyRepository
import com.github.kittinunf.fuel.httpGet
import demo.Service.Helper.CurrencyNBU
import demo.Service.Helper.CurrencyObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CurrencyService: ICurrencyService {

    val NBU_CURRENCYRATE_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode=";

    @Autowired
    private lateinit var currencyRepository: CurrencyRepository

    override fun getRates(): List<CurrencyBO> {

       return CurrencyObjectMapper.mapToBO(currencyRepository.findAll())
    }

    override fun getRate(alias: String): CurrencyBO {
        val rate=currencyRepository.findById(alias.uppercase(Locale.getDefault()))
        if(rate.isEmpty){
            throw Exception("$alias can't be found")
        }
        else return CurrencyObjectMapper.mapToBO(rate.get())
    }

    override fun updateCurrenciesRates() {
        val updateCurrencies: List<CurrencyBO> = getRates()

        for(rate in updateCurrencies){
            val newCurrency= getCurrencyFromNBU(rate.alias)
            if(newCurrency.isNotEmpty())
                rate.rate = newCurrency[0].rate
        }
        currencyRepository.saveAll(CurrencyObjectMapper.mapToEntity(updateCurrencies))
    }

    fun getCurrencyUrl(alias: String): String{
        return "$NBU_CURRENCYRATE_URL$alias&json"
    }

    private fun getCurrencyFromNBU(alias: String): ArrayList<CurrencyNBU> {
        val URL = getCurrencyUrl(alias)
        val nbuRatesToRet = ArrayList<CurrencyNBU>()

        val (request, response, result) = URL.httpGet().responseObject(CurrencyNBU.Deserializer())

        val (nbuCurrencies, err) = result

        nbuCurrencies?.forEach { nbuCurrency ->

            nbuRatesToRet.add(nbuCurrency)
        }

        return nbuRatesToRet
    }
}