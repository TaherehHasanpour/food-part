package com.example.myapplication.network.foodsByIds

import android.icu.util.Output
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    val data:List<FoodResponse>
)
