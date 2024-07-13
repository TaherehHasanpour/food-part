package com.example.myapplication.di

import com.example.myapplication.network.Cook.CookApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object CookModule {
    @Provides
    fun provideCookApi(retrofit: Retrofit): CookApi {
        return retrofit.create(CookApi::class.java)
    }
}