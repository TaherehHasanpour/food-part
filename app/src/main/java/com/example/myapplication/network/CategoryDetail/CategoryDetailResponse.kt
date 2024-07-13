package com.example.myapplication.network.CategoryDetail
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryDetailResponse(
    val data:DataDetail,
    val additionalInfo:AdditionalInfoDetail
)