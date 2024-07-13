package com.example.myapplication.ui.compScreen.pagesAccountScreen.singin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.network.SingIn.ResponseInput
import com.example.myapplication.network.SingIn.ResponseOutput
import com.example.myapplication.network.SingIn.SingInApi
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
class SingInViewModel @Inject constructor(
    private val singInApi: SingInApi
) : ViewModel() {

    private val _userName = MutableStateFlow<String>("")
    val userName: StateFlow<String> = _userName.asStateFlow()

    private val _password = MutableStateFlow<String>("")
    val password: StateFlow<String> = _password.asStateFlow()

    fun setUserName(text: String) {
        _userName.value = text
    }

    fun setPassword(text: String) {
        _password.value = text
    }

    private val _singInResponse = MutableStateFlow<ResponseOutput?>(null)
    val singInResponse: StateFlow<ResponseOutput?> = _singInResponse.asStateFlow()

    private val _singInResult = MutableStateFlow<Result>(Result.Idle)
    val singInResult: SharedFlow<Result> = _singInResult.asSharedFlow()
    fun createUser() {
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = {
                    singInApi.createUser(
                        ResponseInput(userName.value,password.value)
                    )
                },
                {
                    _singInResponse.value = it
                }
            ).collect(_singInResult)
        }
    }


}