package com.example.worldofplants.repositories

import com.example.worldofplants.data.dao.PlantDao
import com.example.worldofplants.data.model.PlantModel

class PlantRepository(private val plantsDao: PlantDao) {

    suspend fun getPlants() = plantsDao.getAllPlants()

    suspend fun addPlants(plant: PlantModel) = plantsDao.insertPlant(plant)

    suspend fun getPlantsWithAlarms(id: Int) = plantsDao.getPlantWithAlarmsById(id)
}