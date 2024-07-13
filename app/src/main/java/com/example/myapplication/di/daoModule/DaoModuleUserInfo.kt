package com.example.myapplication.di.daoModule

import com.example.myapplication.database.DataBaseFoodPart
import com.example.myapplication.database.dao.DaoProfile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DaoModuleUserInfo {
    @Provides
    fun provideDaoUserInfo(dataBaseFoodPart: DataBaseFoodPart):DaoProfile{
        return dataBaseFoodPart.DaoProfile()
    }
}