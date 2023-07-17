package com.example.worldofplants.ui.plant.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.worldofplants.PlantReviewerApplication
import com.example.worldofplants.data.model.PlantModel
import com.example.worldofplants.repositories.PlantRepository
import kotlinx.coroutines.launch

class PlantViewModel(private val repository: PlantRepository): ViewModel() {
    var id = MutableLiveData(0)
    var name = MutableLiveData("")
    var description = MutableLiveData("")
    var status = MutableLiveData("")


    suspend fun getPlants() = repository.getPlants()

    suspend fun getPlantWithAlarmById(plantId: Int) = repository.getPlantsWithAlarm(plantId)

    private fun addPlants(plant: PlantModel){
        viewModelScope.launch {
            repository.addPlants(plant)
        }
    }

    fun createPlant(){
        if(!validateData()){
            status.value = WRONG_INFORMATION
            return
        }

        val plant = PlantModel(
            name = name.value!!,
            description = description.value!!
        )

        addPlants(plant)
        clearData()

        status.value = PLANT_CREATED
    }

    private fun validateData(): Boolean{
        when{
            name.value.isNullOrEmpty() -> return false
            description.value.isNullOrEmpty() -> return false
        }
        return true
    }

    fun clearData(){
        id.value = 0
        name.value = ""
        description.value = ""

    }

    fun clearStatus(){
        status.value = INACTIVE
    }

    fun setSelectedPlant(plant: PlantModel){
        id.value = plant.plantId
        name.value = plant.name
        description.value = plant.description
    }

    companion object{
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as PlantReviewerApplication
                PlantViewModel(app.plantRepository)
            }
        }

        const val PLANT_CREATED = "Movie created"
        const val WRONG_INFORMATION = "Wrong Information"
        const val INACTIVE = ""
    }








}