package com.example.wtest.service

import com.example.wtest.model.CountryDetail
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("countries.json")
     suspend fun getAllCountriesData(): Response<List<CountryDetail>>

    companion object {
        var retrofitService: RetrofitService?=null
        const val baseUrl="https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/"
        fun getRetrofitInstance(): RetrofitService {
            if(retrofitService ==null){
                val retrofit= Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                retrofitService =retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }


    }
}