package com.example.worldofplants.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.worldofplants.data.model.PlantModel
import com.example.worldofplants.data.model.PlantWithAlarm

@Dao
interface PlantDao {

    @Query("SELECT * FROM plant_table")
    suspend fun getAllPlants(): List<PlantModel>

    @Transaction
    @Insert
    suspend fun insertPlant(plant: PlantModel)

    @Query("SELECT * FROM plant_table WHERE plantId = :plantId")
    suspend fun getPlantWithAlarmsById(plantId: Int): PlantWithAlarm?


}