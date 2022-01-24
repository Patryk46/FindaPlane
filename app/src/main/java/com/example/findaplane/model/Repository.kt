package com.example.findaplane.model

import retrofit2.awaitResponse

class Repository {

   companion object {

       suspend fun getAll(lamin:Double, lomin:Double, lamax:Double, lomax:Double): Response? {
           return RetrofitInstance.api.getAll(lamin, lomin, lamax, lomax).awaitResponse().body()
       }

   }
}