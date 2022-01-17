package demo.Service

interface ICurrencyService {

    fun getRates() : List<CurrencyBO>

    fun getRate(alias : String) : CurrencyBO

    fun updateCurrenciesRates()

}