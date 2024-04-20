package com.example.turnoverprediction.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EmployeeApi {

    @POST("/predict")
    fun postData(@Body postRequest: PostRequest):Call<ResultResponse>

}