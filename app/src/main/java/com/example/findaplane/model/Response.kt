package com.example.findaplane.model

import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonArray
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import com.example.findaplane.model.States

data class Response(
    val time:Int,
    val states: List<List<Any>>

) {
    val size: Int?
        get() {
            return states.size
        }

    fun get(position: Int): Any {
        return position
    }

    fun get0(position: Int): String {
        return states[position][0].toString()
    }
    fun get1(position: Int): Any {
        return states[position][1]
    }
    fun get2(position: Int): Any {
        return states[position][2]
    }
    fun get3(position: Int): Any {
        return states[position][3]
    }
    fun get4(position: Int): Any {
        return states[position][4]
    }
    fun get5(position: Int): Any {
        return states[position][5]
    }
    fun get6(position: Int): Any {
        return states[position][6]
    }
    fun get7(position: Int): Any {
        return states[position][7]
    }
    fun get8(position: Int): Any {
        return states[position][8]
    }
    fun get9(position: Int): Any {
        return states[position][9]
    }


}