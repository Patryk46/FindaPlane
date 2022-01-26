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
        //val latitude = GPS.latitude
        //val longitude = GPS.longitude
        val lamin = 0.6
        val lamax = 0.6
        val lomin = 0.6
        val lomax = 0.6

        //val wartosci = przesuniecie(kompas, kierunek, latitude, longitude)
        //var wartosci = arrayOf(3.2, 5.7)
        // na jaka odleglosc w kazda strone bedziemy szukac
        //viewModel.postAll(wartosci[0], wartosci[1], lamin, lomin, lamax, lomax)


        (view.findViewById<FloatingActionButton>(R.id.backFromAirplanesNearby)).setOnClickListener {
            it.findNavController().navigate(R.id.action_airplanesNearby_to_sensorsFragment)
        }

    }

    fun przesuniecie(kom: Double, kat: Double, latit: Double, longit: Double): Array<Double>{
        var zwracana = arrayOf(latit, longit)
        var prawo = true
        var gora = true
        var b1 = 0.0
        var b2 = 0.0
        var a = 0.0
        if(kom > 0 && kom < 180){
            val also = Math.abs(90 - kom).also { a = it }
        }
        else{
            val also = Math.abs(270 - kom).also { a = it }
            prawo = false
        }
        b1 = 90 - a

        if(kom > 270){
            val also = Math.abs(360 - kom).also { a = it }
        }
        else if(kom < 90){
            val also = Math.abs(90 - kom).also { a = it }
        }
        else {
            val also = Math.abs(180 - kom).also { a = it }
            gora = false
        }
        b2 = 90 - a

        var par = 100
        var zmiana = (90 - kat)/100

        var dlugosc = 0.0
        var szerokosc = 0.0

        if(prawo == true){
            dlugosc += b1/90 * zmiana
        }
        else{
            dlugosc -= b1/90 * zmiana
        }

        if(gora == true){
            szerokosc += b2/90 * zmiana
        }
        else{
            szerokosc -= b2/90 * zmiana
        }

        zwracana[0] += szerokosc
        zwracana[1] += dlugosc
        return zwracana
    }

}