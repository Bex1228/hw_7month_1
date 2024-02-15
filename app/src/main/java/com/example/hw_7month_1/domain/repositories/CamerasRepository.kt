package com.example.hw_7month_1.domain.repositories

import androidx.lifecycle.LiveData
import com.example.hw_7month_1.data.Resource
import com.example.hw_7month_1.data.response.CamerasModel

interface CamerasRepository {
    fun getCameras(): LiveData<Resource<CamerasModel>>
}