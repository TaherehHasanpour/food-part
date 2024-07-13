package com.example.myapplication.ui.compScreen.Grouping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.network.category.CategoryApi
import com.example.myapplication.network.category.CategoryResponse
import com.example.myapplication.network.subcategory.SubCategorApi
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
class GroupingViewModel @Inject constructor(
    private val categoryApi: CategoryApi,
    private val subCategoryApi: SubCategorApi
) : ViewModel() {

    private val _categoryResult = MutableStateFlow<Result>(Result.Idle)
    val categoryResult: SharedFlow<Result> = _categoryResult.asSharedFlow()

    private val _subcategoryResult = MutableStateFlow<Result>(Result.Idle)
    val subCategoryResult: SharedFlow<Result> = _subcategoryResult.asSharedFlow()

    private val _categoryResponse = MutableStateFlow<CategoryResponse?>(null)
    val categoryResponse: StateFlow<CategoryResponse?> = _categoryResponse.asStateFlow()

    private val _subcategoryResponse = MutableStateFlow<SubCategoryview?>(null)
    val subcategoryResponse: StateFlow<SubCategoryview?> = _subcategoryResponse.asStateFlow()

    init {
        fetchCategory()

    }

    fun fetchCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = { categoryApi.getCategory() },
                onDataReady = {
                    _categoryResponse.value = it
                    if (it.data.first().subCategories?.first()?.id == null) {
                        getCategoryById(it.data.first().id)
                    } else {
                        getCategoryById(it.data.first().subCategories?.first()?.id ?: "")
                    }


                }
            ).collect(_categoryResult)
        }
    }


    fun getCategoryById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = { subCategoryApi.getCategoryById(id) },
                onDataReady = {
                    _subcategoryResponse.value = it
                }
            ).collect(_subcategoryResult)
        }
    }
}