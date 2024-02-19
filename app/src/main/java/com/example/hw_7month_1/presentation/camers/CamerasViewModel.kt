package com.example.hw_7month_1.presentation.camers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw_7month_1.data.utils.Resource
import com.example.hw_7month_1.domain.models.CamerasModel
import com.example.hw_7month_1.domain.usecases.GetCamerasUseCase
import com.example.hw_7month_1.domain.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CamerasViewModel @Inject constructor(private val getCamerasUseCase: GetCamerasUseCase): ViewModel() {
    private var _camerasFlow = MutableStateFlow<UiState<CamerasModel>>(UiState.Loading())
    var camerasFlow: StateFlow<UiState<CamerasModel>> = _camerasFlow

    suspend fun getCameras() {
        viewModelScope.launch {
            getCamerasUseCase.getCameras().collect {
                when (it) {
                    is Resource.Error -> _camerasFlow.value = UiState.Error(it.message)
                    is Resource.Loading -> _camerasFlow.value = UiState.Loading()
                    is Resource.Success -> {
                        if (it.data != null) {
                            _camerasFlow.value = UiState.Success(it.data)
                        } else {
                            _camerasFlow.value = UiState.Empty("DATA IS EMPTY")
                        }
                    }
                }
            }
        }
    }
}