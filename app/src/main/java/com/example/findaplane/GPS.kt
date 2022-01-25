package com.example.findaplane

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.Settings
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import java.lang.Exception
import kotlin.properties.Delegates


class GPS: AppCompatActivity() {

    lateinit var fusedLocationClient : FusedLocationProviderClient
    companion object {
        var latitude: Double = 0.0
        var longitude: Double = 0.0
    }
    private val LOCATION_PERMISSION_REQ_CODE = 1000;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_gps)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val tvLatitude = findViewById<TextView>(R.id.lat)
        val tvLongitude = findViewById<TextView>(R.id.lon)
            // checking location permission
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // request permission
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQ_CODE
                )
            }
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    // getting the last known or current location
                    try {
                        latitude = location.latitude
                        longitude = location.longitude
                        System.out.println(longitude)
                        tvLatitude.text ="Latitude: " + location.latitude.toString()
                        tvLongitude.text ="Longitude: " + location.longitude.toString()
                    }
                    catch(e: Exception){
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(
                        this, "Failed on getting current location",
                        Toast.LENGTH_SHORT
                    ).show()
                }
       // }
    }
}