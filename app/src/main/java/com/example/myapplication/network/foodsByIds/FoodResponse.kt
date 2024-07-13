package com.example.myapplication.network.foodsByIds

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodResponse(
val id:String,
val categoryId:String,
val name:String,
val image:String,
val cookTime:Int?,
val readyTime:Int?,
)
