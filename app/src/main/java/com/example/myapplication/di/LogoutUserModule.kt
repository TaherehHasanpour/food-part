package com.example.myapplication.di

import com.example.myapplication.network.Logout.ApiLogout
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object LogoutUserModule {
    @Provides
    fun provideApiApiLogout(retrofit: Retrofit): ApiLogout {
        return retrofit.create(ApiLogout::class.java)
    }


}
