package com.example.myapplication.network.editUser.PasswordAndUserName

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BodyResponse(
  val  username:String,
  val  oldPassword:String,
  val  newpassword:String,

)
