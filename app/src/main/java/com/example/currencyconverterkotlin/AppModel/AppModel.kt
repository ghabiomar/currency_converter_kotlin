package com.example.currencyconverterkotlin.AppModel

import com.example.currencyconverterkotlin.Main.MainDefaultRepository
import com.example.currencyconverterkotlin.Main.MainRepository
import com.example.currencyconverterkotlin.RequestResponse.CurrencyApi
import com.example.currencyconverterkotlin.Util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://v6.exchangerate-api.com/v6/746417b1395a760f29a03212/"
@Module
@InstallIn(ApplicationComponent::class)
object AppModel {

    @Singleton
    @Provides
    fun provideCurrencyApi () : CurrencyApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CurrencyApi::class.java)

    @Singleton
    @Provides
    fun provideDefaultRepository(api : CurrencyApi) :MainRepository = MainDefaultRepository(api)

    @Singleton
    @Provides
    fun provideDispatcher (): DispatcherProvider = object :DispatcherProvider{
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined

    }
}