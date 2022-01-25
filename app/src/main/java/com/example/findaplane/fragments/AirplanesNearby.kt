package com.example.findaplane.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.findaplane.*
import com.example.findaplane.model.Response
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.absoluteValue


class AirplanesNearby : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_airplanes_nearby, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: AirplanesNearbyViewModel by activityViewModels()
        //viewModel = ViewModelProvider(this).get(AirplanesNearbyViewModel::class.java)

        val listAdapter = ListAdapter(viewModel.all as MutableLiveData<Response>, viewModel)

        viewModel.all.observe(viewLifecycleOwner, { listAdapter.notifyDataSetChanged() })
        if(listAdapter.itemCount > 0) {
            val layoutManager = LinearLayoutManager(view.context)
            view.findViewById<RecyclerView>(R.id.recycler).let {

                it.adapter = listAdapter
                it.layoutManager = layoutManager
            }
            view.findViewById<TextView>(R.id.textViewBrak).text = ""
        }
        else{
            view.findViewById<TextView>(R.id.textViewBrak).text = "Brak Samolotów"
        }
        val kompas = Sensors.kompas_value.toDouble()
        val kierunek = Sensors.akcelerometr_value.toDouble()
        val latitude = GPS.latitude
        val longitude = GPS.longitude
        val lamin = 0.0
        val lamax = 0.0
        val lomin = 0.0
        val lomax = 0.0

        val wartosci = oblicz_wartosci(kompas, kierunek, lamin, lamax, lomin, lomax)

        // na jaka odleglosc w kazda strone bedziemy szukac
        viewModel.postAll(wartosci[0], wartosci[2], wartosci[1], wartosci[3])


        (view.findViewById<FloatingActionButton>(R.id.backFromAirplanesNearby)).setOnClickListener {
            it.findNavController().navigate(R.id.action_airplanesNearby_to_sensorsFragment)
        }

    }

        fun oblicz_wartosci(kom: Double, odle: Double, lamin: Double, lamax: Double, lomin: Double, lomax: Double): Array<Double> {
            val zwracana = arrayOf(lamin, lamax, lomin, lomax)
            var a = (270 - kom).absoluteValue

            val stala = 600000

            a = (180 - a).absoluteValue
            zwracana[0] = (a*a*a)/stala

            a = (180 - kom).absoluteValue
            a = (180 - a).absoluteValue
            zwracana[2] = (a*a*a)/stala

            a = (90 - kom).absoluteValue
            a = (180 - a).absoluteValue
            zwracana[1] = (a*a*a)/stala

            a = (360 - kom).absoluteValue
            a = (180 - a).absoluteValue
            zwracana[3] = ((a*a*a)/stala)
            return zwracana
        }

}