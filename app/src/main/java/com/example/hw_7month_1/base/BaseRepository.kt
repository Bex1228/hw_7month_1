package com.example.hw_7month_1.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.hw_7month_1.data.ApiService
import com.example.hw_7month_1.data.Resource
import kotlinx.coroutines.Dispatchers

abstract class BaseRepository(private val api:ApiService) {
    fun <T> performRequest(apiCall: suspend () -> T):LiveData<Resource<T>> =
        liveData(Dispatchers.Main){
            emit(Resource.Loading())
            try {
                val response = apiCall.invoke()
                emit(Resource.Success(response))
            }catch (ex:Exception){
                emit(Resource.Error(ex.localizedMessage ?: "Error"))
                Log.e("ololo", "${ex.message}")
            }
        }
}