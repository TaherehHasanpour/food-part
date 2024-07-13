package com.example.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.database.entity.UserInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoProfile {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user:UserInfoEntity)
//
//    @Query("SELECT * FROM UserInfoEntity")
//    fun eee(username:String):Flow<UserInfoEntity>
}