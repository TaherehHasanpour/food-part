package com.example.myapplication.ui.compScreen.PagesGrouping.Detail

import android.content.SharedPreferences
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.network.CategoryDetail.CategoryDetailResponse
import com.example.myapplication.network.CategoryDetail.DetailApi
import com.example.myapplication.network.ReportFood.ApiReporFood
import com.example.myapplication.network.ReportFood.BodyReport
import com.example.myapplication.network.foodsByIds.ApiGetFoodIds
import com.example.myapplication.network.foodsByIds.Data
import com.example.myapplication.network.foodsByIds.Input
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
class DetailViewModul @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val detailApi: DetailApi,
    private val apiGetFoodIds: ApiGetFoodIds,
    private val dpiReporFood: ApiReporFood,
    private val preferences: SharedPreferences
) : ViewModel() {
    val id = savedStateHandle.get<String>("id") ?: "0"
    private val _detailResult = MutableStateFlow<Result>(Result.Idle)
    val detailResult: SharedFlow<Result> = _detailResult.asSharedFlow()

    private val _DetailResponse = MutableStateFlow<CategoryDetailResponse?>(null)
    val DetailResponse: StateFlow<CategoryDetailResponse?> = _DetailResponse.asStateFlow()

    private val _getDetailByIdResponse = MutableStateFlow<Data?>(null)
    val getDetailByIdResponse: StateFlow<Data?> = _getDetailByIdResponse.asStateFlow()

    private val _getDetailByIdResult = MutableStateFlow<Result>(Result.Idle)
    val getDetailByIdResult: SharedFlow<Result> = _getDetailByIdResult.asSharedFlow()

    private val _reportResult = MutableStateFlow<Result>(Result.Idle)
    val reportResult: SharedFlow<Result> = _reportResult.asSharedFlow()

    init {
        getDetailById()

    }

    private fun getDetailById() {
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = { detailApi.getDetailById(id) },
                onDataReady = {
                    _DetailResponse.value = it
                    val similarFood = it.additionalInfo.similarFoods ?: emptyList()
                    getDetailById(similarFood)

                }
            ).collect(_detailResult)
        }
    }

    private fun getDetailById(ids: List<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = {
                    apiGetFoodIds.getDetailById(
                        Input(
                            ids
                        )
                    )
                },
                onDataReady = {
                    _getDetailByIdResponse.value = it

                }
            ).collect(_detailResult)
        }
    }
    private val _massage = MutableStateFlow("")
    val massage: StateFlow<String> = _massage.asStateFlow()

    fun setMassage(text: String) {
        _massage.value = text
    }
    fun checkLogin(): String? {
        return preferences.getString("token", null)
    }
    fun reportFoodDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = {
                    dpiReporFood.ReporFood(
                        authorization=checkLogin()?:"",id=DetailResponse.value?.data?.id?:"", BodyReport(massage.value)
                    )
                },
                onDataReady = {

                }
            ).collect(_reportResult)
        }
    }

}