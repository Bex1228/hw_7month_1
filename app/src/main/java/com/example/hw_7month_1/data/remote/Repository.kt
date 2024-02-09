package com.example.hw_7month_1.data.remote

import androidx.lifecycle.LiveData
import com.example.hw_7month_1.presentation.base.BaseRepository
import com.example.hw_7month_1.data.response.CamerasModel
import com.example.hw_7month_1.data.response.DoorsModel
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService): BaseRepository(apiService) {
    fun getCameras():LiveData<Resource<List<CamerasModel.Data.Camera>>> = performRequest{
        apiService.getCameras().body()!!
    }

    fun getDoors():LiveData<Resource<List<DoorsModel.Data>>> = performRequest{
        apiService.getDoors().body()!!
    }
}