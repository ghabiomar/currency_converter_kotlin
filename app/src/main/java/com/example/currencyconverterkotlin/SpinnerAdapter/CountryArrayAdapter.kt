package com.example.currencyconverterkotlin.SpinnerAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.currencyconverterkotlin.R
import kotlinx.android.synthetic.main.spinner_layout.view.*

class CountryArrayAdapter (context : Context , countryList:List<Country>) : ArrayAdapter<Country>(context ,0,countryList){
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }
    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View {
        val country = getItem(position)
        val view = convertView?:LayoutInflater.from(context).inflate(R.layout.spinner_layout,parent,false)
        view.imageViewLay.setImageResource(country!!.image)
        view.textViewLay.text = country.name
        return view
    }


}