package com.example.myapplication.ui.compScreen.Account

import android.content.SharedPreferences
import android.provider.Settings.Global.getString
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
  val   peref: SharedPreferences,

    ) : ViewModel() {
    fun checkLogin(): String? {
        return peref.getString("token", null)
    }


}
