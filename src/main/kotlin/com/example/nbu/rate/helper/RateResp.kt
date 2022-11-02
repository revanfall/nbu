package com.example.nbu.rate.helper

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class RateResp(
    val cc:String,
    val rate: Double) {

    class Deserializer:ResponseDeserializable<Array<RateResp>> {
        override fun deserialize(content: String): Array<RateResp>? {
            return Gson().fromJson(content, Array<RateResp>::class.java)
        }
    }
}