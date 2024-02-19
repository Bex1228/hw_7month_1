package com.example.hw_7month_1.domain.repositories

import com.example.hw_7month_1.data.utils.Resource
import com.example.hw_7month_1.domain.models.DoorsModel
import kotlinx.coroutines.flow.Flow

interface DoorsRepository {
    suspend fun getDoors(): Flow<Resource<DoorsModel>>
}