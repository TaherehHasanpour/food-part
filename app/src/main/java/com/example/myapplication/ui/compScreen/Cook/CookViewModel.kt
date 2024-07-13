package com.example.myapplication.ui.compScreen.Cook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.network.Cook.CookApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CookViewModel @Inject constructor(
    private val cookApi: CookApi
) : ViewModel() {

    private val _timeLimit = MutableStateFlow<String>("")
    val timeLimit: StateFlow<String> = _timeLimit.asStateFlow()

    private val _ingredients = MutableStateFlow<String>("")
    val ingredients: StateFlow<String> = _ingredients.asStateFlow()

    private val _selected = MutableStateFlow<RadioButtonOption?>(RadioButtonOption.OPTION1)
    val selected: StateFlow<RadioButtonOption?> = _selected.asStateFlow()

    fun updateTextValue1(value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _ingredients.value = value
        }
    }

    fun updateTextValue2(value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _timeLimit.value = value
        }
    }

    fun updateRadioButton(value: RadioButtonOption) {
        viewModelScope.launch(Dispatchers.IO) {
            _selected.emit(value)
        }
    }

    fun updateQueryTimeLimit(query: String) {
        _timeLimit.value = query
    }

    fun updateQueryIngredients(query: String) {
        _ingredients.value = query
    }

}


