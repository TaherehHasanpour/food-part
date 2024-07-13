package com.example.myapplication.di

import com.example.myapplication.network.editUser.password.ApiPassword
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object PasswordEditModule {
    @Provides
    fun provideApiEditPassword(retrofit: Retrofit): ApiPassword {
        return retrofit.create(ApiPassword::class.java)
    }


}
