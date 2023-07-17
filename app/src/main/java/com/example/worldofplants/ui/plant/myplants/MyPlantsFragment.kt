package com.example.worldofplants.ui.plant.myplants

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worldofplants.R
import com.example.worldofplants.data.model.PlantModel
import com.example.worldofplants.databinding.FragmentMyPlantsBinding
import com.example.worldofplants.ui.plant.myplants.recyclerview.PlantRecyclerViewAdapter
import com.example.worldofplants.ui.plant.viewmodel.PlantViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyPlantsFragment : Fragment() {

    private val plantViewModel: PlantViewModel by activityViewModels {
        PlantViewModel.Factory
    }

    private lateinit var adapter: PlantRecyclerViewAdapter

    private lateinit var binding: FragmentMyPlantsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyPlantsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            setRecyclerView(view)
        }

        binding.btnNavCreatePlant.setOnClickListener{
            plantViewModel.clearData()
            it.findNavController().navigate(R.id.action_myPlantsFragment_to_newPlantFragment)
        }

        binding.btnNavCreateAlarm.setOnClickListener {
            plantViewModel.clearData()
            it.findNavController().navigate(R.id.action_myPlantsFragment_to_newAlarmFragment)
        }

        binding.btnNavCreateGroup.setOnClickListener {
            it.findNavController().navigate(R.id.action_myPlantsFragment_to_newGroupsFragment)
        }
    }

    private suspend fun setRecyclerView(view: View){
        binding.recyclerView.layoutManager = LinearLayoutManager(view.context)

        adapter = PlantRecyclerViewAdapter { selectedPlant ->
            showSelectedItem(selectedPlant)
        }

        binding.recyclerView.adapter = adapter
        displayPlants()
    }

    private suspend fun displayPlants(){

        adapter.setData(plantViewModel.getPlants())
        adapter.notifyDataSetChanged()
    }

    private fun showSelectedItem(plant: PlantModel){
        plantViewModel.setSelectedPlant(plant)
        findNavController().navigate(R.id.action_myPlantsFragment_to_plantFragment)
    }


}