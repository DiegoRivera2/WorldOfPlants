package com.example.worldofplants.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plant_table")
data class PlantModel (
    @PrimaryKey(autoGenerate = true) val plantId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
)
{
    constructor(name: String, description: String)
    :this(0, name, description)
}