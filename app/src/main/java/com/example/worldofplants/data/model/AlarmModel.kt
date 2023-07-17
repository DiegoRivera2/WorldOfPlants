package com.example.worldofplants.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarm_table")
data class AlarmModel(
    @PrimaryKey(autoGenerate = true) val alarmId: Int,
    @ColumnInfo(name = "hour") val hour: String,
)
{
    constructor(hour: String):
            this(0, hour)
}