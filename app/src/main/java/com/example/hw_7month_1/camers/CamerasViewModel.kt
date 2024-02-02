package com.example.hw_7month_1.camers

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hw_7month_1.data.Repository
import com.example.hw_7month_1.data.Resource
import com.example.hw_7month_1.response.CamerasModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CamerasViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    fun getCameras(): LiveData<Resource<List<CamerasModel.Data.Camera>>> = repository.getCameras()
}