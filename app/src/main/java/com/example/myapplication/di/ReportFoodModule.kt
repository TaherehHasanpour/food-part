package com.example.myapplication.di

import com.example.myapplication.network.ReportFood.ApiReporFood
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ReportFoodModule {

    @Provides
    fun provideApiReporFood(retrofit: Retrofit):ApiReporFood  {
        return retrofit.create(ApiReporFood::class.java)
    }


}