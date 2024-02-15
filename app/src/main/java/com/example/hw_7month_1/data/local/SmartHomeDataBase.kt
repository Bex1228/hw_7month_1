package com.example.hw_7month_1.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hw_7month_1.data.local.models.CameraData
import com.example.hw_7month_1.data.local.models.DoorData

@Database(entities = [CameraData::class, DoorData::class], version = 1)
abstract class SmartHomeDatabase : RoomDatabase() {
    abstract fun smartHomeDao(): SmartHomeDao
}