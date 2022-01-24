package com.example.findaplane.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.findaplane.R
import com.example.findaplane.Sensors


class MenuFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //poprzez wcisniecie buttona wchodzimy z menu do AirplanesNearby


        (view.findViewById<Button>(R.id.go_to_sensor)).setOnClickListener{

            val intent = Intent(context, Sensors::class.java)
            startActivity(intent)

        }
        view.findViewById<Button>(R.id.buttonHistory).apply {
            setOnClickListener {
                findNavController().navigate(R.id.action_menuFragment_to_historyPlanes)
            }
        }

        var kom = "Kompas: " + Sensors.kompas_value.toInt()
        var akc = "Kierunek: " + Sensors.akcelerometr_value.toInt()
        (view.findViewById<TextView>(R.id.kompas)).text = kom
        (view.findViewById<TextView>(R.id.kierunek)).text = akc
    }


}