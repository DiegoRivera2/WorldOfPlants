package com.example.worldofplants.ui.plant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worldofplants.R
import com.example.worldofplants.databinding.FragmentMyPlantsBinding
import com.example.worldofplants.databinding.FragmentPlantBinding
import com.example.worldofplants.ui.groups.recyclerview.GroupRecyclerViewAdapter
import com.example.worldofplants.ui.plant.viewmodel.PlantViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PlantFragment : Fragment() {

    private val plantViewModel: PlantViewModel by activityViewModels{
        PlantViewModel.Factory
    }

    private lateinit var binding: FragmentPlantBinding

    private lateinit var adapter: GroupRecyclerViewAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(lifecycleScope.coroutineContext).launch {
            setRecyclerView(view)
        }

        setViewModel()
    }

    private fun setRecyclerView(view: View){
        binding.alarmsRecyclerView.layoutManager = LinearLayoutManager(view.context)

        adapter = GroupRecyclerViewAdapter()

        binding.alarmsRecyclerView.adapter = adapter
        displayGroup()
    }

    private fun displayGroup(){
        val plantId = plantViewModel.id
        CoroutineScope(lifecycleScope.coroutineContext).launch {
            val plantWithAlarm = plantViewModel.getPlantWithAlarmById(plantId.value!!)

            val alarms = plantWithAlarm?.alarms
            adapter.setData(alarms!!)

            adapter.notifyDataSetChanged()
        }
    }

    private fun setViewModel(){
        binding.viewmodel = plantViewModel
    }
}