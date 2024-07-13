package com.example.myapplication.network.category


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubCategories(
    val id: String,
    val name: String
)