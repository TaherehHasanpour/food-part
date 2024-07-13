package com.example.myapplication.network.category


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryResponse(
    val data: List<Data>
)