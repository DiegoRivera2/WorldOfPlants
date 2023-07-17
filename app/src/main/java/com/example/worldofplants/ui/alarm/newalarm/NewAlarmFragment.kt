package com.example.worldofplants.ui.alarm.newalarm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.worldofplants.R
import com.example.worldofplants.databinding.FragmentNewAlarmBinding
import com.example.worldofplants.ui.alarm.viewmodel.AlarmViewModel

class NewAlarmFragment : Fragment() {

    private val alarmViewModel: AlarmViewModel by activityViewModels{
        AlarmViewModel.Factory
    }
    private lateinit var binding: FragmentNewAlarmBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewAlarmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewModel()
        observeStatus()

    }

    private fun setViewModel(){
        binding.viewmodel = alarmViewModel
    }

    private fun observeStatus(){
        alarmViewModel.status.observe(viewLifecycleOwner){ status ->
            when{
                status.equals(AlarmViewModel.WRONG_INFORMATION) -> {
                    alarmViewModel.clearStatus()
                }
                status.equals(AlarmViewModel.ALARM_CREATED) ->{
                    alarmViewModel.clearStatus()
                    findNavController().popBackStack()
                }
            }
        }
    }
}