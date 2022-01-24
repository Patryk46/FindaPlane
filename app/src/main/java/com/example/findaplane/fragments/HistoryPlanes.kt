package com.example.findaplane.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.findaplane.HistoryPlanesViewModel
import com.example.findaplane.R
import com.example.findaplane.model.PlanesAdapter
import com.example.findaplane.model.SavedPlaneEntity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HistoryPlanes : Fragment() {

    lateinit var viewModell: HistoryPlanesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_planes, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModell = ViewModelProvider(this).get(HistoryPlanesViewModel::class.java)
        val usersListAdapter= PlanesAdapter(viewModell.users, viewModell)

        viewModell.users.observe(viewLifecycleOwner,
            Observer<List<SavedPlaneEntity>> { usersListAdapter.notifyDataSetChanged() }
        )


        val layoutManager= LinearLayoutManager(view.context)
        view.findViewById<RecyclerView>(R.id.historyRecycler).let {

            it.adapter=usersListAdapter
            it.layoutManager=layoutManager
        }


        view.findViewById<FloatingActionButton>(R.id.backFromHistory).apply {
            setOnClickListener {
                findNavController().navigate(R.id.action_historyPlanes_to_menuFragment)
            }
        }
    }
}