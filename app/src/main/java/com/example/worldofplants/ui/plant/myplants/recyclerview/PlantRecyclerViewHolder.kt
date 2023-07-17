package com.example.worldofplants.ui.plant.myplants.recyclerview

import android.widget.ExpandableListView.OnChildClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.worldofplants.data.model.PlantModel
import com.example.worldofplants.databinding.PlantItemBinding

class PlantRecyclerViewHolder(private val binding: PlantItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(plant: PlantModel, clickListener: (PlantModel) -> Unit){
        binding.titleTextView.text = plant.name

        binding.plantItemCardView.setOnClickListener{
            clickListener(plant)
        }
    }
}