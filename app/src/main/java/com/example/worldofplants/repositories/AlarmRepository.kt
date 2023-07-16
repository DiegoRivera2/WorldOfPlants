package com.example.worldofplants.repositories

import com.example.worldofplants.data.dao.AlarmDao
import com.example.worldofplants.data.model.AlarmModel

class AlarmRepository(private val AlarmsDao: AlarmDao) {

    suspend fun getAllAlarms() = AlarmsDao.getAllAlarms()

    suspend fun insertAlarms(alarm: AlarmModel) = AlarmsDao.insertAlarm(alarm)


}