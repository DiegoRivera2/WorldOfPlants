package com.example.worldofplants.ui.groups.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.example.worldofplants.data.model.AlarmModel
import com.example.worldofplants.databinding.AlarmItemBinding

class GroupRecyclerViewHolder(private val binding: AlarmItemBinding):  RecyclerView.ViewHolder(binding.root){

    fun bind(alarm: AlarmModel){
        binding.hourTextView.text = alarm.hour
    }
}