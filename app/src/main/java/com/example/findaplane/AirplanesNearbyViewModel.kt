package com.example.findaplane

import android.annotation.SuppressLint
import androidx.lifecycle.*
import com.example.findaplane.model.Repository
import com.example.findaplane.model.Response
import com.example.findaplane.GPS
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch

class AirplanesNearbyViewModel:ViewModel(){

    private val _all: MutableLiveData<Response> = MutableLiveData()
    val all: LiveData<Response>
        get(){
            return _all
        }

    var posit: Int = 0

    fun postAll(lat: Double,lon: Double, lamin:Double, lomin:Double, lamax:Double, lomax:Double)
    {
        viewModelScope.launch {

            System.out.println(lat)
            System.out.println(lon)
            val planes= Repository.getAll(lat - lamin,
               lon - lomin,
               lat + lamax,
               lon + lomax)

            if(planes!=null)
                _all.value= planes
        }

    }


}