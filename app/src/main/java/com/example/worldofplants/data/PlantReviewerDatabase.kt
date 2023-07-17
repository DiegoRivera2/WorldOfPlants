package com.example.worldofplants.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.worldofplants.data.dao.AlarmDao
import com.example.worldofplants.data.dao.GroupDao
import com.example.worldofplants.data.dao.PlantDao
import com.example.worldofplants.data.model.AlarmModel
import com.example.worldofplants.data.model.GroupModel
import com.example.worldofplants.data.model.PlantModel

@Database(entities = [PlantModel::class, AlarmModel::class, GroupModel::class], version = 2)
abstract class PlantReviewerDatabase : RoomDatabase(){

    abstract fun plantDao(): PlantDao

    abstract fun alarmDao(): AlarmDao

    abstract fun groupDao(): GroupDao

    companion object{
        @Volatile
        private var INSTANCE: PlantReviewerDatabase? = null

        fun newInstance(application: Application): PlantReviewerDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room
                    .databaseBuilder(
                            application.applicationContext,
                            PlantReviewerDatabase::class.java,
                            "plant_reviewer"
                        ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }

    }

}