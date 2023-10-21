package com.example.wtest.model

data class CountryDetail(val capital:String, val code:String,
                         val currency: CurrencyDetail,
                         val flag:String,
                         val language: LanguageDetail,
                         val name:String,
                         val region:String)

/*"capital": "Kabul",
"code": "AF",
"currency": {
    "code": "AFN",
    "name": "Afghan afghani",
    "symbol": "؋"
},
"flag": "https://restcountries.eu/data/afg.svg",
"language": {
    "code": "ps",
    "name": "Pashto"
},
"name": "Afghanistan",
"region": "AS"

 */