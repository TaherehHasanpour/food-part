package com.example.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.database.dao.DaoProfile
import com.example.myapplication.database.entity.UserInfoEntity

@Database(entities =[UserInfoEntity::class] , version = 1)
abstract class DataBaseFoodPart:RoomDatabase() {
    abstract fun DaoProfile(): DaoProfile
}