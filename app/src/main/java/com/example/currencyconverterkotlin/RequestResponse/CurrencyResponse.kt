package com.example.currencyconverterkotlin.RequestResponse

import com.google.gson.annotations.SerializedName

class CurrencyResponse (
    @SerializedName("base_code")
    val baseCode: String ?=null,
    @SerializedName("conversion_rates")
    val conversionRates: ConversionRates,
    @SerializedName("documentation")
    val documentation: String?=null,
    @SerializedName("result")
    val result: String?=null,
    @SerializedName("terms_of_use")
    val terms_of_use: String?=null,
    @SerializedName("time_last_update_unix")
    val time_last_update_unix: Int?=null,
    @SerializedName("time_last_update_utc")
    val time_last_update_utc: String?=null,
    @SerializedName("time_next_update_unix")
    val time_next_update_unix: Int?=null,
    @SerializedName("time_next_update_utc")
    val time_next_update_utc: String?=null
)