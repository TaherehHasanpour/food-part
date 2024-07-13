package com.example.myapplication.ui.compScreen.pagesAccountScreen.logein

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.database.dao.DaoProfile
import com.example.myapplication.database.entity.UserInfoEntity
import com.example.myapplication.network.Login.LoginApi
import com.example.myapplication.network.Login.LoginResponse
import com.example.myapplication.network.SingIn.ResponseInput
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.partsoftware.programmingquote.network.common.safeApi
import ir.partsoftware.programmingquote.ui.common.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

    private val preferences: SharedPreferences,
    private val loginApi: LoginApi,
    private val daoProfile: DaoProfile
) : ViewModel() {

    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    fun setUserName(text: String) {
        _userName.value = text
    }

    fun setPassword(text: String) {
        _password.value = text
    }

    private val _loginResult = MutableStateFlow<Result>(Result.Idle)
    val loginResult: SharedFlow<Result> = _loginResult.asSharedFlow()

    private val _loginResponse = MutableStateFlow<LoginResponse?>(null)
    val loginResponse: StateFlow<LoginResponse?> = _loginResponse.asStateFlow()

    fun fetchLoginUser(userName: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(call = {
                loginApi.loginUser(
                    ResponseInput(userName, password)
                )
            }, onDataReady = {
                _loginResponse.value = it
                preferences.edit {
                    putString("token", it.data.token)
                }
                storeUser(it.data.user.toUserInfoEntity())

            }).collect(_loginResult)
        }
    }


    private fun storeUser(user: UserInfoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            daoProfile.insertUser(user)
        }
    }

}