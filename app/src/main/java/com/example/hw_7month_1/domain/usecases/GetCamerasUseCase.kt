package com.example.hw_7month_1.domain.usecases

import com.example.hw_7month_1.domain.repositories.CamerasRepository
import javax.inject.Inject

class GetCamerasUseCase @Inject constructor(private val camerasRepository: CamerasRepository) {
    suspend fun getCameras() = camerasRepository.getCameras()
}