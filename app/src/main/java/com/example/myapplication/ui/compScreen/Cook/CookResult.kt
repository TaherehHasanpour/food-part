package com.example.myapplication.ui.compScreen.Cook

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.network.Search.FoodItem
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CookResult(
    navController: NavHostController,
    viewModel: CookResultViewModel,
    text1: String,
    text2: String,
    buttonOption: String
) {
    val list by viewModel.cookResponse.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val state = rememberLazyGridState()
    val scope = rememberCoroutineScope()
    val isShow by remember { derivedStateOf { state.firstVisibleItemIndex != 0 } }
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                content = {
                    IconButton(onClick = {
                        navController.navigate("Grouping") {
                            popUpTo("Grouping")
                        }
                    }
                    )
                    {
                        Icon(
                            Icons.Default.KeyboardArrowRight,
                            contentDescription = " ",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                    Text(
                        text = stringResource(R.string.cook),
                        style = MaterialTheme.typography.h2,
                        modifier = Modifier,
                        color = MaterialTheme.colors.onBackground,
                        textAlign = TextAlign.Justify
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            )
        },
        floatingActionButton = {
            if (isShow) {
                FloatingActionButton(onClick = {
                    scope.launch { state.animateScrollToItem(0) }
                }, backgroundColor = MaterialTheme.colors.primary) {
                    Icon(
                        Icons.Filled.KeyboardArrowUp,
                        tint = Color.White,
                        contentDescription = " "
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        scaffoldState = scaffoldState
    )
    {
        Column {
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(R.string.search_result_whith, text1),
                    style = MaterialTheme.typography.subtitle2
                )
                Row {
                    Text(
                        text = stringResource(R.string.in_minute, text2),
                        style = MaterialTheme.typography.subtitle2
                    )
                    Text(
                        text = stringResource(R.string.difficulty, buttonOption),
                        style = MaterialTheme.typography.subtitle2
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.Top
            ) {
                LazyVerticalGrid(
                    contentPadding = PaddingValues(start = 40.dp, end = 40.dp, top = 0.dp),
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    columns = GridCells.Fixed(2),
                    content = {
                        items(list) { index ->
                            Log.d("tagging", "$list")
                            Column(
                                modifier = Modifier.clickable {
                                    navController.navigate("Detail/${list.first().id}")
                                }){
                                FoodItem(
                                    foodName = index.name,
                                    foodImage = index.image,
                                    cookTime = index.cookTime
                                )
                            }

                        }
                    }
                )
            }
        }
    }
}



