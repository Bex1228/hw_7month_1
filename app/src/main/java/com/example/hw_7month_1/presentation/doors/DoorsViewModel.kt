package com.example.hw_7month_1.presentation.doors

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hw_7month_1.data.Resource
import com.example.hw_7month_1.data.response.DoorsModel
import com.example.hw_7month_1.domain.usecases.GetDoorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DoorsViewModel @Inject constructor(private val getDoorsUseCase: GetDoorsUseCase): ViewModel() {
    fun getDoors(): LiveData<Resource<DoorsModel>> = getDoorsUseCase.getDoors()
}