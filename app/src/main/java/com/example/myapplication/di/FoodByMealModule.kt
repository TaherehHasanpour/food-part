package com.example.myapplication.di

import com.example.myapplication.network.FoodByMeal.ApiMeal
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object FoodByMealModule {

    @Provides
    fun provideApiMeal(retrofit: Retrofit): ApiMeal {
        return retrofit.create(ApiMeal::class.java)
    }
}

