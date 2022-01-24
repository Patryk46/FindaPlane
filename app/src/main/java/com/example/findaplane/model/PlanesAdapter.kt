package com.example.findaplane.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.findaplane.HistoryPlanesViewModel
import com.example.findaplane.R

class PlanesAdapter(private val users: LiveData<List<SavedPlaneEntity>>, private val viewModel: HistoryPlanesViewModel): RecyclerView.Adapter<PlanesAdapter.ListViewHolder>() {


    inner class ListViewHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        val icao=view.findViewById<TextView>(R.id.icaoH)
        val callsign=view.findViewById<TextView>(R.id.callsignH)
        val origin=view.findViewById<TextView>(R.id.origin_countryH)
        val buttonInfo=view.findViewById<Button>(R.id.infoH)
        val buttonDelete=view.findViewById<Button>(R.id.deleteButtonH)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view= LayoutInflater.from(parent.context).
        inflate(R.layout.one_row_for_history,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        holder.icao.text=users.value?.get(position)?.icao24
        holder.callsign.text=users.value?.get(position)?.callsign
        holder.origin.text=users.value?.get(position)?.origin
        val bundle = bundleOf("position" to users.value?.get(position)?.id)

        holder.buttonInfo.apply {
            setOnClickListener {
                findNavController().navigate(R.id.action_historyPlanes_to_historyInfo, bundle)

            }
        }
        holder.buttonDelete.setOnClickListener {
                users.value?.let { existingUsers ->
                    viewModel.deleteUser(existingUsers.get(position))

                }
        }

    }

    override fun getItemCount()=users.value?.size?:0


}