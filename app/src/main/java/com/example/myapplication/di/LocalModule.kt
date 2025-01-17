package com.example.myapplication.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    fun provideSharedPreferences(
        @ApplicationContext context : Context
    ):SharedPreferences{
        return context.getSharedPreferences("token",Context.MODE_PRIVATE)
    }
}