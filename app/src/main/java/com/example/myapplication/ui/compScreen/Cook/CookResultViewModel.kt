package com.example.myapplication.ui.compScreen.Cook

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.network.Cook.CookApi
import com.example.myapplication.network.Search.FoodData
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.partsoftware.programmingquote.network.common.safeApi
import ir.partsoftware.programmingquote.ui.common.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CookResultViewModel @Inject constructor(
    private val cookApi: CookApi,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _searchResult = MutableStateFlow<Result>(Result.Idle)
    val searchResult: SharedFlow<Result> = _searchResult.asSharedFlow()

    private val _cookResponse = MutableStateFlow<List<FoodData>>(emptyList())
    val cookResponse: StateFlow<List<FoodData>> = _cookResponse.asStateFlow()

    private val ingredient: String = savedStateHandle.get<String>("text1").orEmpty()
    private val time: Int = savedStateHandle.get<String>("text2")?.toInt() ?: 0
    private val difficulty: String? = savedStateHandle.get<String>("buttonOption")

    init {
        cookCall(ingredient, time, difficulty)
    }

    private var searchJob: Job? = null
    fun cookCall(
        ingredient: String,
        time: Int,
        difficulty: String?
    ) {
        searchJob?.cancel()
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = {
                    cookApi.cook(query1 = time, query = ingredient, query3 = difficulty)
                       },
                onDataReady = {
                    viewModelScope.launch(Dispatchers.IO) {
                        _cookResponse.emit(it.searchData)
                    }
                }
            ).collect(_searchResult)
            searchJob = null
        }
    }
}