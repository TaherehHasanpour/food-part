package com.example.myapplication.network.Cook

import com.example.myapplication.network.Search.FoodData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CookResponse(
    @Json(name = "data")
    val searchData:List<FoodData>
)
