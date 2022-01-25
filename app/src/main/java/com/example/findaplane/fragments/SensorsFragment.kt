package com.example.findaplane.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.findaplane.GPS
import com.example.findaplane.R
import com.example.findaplane.Sensors

class SensorsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sensors, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.finderButton).apply {
            setOnClickListener {
                findNavController().navigate(R.id.action_sensorsFragment_to_airplanesNearby)
            }
        }
        view.findViewById<Button>(R.id.go_lokalizacja).apply {
            setOnClickListener {
                val intent2 = Intent(context, GPS::class.java)
                startActivity(intent2)
            }
        }
        view.findViewById<TextView>(R.id.tLat).text ="Latitude: " + GPS.latitude.toString()
        view.findViewById<TextView>(R.id.tLon).text ="Longitude: " + GPS.longitude.toString()

        view.findViewById<Button>(R.id.go_map).apply {
            setOnClickListener {
                findNavController().navigate(R.id.action_sensorsFragment_to_mapsFragment)
            }
        }
    }
}

