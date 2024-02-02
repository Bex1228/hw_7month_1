package com.example.hw_7month_1.data

import androidx.lifecycle.LiveData
import com.example.hw_7month_1.base.BaseRepository
import com.example.hw_7month_1.response.CamerasModel
import com.example.hw_7month_1.response.DoorsModel
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService): BaseRepository(apiService) {
    fun getCameras():LiveData<Resource<List<CamerasModel.Data.Camera>>> = performRequest{
        apiService.getCameras().body()!!
    }

    fun getDoors():LiveData<Resource<List<DoorsModel.Data>>> = performRequest{
        apiService.getDoors().body()!!
    }
}