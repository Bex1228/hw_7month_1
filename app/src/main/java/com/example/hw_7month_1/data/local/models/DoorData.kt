package com.example.hw_7month_1.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "door_data")
data class DoorData(
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,
    val count:Int
)