package com.example.findaplane.model

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface PlanesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: SavedPlaneEntity)

    @Delete
    fun deleteUser(user: SavedPlaneEntity)

    @Query("SELECT * FROM plane_info")
    fun getAll(): LiveData<List<SavedPlaneEntity>>

    @Query("SELECT * FROM plane_info WHERE id = :id")
    fun onePlane(id:Int): SavedPlaneEntity
}