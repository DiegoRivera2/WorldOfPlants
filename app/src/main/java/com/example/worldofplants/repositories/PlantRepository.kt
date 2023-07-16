package com.example.worldofplants.repositories

import androidx.room.Dao

class PlantRepository(private val plantsDao: PlantDao) {

    suspend fun getPlants() = plantsDao.getAllPlants()

    suspend fun AddPlants(plant: PlantModel) = plantsDao.inserPlant(plant)

    suspend fun getPlantsWithGroups(id: Int) = plantsDao.getPlantsWithGroupsById(Id)
}