package com.example.myapplication.network.Search

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
@JsonClass(generateAdapter = true)
data class SearchResponse(
    @Json(name = "data")
    val searchData:List<FoodData>
)
