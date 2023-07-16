package com.example.worldofplants.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.worldofplants.data.model.AlarmModel

@Dao
interface AlarmDao {

    @Query("SELECT * FROM alarm_table")
    suspend fun getAllAlarms(): List<AlarmModel>

    @Transaction
    @Insert
    suspend fun insertAlarm(alarm: AlarmModel)
}