package com.example.myapplication.ui.compScreen.pagesAccountScreen.profile

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.network.Logout.ApiLogout
import com.example.myapplication.network.editUser.PasswordAndUserName.ApiPasAndUName
import com.example.myapplication.network.editUser.PasswordAndUserName.BodyResponse
import com.example.myapplication.network.editUser.password.ApiPassword
import com.example.myapplication.network.editUser.password.BodyPassword
import com.example.myapplication.network.editUser.password.ResponsePassword
import com.example.myapplication.network.editUser.userName.ApiEditUserName
import com.example.myapplication.network.editUser.userName.BodyUserName
import com.example.myapplication.network.editUser.userName.UserNameEditResponse
import com.example.myapplication.network.profileImgeUser.ApiProfileImage
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
class ProfileViewModule @Inject constructor(
    savedStateHandle: SavedStateHandle,
  val peref: SharedPreferences,
    private val apiProfileImage: ApiProfileImage,
    private val apiEditUserName: ApiEditUserName,
    private val apiPassword: ApiPassword,
    private val apiPasAndUName: ApiPasAndUName,
    private val apiLogout: ApiLogout,
) : ViewModel() {
    val userName = savedStateHandle.get<String>("userName") ?: "0"
    private val userId = savedStateHandle.get<String>("userId") ?: "0"
    private val filename = savedStateHandle.get<String>("fileName") ?: "0"

    private val _fileProfile = MutableStateFlow<String?>(null)
    val fileProfile: StateFlow<String?> = _fileProfile.asStateFlow()

    private val _inputUserName = MutableStateFlow("")
    val inputUserName: StateFlow<String> = _inputUserName.asStateFlow()
    fun setUserName(text: String) {
        _inputUserName.value = text
    }

    private val _passwordCurrent = MutableStateFlow<String>("")
    val passwordCurrent: StateFlow<String> = _passwordCurrent.asStateFlow()
    fun setPasswordCurrent(passwordCurrent: String) {
        _passwordCurrent.value = passwordCurrent
    }

    private val _passwordNew = MutableStateFlow<String>("")
    val passwordNew: StateFlow<String> = _passwordNew.asStateFlow()
    fun setNewPassword(passwordNew: String) {
        _passwordNew.value = passwordNew
    }

    private val _passwordRepeat = MutableStateFlow<String>("")
    val passwordRepeat: StateFlow<String> = _passwordRepeat.asStateFlow()
    fun setPasswordRepeat(passwordRepeat: String) {
        _passwordRepeat.value = passwordRepeat
    }

    private val _userNameResponse = MutableStateFlow<UserNameEditResponse?>(null)
    val userNameResponse: StateFlow<UserNameEditResponse?> = _userNameResponse.asStateFlow()
    private val _userNameResult = MutableStateFlow<Result>(Result.Idle)
    val userNameResult: SharedFlow<Result> = _userNameResult.asSharedFlow()

    private val _passwordResponse = MutableStateFlow<ResponsePassword?>(null)
    val passwordResponse: StateFlow<ResponsePassword?> = _passwordResponse.asStateFlow()
    private val _passwordResult = MutableStateFlow<Result>(Result.Idle)
    val passwordResult: SharedFlow<Result> = _passwordResult.asSharedFlow()


    private val _editPasAndUNameResult = MutableStateFlow<Result>(Result.Idle)
    val editPasAndUNameResult: SharedFlow<Result> = _editPasAndUNameResult.asSharedFlow()
    private val _editPasAndUNameResponse = MutableStateFlow<ResponsePassword?>(null)
    val editPasAndUNameResponse: StateFlow<ResponsePassword?> = _editPasAndUNameResponse.asStateFlow()

    private val _userLogoutResult = MutableStateFlow<Result>(Result.Idle)
    val userLogoutResult: SharedFlow<Result> = _userLogoutResult.asSharedFlow()

    val token = peref.getString("token", "") ?: ""

    fun editUserName() {
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = {
                    apiEditUserName.editUserName(token, BodyUserName(inputUserName.value))
                },
                {
                    _userNameResponse.value = it

                    Log.d("tag", " filename$it")
                }
            ).collect {
                _userNameResult.value = it
            }
        }
    }

    fun editPassword() {
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = {
                    apiPassword.editPassword(
                        token,
                        BodyPassword(passwordCurrent.value, passwordNew.value)
                    )
                },
                {
                    _passwordResponse.value = it
                    peref.edit {
                        putString("token", it.additionalInfo.token)
                    }
                }
            ).collect {
                _passwordResult.value = it

            }
        }
    }

    fun editPasAndUName() {
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = {
                    apiPasAndUName.editePasAndUName(
                        token,
                        BodyResponse(inputUserName.value, passwordCurrent.value, passwordNew.value)
                    )
                },
                {
                    _editPasAndUNameResponse.value=it
                    peref.edit {
                        putString("token", it.additionalInfo.token)
                    }
                }
            ).collect {
                _editPasAndUNameResult.value = it

            }
        }
    }
    fun userLogout() {
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = {
                   apiLogout.userLogout("Bearer $token")
                },
                {
                        peref.edit().remove("token").commit()
                    Log.d("tag","  peref $it")

                }
            ).collect {
                _userLogoutResult.value = it

            }
        }
    }
    fun profileUser() {
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = {
                    apiProfileImage.profileImage(userId = userId, filename = filename)
                },
                {
                    _fileProfile.value = it

                    Log.d("tag", " filename$it")
                }
            ).collect {

            }
        }
    }


}