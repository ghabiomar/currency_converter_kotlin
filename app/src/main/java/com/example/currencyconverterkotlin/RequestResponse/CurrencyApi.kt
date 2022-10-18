package com.example.currencyconverterkotlin.RequestResponse

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyApi {
    @GET("latest/{Currency}")
    // API_KEY : 746417b1395a760f29a03212

    suspend fun getRates (
        @Path("Currency") currency : String
    ) : Response<CurrencyResponse>
}