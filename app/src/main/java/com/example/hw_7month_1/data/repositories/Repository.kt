package com.example.hw_7month_1.data.repositories

import androidx.lifecycle.LiveData
import com.example.hw_7month_1.data.Resource
import com.example.hw_7month_1.data.remote.ApiService
import com.example.hw_7month_1.data.response.CamerasModel
import com.example.hw_7month_1.data.response.DoorsModel
import com.example.hw_7month_1.domain.repositories.CamerasRepository
import com.example.hw_7month_1.domain.repositories.DoorsRepository
import com.example.hw_7month_1.presentation.base.BaseRepository

class Repository (private val apiService: ApiService) : CamerasRepository, DoorsRepository, BaseRepository() {
    override fun getCameras(): LiveData<Resource<CamerasModel>> = performRequest{
        apiService.getCameras().body() as CamerasModel
    }

    override fun getDoors(): LiveData<Resource<DoorsModel>> = performRequest{
        apiService.getDoors().body() as DoorsModel
    }
}