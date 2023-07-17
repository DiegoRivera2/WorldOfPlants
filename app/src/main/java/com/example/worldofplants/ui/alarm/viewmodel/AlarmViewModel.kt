package com.example.worldofplants.ui.alarm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.worldofplants.PlantReviewerApplication
import com.example.worldofplants.data.model.AlarmModel
import com.example.worldofplants.repositories.AlarmRepository
import com.example.worldofplants.ui.plant.viewmodel.PlantViewModel
import com.example.worldofplants.ui.plant.viewmodel.PlantViewModel.Companion.INACTIVE
import kotlinx.coroutines.launch

class AlarmViewModel(private var repository: AlarmRepository) : ViewModel(){
    var hour = MutableLiveData("")
    var status = MutableLiveData("")

    suspend fun getAllAlarms() = repository.getAllAlarms()

    private fun addAlarm(alarm: AlarmModel){
        viewModelScope.launch {
            repository.insertAlarms(alarm)
        }
    }
    fun createAlarm(){
        if (!validateData()){
            status.value = PlantViewModel.WRONG_INFORMATION
            return
        }

        val alarm = AlarmModel(
            0,
            hour = hour.value!!,
        )

        addAlarm(alarm)
        clearData()

        status.value = AlarmViewModel.ALARM_CREATED
    }

    private fun validateData(): Boolean {
        when {
            hour.value.isNullOrEmpty() -> return false
        }
        return true
    }

    fun clearData() {
        hour.value = ""

    }

    fun clearStatus() {
        status.value = INACTIVE
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PlantReviewerApplication
                AlarmViewModel(app.alarmRepository)
            }
        }

        const val ALARM_CREATED = "Actor created"
        const val WRONG_INFORMATION = "Wrong information"
        const val INACTIVE = ""
    }

}