package com.example.myapplication.di

import com.example.myapplication.network.subcategory.SubCategorApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

    @Module
    @InstallIn(SingletonComponent::class)
    object SubCategorModule {

        @Provides
        fun SubCategorApi(retrofit: Retrofit): SubCategorApi {
            return retrofit.create(SubCategorApi::class.java)
        }
    }
