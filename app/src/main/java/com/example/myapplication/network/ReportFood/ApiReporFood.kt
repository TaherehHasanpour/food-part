package com.example.myapplication.network.ReportFood

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiReporFood {
    @POST("v1/food/{id}/report")
    suspend fun ReporFood(
        @Header("Authorization") authorization: String,
        @Path("id")id :String,
        @Body body: BodyReport
    ): Response<ResponseReport>
}