package com.example.findaplane.model

import java.util.*

data class States(
    val icao: String,
    val callsign: String?,
    val origin_country: String,
    val time_position: Int?,
    val last_contact: Int?,
    val longitude: Float?,
    val latitude: Float?,
    val baro_altitude: Float?,
    val on_ground: Boolean,
    val velocity: Float?,
    val true_track: Float?,
    val vertical_rate: Float?,
    val sensors: IntArray?,
    val geo_altitude: Float?,
    val squawk: String?,
    val spi: Boolean,
    val position_source:Int
)