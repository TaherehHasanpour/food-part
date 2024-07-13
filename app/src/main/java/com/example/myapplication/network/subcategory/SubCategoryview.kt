package com.example.myapplication.network.subcategory

import com.example.myapplication.network.subcategory.SubData
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubCategoryview(
    val data:List<SubData>
)
