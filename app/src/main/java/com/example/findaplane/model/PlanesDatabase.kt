package com.example.findaplane.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities=[SavedPlaneEntity::class], version = 1, exportSchema = false)
abstract class PlanesDatabase: RoomDatabase() {

    abstract val planesDAO: PlanesDAO


    companion object {

        @Volatile
        private var INSTANCE: PlanesDatabase? = null

        fun getInstance(context: Context): PlanesDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PlanesDatabase::class.java,
                        "PlanesDataBase"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}