package com.example.myapplication.di

import com.example.myapplication.network.Login.LoginApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Provides
    fun loginModel(retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }
}
