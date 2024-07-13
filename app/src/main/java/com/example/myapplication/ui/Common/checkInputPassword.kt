package com.example.myapplication.ui.Common

import androidx.compose.runtime.MutableState

fun tokenUser(token:String=""){

}

fun checkInputPassword(
    text: String,
    hasUpperCase: MutableState<Boolean>,
    hasLowerCase: MutableState<Boolean>
) {
    hasUpperCase.value = false
    hasLowerCase.value = false

    for (char in text) {
        if (char.isUpperCase()) {
            hasUpperCase.value = true
        } else if (char.isLowerCase()) {
            hasLowerCase.value = true
        }
    }
}
