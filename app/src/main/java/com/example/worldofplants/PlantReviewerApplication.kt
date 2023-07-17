package com.example.worldofplants

import android.app.Application
import com.example.worldofplants.data.PlantReviewerDatabase
import com.example.worldofplants.repositories.AlarmRepository
import com.example.worldofplants.repositories.GroupRepository
import com.example.worldofplants.repositories.PlantRepository

class PlantReviewerApplication: Application() {
    private val database: PlantReviewerDatabase by lazy{
        PlantReviewerDatabase.newInstance(this)
    }

    val plantRepository: PlantRepository by lazy{
        PlantRepository(database.plantDao())
    }
    val alarmRepository: AlarmRepository by lazy{
        AlarmRepository(database.alarmDao())
    }
    val groupRepository: GroupRepository by lazy{
        GroupRepository(database.groupDao())
    }
}