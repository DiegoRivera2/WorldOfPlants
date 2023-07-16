package com.example.worldofplants.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation


data class PlantWithAlarm(
    @Embedded val plant: PlantModel,
    @Relation(
        parentColumn = "plantId",
        entityColumn = "alarmId",
        associateBy = Junction(GroupModel::class)
    )
    val alarms: List<AlarmModel>
)