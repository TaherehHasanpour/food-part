package com.example.myapplication.ui.compScreen.PagesGrouping.saveImage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SaveViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
):ViewModel() {
    val img = savedStateHandle.get<String>("img") ?: ""
}