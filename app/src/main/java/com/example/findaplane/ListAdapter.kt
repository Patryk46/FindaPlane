package com.example.findaplane

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.findaplane.model.Response


class ListAdapter(private val planes: MutableLiveData<Response>, private val viewModel: AirplanesNearbyViewModel)
    : RecyclerView.Adapter<ListAdapter.ListHolder>()
{
    inner class ListHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        val icao=view.findViewById<TextView>(R.id.icao)
        val callsign=view.findViewById<TextView>(R.id.callsign)
        val origin_country=view.findViewById<TextView>(R.id.origin_country)
        val info = view.findViewById<Button>(R.id.info)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val view= LayoutInflater.from(parent.context).
        inflate(R.layout.one_row,parent,false)
        return ListHolder(view)
    }

    // dla kazdego wykrytego samolotu, do kazdej danej do wyswietlenia wrzucamy zawartosc listy planes
    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.icao.text="ICAO: " + planes.value?.get0(position).toString()
        holder.callsign.text=planes.value?.get1(position).toString()
        holder.origin_country.text="from: " + planes.value?.get2(position).toString()

        holder.info.setOnClickListener {
            planes.value?.let{ p->
                viewModel.posit = p.get(position) as Int
                it.findNavController()
                    .navigate(R.id.action_airplanesNearby_to_infoFragment)
            }
        }
    }

    override fun getItemCount():Int {

        return planes.value?.size?:0
    }
}