package com.example.hw_7month_1.domain.repositories

import com.example.hw_7month_1.data.utils.Resource
import com.example.hw_7month_1.domain.models.CamerasModel
import kotlinx.coroutines.flow.Flow

interface CamerasRepository {
    suspend fun getCameras(): Flow<Resource<CamerasModel>>
}