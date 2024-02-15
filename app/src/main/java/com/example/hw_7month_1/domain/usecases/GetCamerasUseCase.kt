package com.example.hw_7month_1.domain.usecases

import androidx.lifecycle.LiveData
import com.example.hw_7month_1.data.Resource
import com.example.hw_7month_1.data.response.CamerasModel
import com.example.hw_7month_1.domain.repositories.CamerasRepository
import javax.inject.Inject

class GetCamerasUseCase @Inject constructor(private val camerasRepository: CamerasRepository) {
    fun getCameras(): LiveData<Resource<CamerasModel>> = camerasRepository.getCameras()
}