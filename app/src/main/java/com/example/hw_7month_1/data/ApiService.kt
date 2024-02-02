package com.example.hw_7month_1.data

import com.example.hw_7month_1.response.CamerasModel
import com.example.hw_7month_1.response.DoorsModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("cameras")
    suspend fun getCameras(): Response<List<CamerasModel.Data.Camera>>

    @GET("doors")
    suspend fun getDoors():Response<List<DoorsModel.Data>>
}