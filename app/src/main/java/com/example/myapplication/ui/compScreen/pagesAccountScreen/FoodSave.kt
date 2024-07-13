package com.example.myapplication.ui.compScreen.pagesAccountScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.network.Search.FoodItem
import com.example.myapplication.ui.theme.potato

@Composable
fun FoodSave(navController: NavHostController){
    val state = rememberLazyGridState()
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                modifier = Modifier.background(MaterialTheme.colors.background),
                title = {
                    Text(
                        text = stringResource(id =  R.string.savefood),
                        style = MaterialTheme.typography.h1,
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.onBackground,

                        )
                },

                )
        },
    ) {
        Column(modifier = Modifier.padding(it)) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                state = state,
                contentPadding = PaddingValues(
                    start = 15.dp,
                    top = 16.dp,
                    end = 15.dp,
                    bottom = 16.dp
                ),
                content = {
                    itemsIndexed(potato) { _, food ->
//                        FoodItem(
//                            food = food.copy(title = food.title),navController
//                        )
                    }
                }
            )


        }
    }
}