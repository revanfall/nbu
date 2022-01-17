package demo.Service.Helper

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson


data class CurrencyNBU (
    val cc: String,
    val rate: Double
){

    class Deserializer: ResponseDeserializable<Array<CurrencyNBU>> {
        override fun deserialize(content: String): Array<CurrencyNBU>? = Gson().fromJson(content, Array<CurrencyNBU>::class.java)
    }

}

