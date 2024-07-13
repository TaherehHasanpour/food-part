package com.example.myapplication.di

import com.example.myapplication.network.foodsByIds.ApiGetFoodIds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object GetFoodIdsModule {

    @Provides
    fun provideCategoryApi(retrofit: Retrofit): ApiGetFoodIds {
        return retrofit.create(ApiGetFoodIds::class.java)
    }

}