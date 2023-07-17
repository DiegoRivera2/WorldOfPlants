package com.example.worldofplants.ui.groups.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.worldofplants.PlantReviewerApplication
import com.example.worldofplants.data.model.AlarmModel
import com.example.worldofplants.data.model.GroupModel
import com.example.worldofplants.data.model.PlantModel
import com.example.worldofplants.repositories.GroupRepository
import kotlinx.coroutines.launch

class GroupsViewModel(private val repository: GroupRepository) : ViewModel() {
    var plantId = MutableLiveData("")
    var alarmId = MutableLiveData("")
    var status = MutableLiveData("")

    fun selectPlant(plant: PlantModel){
        plantId.value = plant.plantId.toString()
    }

    fun selectAlarm(alarm: AlarmModel){
        alarmId.value = alarm.alarmId.toString()
    }

    private fun addGroups(groups: GroupModel){
        viewModelScope.launch{
            repository.addGroups(groups)
        }
    }

    fun createGroups(){
        if (!validateData()) {
            status.value = WRONG_INFORMATION
            return
        }

        val groups = GroupModel(
            plantId = plantId.value?.toInt()!!,
            alarmId = alarmId.value?.toInt()!!,
        )

        addGroups(groups)
        clearData()

        status.value = GROUP_CREATED
    }

    private fun validateData(): Boolean {
        when {
            plantId.value.isNullOrEmpty() -> return false
            alarmId.value.isNullOrEmpty() -> return false
            plantId.value!!.toInt() == 0 -> return false
            alarmId.value!!.toInt() == 0 -> return false
        }
        return true
    }

    private fun clearData() {
        plantId.value = ""
        plantId.value = ""
    }

    fun clearStatus() {
        status.value = INACTIVE
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PlantReviewerApplication
                GroupsViewModel(app.groupRepository)
            }
        }

        const val GROUP_CREATED = "Cast created"
        const val WRONG_INFORMATION = "Wrong information"
        const val INACTIVE = ""
    }


}