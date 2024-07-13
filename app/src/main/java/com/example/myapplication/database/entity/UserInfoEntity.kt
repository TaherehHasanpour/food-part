package com.example.myapplication.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class UserInfoEntity(
    @PrimaryKey val avatar: String,
    val id: String,
    val username: String
)
