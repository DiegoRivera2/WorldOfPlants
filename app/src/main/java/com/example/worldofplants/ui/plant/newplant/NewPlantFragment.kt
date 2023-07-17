package com.example.worldofplants.ui.plant.newplant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.worldofplants.R
import com.example.worldofplants.databinding.FragmentNewPlantBinding
import com.example.worldofplants.ui.plant.viewmodel.PlantViewModel

class NewPlantFragment : Fragment() {

    private val plantViewModel: PlantViewModel by activityViewModels{
        PlantViewModel.Factory
    }

    private lateinit var binding: FragmentNewPlantBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewPlantBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewModel()
        observeStatus()
    }

    private fun setViewModel(){
        binding.viewmodel = plantViewModel
    }

    private fun observeStatus(){
        plantViewModel.status.observe(viewLifecycleOwner){ status ->
            when{
                status.equals(PlantViewModel.WRONG_INFORMATION) -> {
                    plantViewModel.clearStatus()
                }
                status.equals(PlantViewModel.PLANT_CREATED) -> {
                    plantViewModel.clearStatus()
                    findNavController().popBackStack()
                }
            }
        }
    }

}