package com.example.hw_7month_1.presentation.camers

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hw_7month_1.data.Resource
import com.example.hw_7month_1.data.response.CamerasModel
import com.example.hw_7month_1.domain.usecases.GetCamerasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CamerasViewModel @Inject constructor(private val getCamerasUseCase: GetCamerasUseCase): ViewModel() {
    fun getCameras(): LiveData<Resource<CamerasModel>> = getCamerasUseCase.getCameras()
}