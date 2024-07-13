package com.example.myapplication.di

import com.example.myapplication.network.editUser.userName.ApiEditUserName
import com.example.myapplication.network.foodsByIds.ApiGetFoodIds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


    @Module
    @InstallIn(SingletonComponent::class)
    object UserNameEditModule {
        @Provides
        fun provideApiEditUserName(retrofit: Retrofit): ApiEditUserName {
            return retrofit.create(ApiEditUserName::class.java)
        }


    }
