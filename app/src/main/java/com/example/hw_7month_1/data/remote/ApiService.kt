package com.example.hw_7month_1.data.remote

import com.example.hw_7month_1.data.response.CamerasModel
import com.example.hw_7month_1.data.response.DoorsModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("cameras")
    suspend fun getCameras(): Response<CamerasModel>

    @GET("doors")
    suspend fun getDoors():Response<DoorsModel>
}