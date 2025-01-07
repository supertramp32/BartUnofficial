package com.example.bart.retrofit

import com.example.bart.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {


    @GET("etd.aspx?cmd=etd&orig=powl&json=y")
    suspend fun getRealTimeBartSchedule() : Response<ApiResponse>

}