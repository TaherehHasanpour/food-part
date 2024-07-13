package com.example.myapplication.di

import com.example.myapplication.network.SingIn.SingInApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object SingInModule {

    @Provides
    fun singInModule(retrofit: Retrofit): SingInApi {
        return retrofit.create(SingInApi::class.java)
    }
}
