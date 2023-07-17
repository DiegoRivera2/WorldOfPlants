package com.example.worldofplants.ui.groups.newgroup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.worldofplants.R
import com.example.worldofplants.data.model.AlarmModel
import com.example.worldofplants.data.model.PlantModel
import com.example.worldofplants.databinding.FragmentNewGroupsBinding
import com.example.worldofplants.ui.alarm.viewmodel.AlarmViewModel
import com.example.worldofplants.ui.groups.viewmodel.GroupsViewModel
import com.example.worldofplants.ui.plant.viewmodel.PlantViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewGroupsFragment : Fragment() {

    private val groupsViewModel: GroupsViewModel by activityViewModels {
        GroupsViewModel.Factory
    }

    private val plantsViewModel: PlantViewModel by activityViewModels {
        PlantViewModel.Factory
    }

    private val alarmsViewModel: AlarmViewModel by activityViewModels {
        AlarmViewModel.Factory
    }

    private lateinit var binding: FragmentNewGroupsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewGroupsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewModel()
        observeStatus()

        CoroutineScope(Dispatchers.Main).launch {
            getPlantsAndLaunchSpinner()
            getAlarmsAndLaunchSpinner()
        }
    }

    private fun setViewModel() {
        binding.viewmodel = groupsViewModel
    }

    private suspend fun getPlantsAndLaunchSpinner() {

        setPlantsSpinner(plantsViewModel.getPlants())
    }

    private fun setPlantsSpinner(plantsList: List<PlantModel>){
        val plantsAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, plantsList.map { it.name })
        binding.plantsSpinner.adapter = plantsAdapter

        binding.plantsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                val selectedPlant = plantsList[position]
                groupsViewModel.selectPlant(selectedPlant)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    private suspend fun getAlarmsAndLaunchSpinner(){
        setAlarmsSpinner(alarmsViewModel.getAllAlarms())
    }

    private fun setAlarmsSpinner(alarmList: List<AlarmModel>) {
        val alarmsAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, alarmList.map { it.hour })
        binding.alarmsSpinner.adapter = alarmsAdapter

        binding.alarmsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                val selectedAlarm = alarmList[position]
                groupsViewModel.selectAlarm(selectedAlarm)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    private fun observeStatus() {
        groupsViewModel.status.observe(viewLifecycleOwner) { status ->
            when {
                status.equals(GroupsViewModel.WRONG_INFORMATION) -> {
                    groupsViewModel.clearStatus()
                }
                status.equals(GroupsViewModel.GROUP_CREATED) -> {
                    groupsViewModel.clearStatus()
                    findNavController().popBackStack()
                }
            }
        }
    }






}