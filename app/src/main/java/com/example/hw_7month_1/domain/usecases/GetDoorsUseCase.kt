package com.example.hw_7month_1.domain.usecases

import androidx.lifecycle.LiveData
import com.example.hw_7month_1.data.Resource
import com.example.hw_7month_1.data.response.DoorsModel
import com.example.hw_7month_1.domain.repositories.DoorsRepository
import javax.inject.Inject


class GetDoorsUseCase @Inject constructor(private val doorsRepository: DoorsRepository) {
    fun getDoors(): LiveData<Resource<DoorsModel>> = doorsRepository.getDoors()
} 