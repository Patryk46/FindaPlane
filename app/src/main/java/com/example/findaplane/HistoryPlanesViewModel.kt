package com.example.findaplane

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.findaplane.model.PlanesDAO
import com.example.findaplane.model.PlanesDatabase
import com.example.findaplane.model.SavedPlaneEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryPlanesViewModel  (
    application: Application
):
    AndroidViewModel(application) {

    private val planesDAO : PlanesDAO = PlanesDatabase.getInstance(application).planesDAO
    val users: LiveData<List<SavedPlaneEntity>> = planesDAO.getAll()


    fun deleteUser(user: SavedPlaneEntity)
    {
        viewModelScope.launch(Dispatchers.IO) {
            planesDAO.deleteUser(user)
        }
    }

}