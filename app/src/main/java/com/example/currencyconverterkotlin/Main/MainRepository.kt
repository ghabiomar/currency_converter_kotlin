package com.example.currencyconverterkotlin.Main

import com.example.currencyconverterkotlin.RequestResponse.CurrencyResponse
import com.example.currencyconverterkotlin.Util.Resource

interface MainRepository {
    suspend fun getRates(currency : String)  : Resource<CurrencyResponse>
}