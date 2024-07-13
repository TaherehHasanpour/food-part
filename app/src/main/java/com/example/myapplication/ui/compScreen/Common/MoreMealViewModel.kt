package com.example.myapplication.ui.compScreen.Common

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.network.FoodByMeal.ApiMeal
import com.example.myapplication.network.category.CategoryResponse
import com.example.myapplication.network.foodsByIds.Data
import com.example.myapplication.network.subcategory.SubCategoryview
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
class MoreMealViewModel @Inject constructor(
    private val apiMeal: ApiMeal,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val idMeal = savedStateHandle.get<String>("idMeal")?:""

    private val _getMealResponse = MutableStateFlow<SubCategoryview?>(null)
    val mealResponse: StateFlow<SubCategoryview?> = _getMealResponse.asStateFlow()

    private val _getMealResult = MutableStateFlow<Result>(Result.Idle)
    val getMealResult: SharedFlow<Result> = _getMealResult.asSharedFlow()

    init {
        fetchMeal()

    }

    private fun fetchMeal() {
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = { apiMeal.getMealById(meal=idMeal) },
                onDataReady = {
                    _getMealResponse.value = it
                }
            ).collect(_getMealResult)
        }
    }
}