package com.example.currencyconverterkotlin.Main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverterkotlin.RequestResponse.ConversionRates
import com.example.currencyconverterkotlin.Util.DispatcherProvider
import com.example.currencyconverterkotlin.Util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Math.round

class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository ,
    private val dispatcher :DispatcherProvider
) : ViewModel(){
    var date =""
    sealed class CurrencyEvent{
        class Success (val resultText : String ) : CurrencyEvent()
        class Failure (val errorText : String ) : CurrencyEvent()
        object Loading : CurrencyEvent()
        object Empty : CurrencyEvent()
    }
    private val _conversion = MutableStateFlow<CurrencyEvent>(CurrencyEvent.Empty)
    val conversion : StateFlow<CurrencyEvent> = _conversion

    fun convert (
       amountStr: String ,
       fromCurrency : String ,
       toCurrency : String
    ){
        val fromAmount = amountStr.toFloatOrNull()
        if (fromAmount==null){
            _conversion.value = CurrencyEvent.Failure("Not a valid amount ")
              return
        }
         viewModelScope.launch(dispatcher.io){
             _conversion.value= CurrencyEvent.Loading
             when(val ratesResponse = repository.getRates(fromCurrency)){
                 is Resource.Error -> _conversion.value = CurrencyEvent.Failure(ratesResponse.message!!)
                 is Resource.Success -> {
                     val rates = ratesResponse.data!!.conversionRates
                     date = ratesResponse.data.time_last_update_utc.toString()
                     val rate = getRateForCurrency(toCurrency,rates)
                     if (rate == null){
                         _conversion.value = CurrencyEvent.Failure("unexpected error")
                     }else {
                         val convertedCurrency = round (fromAmount * rate *100) /100
                         _conversion.value = CurrencyEvent.Success(
                             "$fromAmount $fromCurrency = $convertedCurrency $toCurrency"
                         )
                     }
                 }
             }
         }
    }
    private fun getRateForCurrency(toCurrency: String, conversionRates: ConversionRates) = when (toCurrency) {

        "AED" -> conversionRates.aED
        "AFN" -> conversionRates.aFN
        "ALL" -> conversionRates.aLL
        "AMD" -> conversionRates.aMD
        "ANG" -> conversionRates.aNG
        "AOA" -> conversionRates.aOA
        "ARS" -> conversionRates.aRS
        "AUD" -> conversionRates.aUD
        "AWG" -> conversionRates.aWG
        "AZN" -> conversionRates.aZN
        "BAM" -> conversionRates.bAM
        "BBD" -> conversionRates.bBD
        "BDT" -> conversionRates.bDT
        "BGN" -> conversionRates.bGN
        "BHD" -> conversionRates.bHD
        "BIF" -> conversionRates.bIF
        "BMD" -> conversionRates.bMD
        "BND" -> conversionRates.bND
        "BOB" -> conversionRates.bOB
        "BRL" -> conversionRates.bRL
        "BSD" -> conversionRates.bSD
        "BTN" -> conversionRates.bTN
        "BWP" -> conversionRates.bWP
        "BYN" -> conversionRates.bYN
        "BZD" -> conversionRates.bZD
        "CAD" -> conversionRates.cAD
        "CDF" -> conversionRates.cDF
        "CHF" -> conversionRates.cHF
        "CLP" -> conversionRates.cLP
        "CNH" -> conversionRates.cNH
        "CNY" -> conversionRates.cNY
        "COP" -> conversionRates.cOP
        "CRC" -> conversionRates.cRC
        "CUP" -> conversionRates.cUP
        "CVE" -> conversionRates.cVE
        "CZK" -> conversionRates.cZK
        "DJF" -> conversionRates.dJF
        "DKK" -> conversionRates.dKK
        "DOP" -> conversionRates.dOP
        "DZD" -> conversionRates.dZD
        "EGP" -> conversionRates.eGP
        "ERN" -> conversionRates.eRN
        "ETB" -> conversionRates.eTB
        "EUR" -> conversionRates.eUR
        "FJD" -> conversionRates.fJD
        "FKP" -> conversionRates.fKP
        "GBP" -> conversionRates.gBP
        "GEL" -> conversionRates.gEL
        "GGP" -> conversionRates.gGP
        "GHS" -> conversionRates.gHS
        "GIP" -> conversionRates.gIP
        "GMD" -> conversionRates.gMD
        "GNF" -> conversionRates.gNF
        "GTQ" -> conversionRates.gTQ
        "GYD" -> conversionRates.gYD
        "HKD" -> conversionRates.hKD
        "HNL" -> conversionRates.hNL
        "HRK" -> conversionRates.hRK
        "HTG" -> conversionRates.hTG
        "HUF" -> conversionRates.hUF
        "IDR" -> conversionRates.iDR
        "ILS" -> conversionRates.iLS
        "IMP" -> conversionRates.iMP
        "INR" -> conversionRates.iNR
        "IQD" -> conversionRates.iQD
        "IRR" -> conversionRates.iRR
        "ISK" -> conversionRates.iSK
        "JEP" -> conversionRates.jEP
        "JMD" -> conversionRates.jMD
        "JOD" -> conversionRates.jOD
        "JPY" -> conversionRates.jPY
        "KES" -> conversionRates.kES
        "KGS" -> conversionRates.kGS
        "KHR" -> conversionRates.kHR
        "KMF" -> conversionRates.kMF
        "KPW" -> conversionRates.kPW
        "KRW" -> conversionRates.kRW
        "KWD" -> conversionRates.kWD
        "KYD" -> conversionRates.kYD
        "KZT" -> conversionRates.kZT
        "LAK" -> conversionRates.lAK
        "LBP" -> conversionRates.lBP
        "LKR" -> conversionRates.lKR
        "LRD" -> conversionRates.lRD
        "LSL" -> conversionRates.lSL
        "LYD" -> conversionRates.lYD
        "MAD" -> conversionRates.mAD
        "MDL" -> conversionRates.mDL
        "MGA" -> conversionRates.mGA
        "MKD" -> conversionRates.mKD
        "MMK" -> conversionRates.mMK
        "MNT" -> conversionRates.mNT
        "MOP" -> conversionRates.mOP
        "MRU" -> conversionRates.mRU
        "MUR" -> conversionRates.mUR
        "MVR" -> conversionRates.mVR
        "MWK" -> conversionRates.mWK
        "MXN" -> conversionRates.mXN
        "MYR" -> conversionRates.mYR
        "MZN" -> conversionRates.mZN
        "NAD" -> conversionRates.nAD
        "NGN" -> conversionRates.nGN
        "NIO" -> conversionRates.nIO
        "NOK" -> conversionRates.nOK
        "NPR" -> conversionRates.nPR
        "NZD" -> conversionRates.nZD
        "OMR" -> conversionRates.oMR
        "PAB" -> conversionRates.pAB
        "PEN" -> conversionRates.pEN
        "PGK" -> conversionRates.pGK
        "PHP" -> conversionRates.pHP
        "PKR" -> conversionRates.pKR
        "PLN" -> conversionRates.pLN
        "PYG" -> conversionRates.pYG
        "QAR" -> conversionRates.qAR
        "RON" -> conversionRates.rON
        "RSD" -> conversionRates.rSD
        "RUB" -> conversionRates.rUB
        "RWF" -> conversionRates.rWF
        "SAR" -> conversionRates.rAR
        "SBD" -> conversionRates.rBD
        "SCR" -> conversionRates.sCR
        "SDG" -> conversionRates.sDG
        "SEK" -> conversionRates.sEK
        "SGD" -> conversionRates.sGD
        "SHP" -> conversionRates.sHP
        "SLL" -> conversionRates.sLL
        "SOS" -> conversionRates.sOS
        "SRD" -> conversionRates.sRD
        "SSP" -> conversionRates.sSP
        "STD" -> conversionRates.sTD
        "STN" -> conversionRates.sTN
        "SVC" -> conversionRates.sVC
        "SYP" -> conversionRates.sYP
        "SZL" -> conversionRates.sZL
        "THB" -> conversionRates.tHB
        "TJS" -> conversionRates.tJS
        "TMT" -> conversionRates.tMT
        "TND" -> conversionRates.tND
        "TOP" -> conversionRates.tOP
        "TRY" -> conversionRates.tRY
        "TTD" -> conversionRates.tTD
        "TWD" -> conversionRates.tWD
        "TZS" -> conversionRates.tZS
        "UAH" -> conversionRates.uAH
        "UGX" -> conversionRates.uGX
        "USD" -> conversionRates.uSD
        "UYU" -> conversionRates.uYU
        "UZS" -> conversionRates.uZS
        "VES" -> conversionRates.vES
        "VND" -> conversionRates.vND
        "VUV" -> conversionRates.vUV
        "WST" -> conversionRates.wST
        "XAF" -> conversionRates.xAF
        "XCD" -> conversionRates.xCD
        "XDR" -> conversionRates.xDR
        "XOF" -> conversionRates.xOF
        "XPD" -> conversionRates.xPD
        "XPF" -> conversionRates.xPF
        "YER" -> conversionRates.yER
        "ZAR" -> conversionRates.zAR
        "ZMW" -> conversionRates.zMW
        "ZWL" -> conversionRates.zWL
        else ->null
    }

}