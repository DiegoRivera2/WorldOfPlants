package com.example.worldofplants.data.model

import androidx.room.Entity

@Entity(tableName = "group_table", primaryKeys = ["plantId", "alarmId"])
data class GroupModel(
    val plantId: Int,
    val alarmId: Int
)
