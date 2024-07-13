package com.example.myapplication.network.subcategory

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubData(
    val id :String,
    val categoryId:String,
    val name:String,
    val image:String,
    val readyTime:Int?,
    val cookTime:Int?,
)
