package com.example.hw_7month_1.data.utils

import com.example.hw_7month_1.domain.models.CamerasModel
import com.example.hw_7month_1.domain.models.DoorsModel

fun CamerasModel.mapToCameraModel() =
    CamerasModel(
        data = data,
        success = success
    )

fun DoorsModel.mapToDoorModel() =
    DoorsModel(
        data = data,
        success = success
    )