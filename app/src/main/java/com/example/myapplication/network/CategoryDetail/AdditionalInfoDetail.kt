package com.example.myapplication.network.CategoryDetail

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class AdditionalInfoDetail(
    val difficulty:Difficulty?,
    val meals:List<Meals>?,
    val similarFoods:List<String>?,
)
@JsonClass(generateAdapter = true)
data class Difficulty(
    val id:String,
    val name:String,
)

@JsonClass(generateAdapter = true)
data class Meals(
    val id:String,
    val name:String,
)