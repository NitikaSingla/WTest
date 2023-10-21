package com.example.wtest.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wtest.databinding.AdapterCountriesBinding
import com.example.wtest.model.CountryDetail

class CountryRecyclerAdapter:RecyclerView.Adapter<CountryViewHolder>(){
    var countries= mutableListOf<CountryDetail>()

    fun setCountriesList(countries:List<CountryDetail>){
        this.countries=countries.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding=AdapterCountriesBinding.inflate(inflater,parent,false)
        return CountryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val countryDetail=countries[position]
        holder.binding.tvNameRegion.text=countryDetail.name+", "+countryDetail.region
        holder.binding.tvcapital.text=countryDetail.capital
        holder.binding.tvCode.text=countryDetail.code
    }
}

class CountryViewHolder(val binding:AdapterCountriesBinding):RecyclerView.ViewHolder(binding.root){

}