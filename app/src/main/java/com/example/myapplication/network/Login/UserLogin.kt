package com.example.myapplication.network.Login

import com.example.myapplication.database.entity.UserInfoEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserLogin(
    val avatar: String,
    val id: String,
    val username: String,
) {
    fun toUserInfoEntity() =
        UserInfoEntity(avatar = this.avatar, id = this.id, username = this.username)
}
