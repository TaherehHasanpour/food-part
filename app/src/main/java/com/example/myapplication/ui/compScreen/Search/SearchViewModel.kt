package com.example.myapplication.ui.compScreen.Search


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.network.Search.FoodData
import com.example.myapplication.network.Search.SearchApi
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
class SearchViewModel @Inject constructor(
    private val searchApi: SearchApi
) : ViewModel() {

    private val _searchResult = MutableStateFlow<Result>(Result.Idle)
    val searchResult: SharedFlow<Result> = _searchResult.asSharedFlow()

    private val _searchResponse = MutableStateFlow<List<FoodData>>(emptyList())
    val searchResponse: StateFlow<List<FoodData>> = _searchResponse.asStateFlow()

    private val _query = MutableStateFlow<String>("")
    val query: StateFlow<String> = _query.asStateFlow()

    private var searchJob: Job? = null



    fun search() {
        searchJob?.cancel()

        searchJob = viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = { searchApi.search(query.value) },
                onDataReady = {
                    _searchResponse.value = it.searchData
                }
            ).collect(_searchResult)
            searchJob = null
        }
    }

    fun updateValue(value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _query.value = value
        }
    }

    fun updateQuery(query: String) {
        _query.value = query
    }
}