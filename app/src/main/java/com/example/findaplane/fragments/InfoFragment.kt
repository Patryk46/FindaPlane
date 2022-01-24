package com.example.findaplane.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.findaplane.AirplanesNearbyViewModel
import com.example.findaplane.PlanesViewModel
import com.example.findaplane.R
import com.example.findaplane.model.SavedPlaneEntity
import java.time.Instant

class InfoFragment() : Fragment() {

    private lateinit var viewModell: PlanesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModell = ViewModelProvider(this).get(PlanesViewModel::class.java)

        val viewModel: AirplanesNearbyViewModel by activityViewModels()
        val t0 = view.findViewById<TextView>(R.id.textView0)
        val t1 = view.findViewById<TextView>(R.id.textView1)
        val t2 = view.findViewById<TextView>(R.id.textView2)
        val t3 = view.findViewById<TextView>(R.id.textView3)
        val t4 = view.findViewById<TextView>(R.id.textView4)
        val t5 = view.findViewById<TextView>(R.id.textView5)
        val t6 = view.findViewById<TextView>(R.id.textView6)
        val t7 = view.findViewById<TextView>(R.id.textView7)
        val t8 = view.findViewById<TextView>(R.id.textView8)
        val t9 = view.findViewById<TextView>(R.id.textView9)

        val tmp = "1" + viewModel.all.value?.get3(viewModel.posit).toString().substring(2,11)
        val tmpp = "1" + viewModel.all.value?.get4(viewModel.posit).toString().substring(2,11)


        t0.text ="icao24: " + viewModel.all.value?.get0(viewModel.posit).toString()
        t1.text ="callsign: " + viewModel.all.value?.get1(viewModel.posit).toString()
        t2.text ="origin country: " + viewModel.all.value?.get2(viewModel.posit).toString()
        t3.text ="time position: " + Instant.ofEpochSecond(tmp.toLong());
        t4.text ="last contact: " + Instant.ofEpochSecond(tmpp.toLong());
        t5.text ="longitude: " + viewModel.all.value?.get5(viewModel.posit).toString() + "\u00B0"
        t6.text ="latitude: " + viewModel.all.value?.get6(viewModel.posit).toString() + "\u00B0"
        t7.text ="baro altitude: " + viewModel.all.value?.get7(viewModel.posit).toString() + "m"
        t8.text ="on ground: " + viewModel.all.value?.get8(viewModel.posit).toString()
        t9.text ="velocity: " + viewModel.all.value?.get9(viewModel.posit).toString() + "m/s"

        (view.findViewById<Button>(R.id.wroc)).setOnClickListener{

            it.findNavController().navigate(R.id.action_infoFragment_to_airplanesNearby)

        }



        (view.findViewById<Button>(R.id.buttonZapisz)).setOnClickListener{


            val plane = SavedPlaneEntity(0, t0.text as String,
                t1.text as String,t2.text as String,t3.text as String,t4.text as String,t5.text as String,t6.text as String,t7.text as String,t8.text as String,t9.text as String)

            viewModell.addUser(plane)
            Toast.makeText(requireContext(),"Dodano samolot!", Toast.LENGTH_LONG).show()

        }

    }

}