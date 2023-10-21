package com.example.wtest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wtest.model.CountryDetail
import com.example.wtest.service.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: MainRepository): ViewModel() {

    val countriesList = MutableLiveData<List<CountryDetail>>()
    val errorMessage= MutableLiveData<String>()

    fun getAllCountriesData(){
        CoroutineScope(Dispatchers.IO).launch {
            val response= repository.getAllCountries()
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    countriesList.postValue(response.body())
                }
               else{
                    errorMessage.postValue(response.message())
                }
            }
        }
    }
}