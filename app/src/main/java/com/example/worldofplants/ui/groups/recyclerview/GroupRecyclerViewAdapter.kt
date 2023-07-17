package com.example.worldofplants.ui.groups.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.worldofplants.data.model.AlarmModel
import com.example.worldofplants.databinding.AlarmItemBinding

class GroupRecyclerViewAdapter: RecyclerView.Adapter<GroupRecyclerViewHolder>(){

    private val group = ArrayList<AlarmModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupRecyclerViewHolder {
        val binding = AlarmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GroupRecyclerViewHolder(binding)
    }

    override fun getItemCount() = group.size

    override fun onBindViewHolder(holder: GroupRecyclerViewHolder, position: Int) {
        val alarm = group[position]
        holder.bind(alarm)
    }

    fun setData(groupList: List<AlarmModel>){
        group.clear()
        group.addAll(groupList)
    }

}