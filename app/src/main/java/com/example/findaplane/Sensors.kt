package com.example.findaplane

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Sensors: AppCompatActivity(), SensorEventListener {
    private lateinit var square: TextView
    private lateinit var square2: TextView
    private lateinit var sensorManager: SensorManager
    private lateinit var sensorManager2: SensorManager

    companion object {
        var akcelerometr_value: Float = 0.0f
        var kompas_value: Float = 0.0f

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensors)

        //square = findViewById<TextView>(R.id.kierunek)
        //square2 = findViewById<TextView>(R.id.kompas)
        //square.text = "kierunek"
        //square2.text = "kompas"

   // fun setUpSensorStuff() {
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also {
            sensorManager.registerListener(
                this,
                it,
                SensorManager.SENSOR_DELAY_FASTEST
            )
        }
        // Kompas:
        sensorManager2 = getSystemService(SENSOR_SERVICE) as SensorManager
        sensorManager2.getDefaultSensor(Sensor.TYPE_ORIENTATION)?.also {
            sensorManager2.registerListener(
                this,
                it,
                SensorManager.SENSOR_DELAY_FASTEST
            )

        }
   // }

}

    override fun onSensorChanged(event: SensorEvent?) {
        if(event?.sensor?.type == Sensor.TYPE_ACCELEROMETER){
            val y = event.values[1]

            //square.text = "Kąt:\n ${Math.round(y.toFloat()*9)}"
            akcelerometr_value = Math.round(y.toFloat()*9).toFloat()

        }
        else if(event?.sensor?.type == Sensor.TYPE_ORIENTATION){
            val strona = event.values[0]
            //square2.text = "Kierunek świata:\n ${Math.round(strona.toFloat())}"
            kompas_value = Math.round(strona.toFloat()).toFloat()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onDestroy() {
        sensorManager.unregisterListener(this)
        sensorManager2.unregisterListener(this)
        super.onDestroy()
    }
}