package com.example.findaplane.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plane_info")
class SavedPlaneEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val id: Int,
    @ColumnInfo(name="icao24")
    val icao24:String,
    @ColumnInfo(name="callsign")
    val callsign:String,
    @ColumnInfo(name="origin")
    val origin:String,
    @ColumnInfo(name="time_position")
    val time_position:String,
    @ColumnInfo(name="last_contact")
    val last_contact:String,
    @ColumnInfo(name="longitude")
    val longitude:String,
    @ColumnInfo(name="latitude")
    val latitude:String,
    @ColumnInfo(name="baro_altitude")
    val baro_altitude:String,
    @ColumnInfo(name="on_ground")
    val on_ground:String,
    @ColumnInfo(name="velocity")
    val velocity:String


)