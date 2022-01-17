package demo.Service

import java.util.*

class CurrencyBO {

    val alias: String
    var rate: Double
    val updated: Date?

    constructor(_alias: String, _rate: Double){
        alias = _alias
        rate = _rate
        this.updated = null
    }

    constructor(_alias: String, _rate: Double, _updated: Date?){
        alias = _alias
        rate = _rate
        updated = _updated
    }
}