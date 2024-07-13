package com.example.myapplication.di

import com.example.myapplication.network.foodsByIds.ApiGetFoodIds
import com.example.myapplication.network.profileImgeUser.ApiProfileImage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ProfileImage {

    @Provides
    fun ApiProfileImage(retrofit: Retrofit): ApiProfileImage {
        return retrofit.create(ApiProfileImage::class.java)
    }


}