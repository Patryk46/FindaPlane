package com.example.findaplane.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface InterfaceAPI {

    @GET("all")
    fun getAll(@Query("lamin") lamin:Double, @Query("lomin") lomin:Double, @Query("lamax") lamax:Double, @Query("lomax") lomax:Double): Call<Response>

}