package com.example.worldofplants.ui.plant.myplants.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ExpandableListView.OnChildClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.worldofplants.data.model.PlantModel
import com.example.worldofplants.databinding.PlantItemBinding

class PlantRecyclerViewAdapter(
    private val clickListener: (PlantModel) -> Unit
) : RecyclerView.Adapter<PlantRecyclerViewHolder>(){
    private val plants = ArrayList<PlantModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantRecyclerViewHolder {
        val binding = PlantItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlantRecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return plants.size
    }

    override fun onBindViewHolder(holder: PlantRecyclerViewHolder, position: Int) {
        val plant = plants[position]
        holder.bind(plant, clickListener)
    }

    fun setData(plantsList: List<PlantModel>){
        plants.clear()
        plants.addAll(plantsList)
    }


}