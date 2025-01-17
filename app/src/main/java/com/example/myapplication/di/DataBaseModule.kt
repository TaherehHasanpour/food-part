package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.database.DataBaseFoodPart
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    @Singleton
    fun providesDataBase(@ApplicationContext context: Context): DataBaseFoodPart {
        return Room.databaseBuilder(context = context, DataBaseFoodPart::class.java, "App_db")
            .build()
    }
}