package com.example.findaplane.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL="https://opensky-network.org/api/states/"
object RetrofitInstance {

    private val retrofit by lazy{

        Retrofit.Builder()
            .baseUrl(BASE_URL)//("https://opensky-network.org/api/states")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val api: InterfaceAPI by lazy {
        Retrofit.Builder()
            .baseUrl("https://opensky-network.org/api/states/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(InterfaceAPI::class.java)
    }
}


