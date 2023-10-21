package com.example.wtest.service

class MainRepository(private val retrofitService: RetrofitService) {

     suspend fun getAllCountries()=retrofitService.getAllCountriesData()
}