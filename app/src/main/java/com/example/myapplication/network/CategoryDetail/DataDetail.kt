package com.example.myapplication.network.CategoryDetail

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class DataDetail (
    val id:String,
    val categoryId:String,
    val name:String,
    val count:String?,
    val image:String,
    val difficulty:String,
    val point:String?,
    val cookTime:Int?,
    val readyTime:Int?,
    val recipe:String,
    val ingredients:String,
){

}