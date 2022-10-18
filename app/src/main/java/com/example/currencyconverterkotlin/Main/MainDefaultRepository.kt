package com.example.currencyconverterkotlin.Main

import android.util.Log
import com.example.currencyconverterkotlin.RequestResponse.CurrencyApi
import com.example.currencyconverterkotlin.RequestResponse.CurrencyResponse
import com.example.currencyconverterkotlin.Util.Resource
import javax.inject.Inject

class MainDefaultRepository @Inject  constructor(
    private val api : CurrencyApi
) :MainRepository {
    override suspend fun getRates(currency: String): Resource<CurrencyResponse> {
        return try {
            val response = api.getRates(currency)
            Log.d("API",response.toString())
            val result = response.body()
            if (response.isSuccessful && result != null){
                Resource.Success(result)
            } else {
                Log.d("API",response.toString())
                Resource.Error(response.message())
            }
        }catch (e : Exception){
            Log.d("API","${e.message}")
            Resource.Error(e.message?: "An error occurred")
        }
    }
}

