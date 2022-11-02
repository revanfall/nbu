package com.example.nbu.currency

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CurrencyService(@Autowired val currencyRepository: CurrencyRepository) {

    fun getCurrency():List<Currency>{
        return currencyRepository.findAll()
    }

}