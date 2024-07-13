package com.example.myapplication.di

import com.example.myapplication.network.editUser.PasswordAndUserName.ApiPasAndUName
import com.example.myapplication.network.editUser.userName.ApiEditUserName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object UserNameAndPassEditUser {
    @Provides
    fun provideApiApiPasAndUName(retrofit: Retrofit): ApiPasAndUName {
        return retrofit.create(ApiPasAndUName::class.java)
    }


}
