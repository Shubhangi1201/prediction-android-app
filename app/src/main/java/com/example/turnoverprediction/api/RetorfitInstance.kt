package com.example.turnoverprediction.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetorfitInstance {
    val api: EmployeeApi by lazy{
        Retrofit.Builder()
            .baseUrl("https://employee-turnover-prediction.onrender.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EmployeeApi::class.java)
    }
}