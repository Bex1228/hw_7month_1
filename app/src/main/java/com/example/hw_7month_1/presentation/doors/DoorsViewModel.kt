package com.example.hw_7month_1.presentation.doors

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hw_7month_1.data.remote.Repository
import com.example.hw_7month_1.data.remote.Resource
import com.example.hw_7month_1.data.response.DoorsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DoorsViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    fun getDoors(): LiveData<Resource<List<DoorsModel.Data>>> = repository.getDoors()
}