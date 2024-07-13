package com.example.myapplication.network.Search

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodData(
    val id:String,
    val categoryId:String,
    val name:String,
    val image:String?,
    val cookTime:Int?,
    val readyTime:Int?,
    val difficulty:String?
)
