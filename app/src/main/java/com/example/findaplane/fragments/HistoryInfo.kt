package com.example.findaplane.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.findaplane.PlanesViewModel
import com.example.findaplane.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HistoryInfo : Fragment() {

    private lateinit var viewModell: PlanesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val positionn = arguments?.getInt("position")

        viewModell = ViewModelProvider(this).get(PlanesViewModel::class.java)
        if (positionn != null) {
            viewModell.getOne(positionn)
        }


        view.findViewById<TextView>(R.id.textView0H).text = "icao24: " + viewModell.plane.icao24
        view.findViewById<TextView>(R.id.textView1H).text = "callsign: " + viewModell.plane.callsign
        view.findViewById<TextView>(R.id.textView2H).text = "origin country: " + viewModell.plane.origin
        view.findViewById<TextView>(R.id.textView3H).text = "time position: " + viewModell.plane.time_position
        view.findViewById<TextView>(R.id.textView4H).text = "last contact: " + viewModell.plane.last_contact
        view.findViewById<TextView>(R.id.textView5H).text = "longitude: " + viewModell.plane.longitude
        view.findViewById<TextView>(R.id.textView6H).text = "latitude: " + viewModell.plane.latitude
        view.findViewById<TextView>(R.id.textView7H).text = "baro altitude: " + viewModell.plane.baro_altitude
        view.findViewById<TextView>(R.id.textView8H).text = "on ground: " + viewModell.plane.on_ground
        view.findViewById<TextView>(R.id.textView9H).text = "velocity: " + viewModell.plane.velocity


        view.findViewById<FloatingActionButton>(R.id.backFromHistoryInfo).apply {
            setOnClickListener {
                findNavController().navigate(R.id.action_historyInfo_to_historyPlanes)
            }
        }



    }
}