package com.example.hw_7month_1.data.repositories

import com.example.hw_7month_1.data.remote.ApiService
import com.example.hw_7month_1.data.utils.Resource
import com.example.hw_7month_1.data.utils.mapToCameraModel
import com.example.hw_7month_1.data.utils.mapToDoorModel
import com.example.hw_7month_1.domain.models.CamerasModel
import com.example.hw_7month_1.domain.models.DoorsModel
import com.example.hw_7month_1.domain.repositories.CamerasRepository
import com.example.hw_7month_1.domain.repositories.DoorsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository (private val apiService: ApiService) : CamerasRepository, DoorsRepository{
    override suspend fun getCameras(): Flow<Resource<CamerasModel>> =
        flow {
            emit(Resource.Loading())
            try {
                val result = apiService.getCameras().body()!!.mapToCameraModel()
                emit(Resource.Success(result))
            } catch (ex: Exception) {
                emit(Resource.Error(ex.localizedMessage))
            }
        }

    override suspend fun getDoors(): Flow<Resource<DoorsModel>> =
        flow {
            emit(Resource.Loading())
            try {
                val result = apiService.getDoors().body()!!.mapToDoorModel()
                emit(Resource.Success(result))
            }catch (ex:Exception){
                emit(Resource.Error(ex.localizedMessage))
            }
        }
}