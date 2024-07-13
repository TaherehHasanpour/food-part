package com.example.myapplication.network.category


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(

    val id: String,
    val name: String,
    val image: String,
    val subCategories: List<SubCategories>?
) {

}