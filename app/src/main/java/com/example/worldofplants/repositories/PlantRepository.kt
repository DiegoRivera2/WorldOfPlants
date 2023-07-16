package com.example.worldofplants.repositories

import androidx.room.Dao
import com.example.worldofplants.data.dao.PlantDao
import com.example.worldofplants.data.model.PlantModel

class PlantRepository(private val plantsDao: PlantDao) {

    suspend fun getPlants() = plantsDao.getAllPlants()

    suspend fun addPlants(plant: PlantModel) = plantsDao.insertPlant(plant)

    suspend fun getPlantsWithAlarm(id: Int) = plantsDao.getPlantWithAlarmById(id)
}