package com.example.myapplication.ui.compScreen.Search

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.network.Search.FoodItem
import ir.partsoftware.programmingquote.ui.common.Result

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavHostController, viewModel: SearchViewModel) {
    val searchText by viewModel.query.collectAsState()

    val searchResult by viewModel.searchResult.collectAsState(Result.Idle)

    val scaffoldState = rememberScaffoldState()
    val list by viewModel.searchResponse.collectAsState()

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .background(MaterialTheme.colors.background),
                title = {
                    Text(
                        text = stringResource(R.string.what_look_for),
                        style = MaterialTheme.typography.h2,
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.onBackground,

                        )
                },

                )
        },
    ) {
        Column {
            OutlinedTextField(
                value = searchText,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(
                        color = MaterialTheme.colors.surface,
                        shape = MaterialTheme.shapes.medium,
                    )
                    .width(344.dp),
                shape = MaterialTheme.shapes.medium,
                onValueChange = { newtext -> viewModel.updateQuery(newtext) },
                placeholder = {
                    Text(
                        text = stringResource(R.string.write_here),
                        style = MaterialTheme.typography.body1
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = MaterialTheme.colors.background,
                    unfocusedBorderColor = MaterialTheme.colors.surface,
                    focusedBorderColor = Color.White,
                    errorBorderColor = Color.Red
                ),

                trailingIcon = {
                    if (searchText.isNotEmpty()) {
                        IconButton(onClick = { viewModel.updateValue(value = "") }) {
                            Icon(Icons.Default.Clear, contentDescription = "")
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        viewModel.search()
                    },
                )
            )
            if (searchResult is Result.Success) {
                if (searchText.isNotEmpty()) {
                    Column {

                        Text(
                            text = stringResource(R.string.searchresultwith, searchText),
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSurface,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                        Spacer(modifier = Modifier.padding(bottom = 16.dp))
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(24.dp),
                            horizontalArrangement = Arrangement.spacedBy(24.dp),
                            contentPadding = PaddingValues(horizontal = 40.dp, vertical = 24.dp)
                        ) {
                            items(list) { index ->
                                Column(
                                    modifier = Modifier.clickable {
                                        navController.navigate("Detail/${list.first().id}")
                                    }
                                ) {
                                    FoodItem(
                                        foodName = index.name,
                                        foodImage = index.image,
                                        cookTime = index.cookTime
                                    )
                                }

                            }
                        }


                    }
                } else if (searchText.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "اوپس...چیزی پیدا نشد!",
                            color = MaterialTheme.colors.onBackground,
                            style = MaterialTheme.typography.body1,
                        )
                    }
                }
            }
        }
    }
}



