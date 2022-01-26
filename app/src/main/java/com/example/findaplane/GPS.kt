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


class GPS: AppCompatActivity(){

    private val LOCATION_PERMISSION_REQ_CODE = 1000;
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    companion object {
        var latitude: Double = 0.0
        var longitude: Double = 0.0
    }
    lateinit var tvLatitude: TextView
    lateinit var tvLongitude: TextView
    //lateinit var lm : LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_gps)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        tvLatitude = findViewById<TextView>(R.id.lat)
        tvLongitude = findViewById<TextView>(R.id.lon)
        //lm = getSystemService(LOCATION_SERVICE) as LocationManager
        getCurrentLocation()
        //tvLatitude.text = latitude.toString()
        //tvLongitude.text = longitude.toString()
    }
    private fun getCurrentLocation() {
        // checking location permission
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // request permission
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQ_CODE)
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener {location ->
                //if(location != null) {
            if (location != null) {
                latitude = location.latitude
                longitude = location.longitude
                System.out.println("Weszło")
                tvLatitude.text = "Latitude: " + latitude.toString()
                tvLongitude.text = "Longitude: " + longitude.toString()
            }

            else{
                    System.out.println("Nie Weszło")
            }

            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed on getting current location",
                    Toast.LENGTH_SHORT).show()
            }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_PERMISSION_REQ_CODE -> {
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted
                    Toast.makeText(this, "You have access",
                        Toast.LENGTH_SHORT).show()
                } else {
                    // permission denied
                    Toast.makeText(this, "You need to grant permission to access location",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

