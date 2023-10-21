package com.example.wtest.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.wtest.R
import com.example.wtest.databinding.ActivityMainBinding
import com.example.wtest.service.MainRepository
import com.example.wtest.service.RetrofitService
import com.example.wtest.viewmodel.MainViewModel
import com.example.wtest.viewmodel.MyViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val retrofitService= RetrofitService.getRetrofitInstance()
    private val adapter= CountryRecyclerAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel=ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
            MainViewModel::class.java)

        binding.recyclerview.adapter=adapter

        viewModel.countriesList.observe(this, Observer {
            binding.progressBar.visibility= View.GONE
            binding.loadingTv.visibility=View.GONE
            it.let {
                adapter.setCountriesList(it)
            }
        })

        viewModel.errorMessage.observe(this, Observer {
            binding.progressBar.visibility= View.GONE
            binding.loadingTv.visibility=View.VISIBLE
            binding.loadingTv.text= "Unable to load data!"
        })

        if(isNetworkAvailable()) {
            binding.progressBar.visibility= View.VISIBLE
            binding.loadingTv.visibility=View.VISIBLE
            viewModel.getAllCountriesData()
        }
        else{
            binding.progressBar.visibility= View.GONE
            binding.loadingTv.visibility=View.VISIBLE
            binding.loadingTv.text= "No Internet Connection!"
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetworkInfo: NetworkInfo? = null
        activeNetworkInfo = cm.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }

}