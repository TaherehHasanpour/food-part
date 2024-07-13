//package com.example.myapplication.ui.Common
//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.lazy.grid.rememberLazyGridState
//import androidx.compose.material.Icon
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Scaffold
//import androidx.compose.material.Text
//import androidx.compose.material.TopAppBar
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.KeyboardArrowRight
//import androidx.compose.material3.IconButton
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavHostController
//import coil.compose.AsyncImage
//import com.example.myapplication.R
//import com.example.myapplication.ui.Navigation.Screen
//import kotlinx.coroutines.flow.StateFlow
//
//@Composable
//fun More(navController: NavHostController,,Respons: StateFlow<Any?>) {
//val Respons=
//    val state = rememberLazyGridState()
//    Scaffold(
//        backgroundColor = MaterialTheme.colors.background,
//        topBar = {
//            TopAppBar(
//                backgroundColor = MaterialTheme.colors.background,
//                content = {
//                    IconButton(onClick = {
//                        navController.navigate(Screen.Grouping.route) {
//                            popUpTo(Screen.Grouping.route)
//
//                        }
//
//                    }) {
//                        Icon(
//                            Icons.Default.KeyboardArrowRight,
//                            contentDescription = " ",
//                            tint = MaterialTheme.colors.onBackground
//                        )
//                    }
//                    Text(
//                        text = stringResource(R.string.More),
//                        style = MaterialTheme.typography.h2,
//                        modifier = Modifier,
//                        color = MaterialTheme.colors.onBackground,
//                        textAlign = TextAlign.Justify
//
//                    )
//                    Spacer(modifier = Modifier.weight(1f))
//
//                    IconButton(onClick = {
//                        navController.navigate("Detail") {
//                            popUpTo("Detail")
//                        }
//
//                    }) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.save),
//                            contentDescription = " ",
//                            tint = MaterialTheme.colors.onBackground
//                        )
//                    }
//                }
//            )
//        },
//    ) {
//        Column(modifier = Modifier.padding(it)) {
//            LazyVerticalGrid(
//                contentPadding = PaddingValues(
//                    start = 40.dp,
//                    end = 40.dp,
//                    top = 16.dp
//                ),
//                verticalArrangement = Arrangement.spacedBy(24.dp),
//                horizontalArrangement = Arrangement.spacedBy(24.dp),
//                columns = GridCells.Fixed(2)
//            ) {
//                items(subCategoryViewResponse?.data ?: emptyList()) {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .clickable {
//                                onclick(it.id)
//                            }
//                    ) {
//                        AsyncImage(
//                            alignment = Alignment.Center,
//                            contentScale = ContentScale.Crop,
//                            model = it.image,
//                            contentDescription = "author's image",
//                            error = painterResource(id = R.drawable.f2),
//                            modifier = Modifier
//                                .clip(MaterialTheme.shapes.medium)
//                                .fillMaxWidth()
//                                .height(84.dp)
//                        )
//                        Column(modifier = Modifier.padding(start = 8.dp)) {
//                            Text(
//                                text = it.name,
//                                style = MaterialTheme.typography.body1,
//                                color = MaterialTheme.colors.onBackground
//                            )
//                            val time = it.readyTime ?: (0 + (it.cookTime ?: 0))
//                            if (time != 0) {
//                                Text(
//                                    modifier = Modifier.padding(top = 3.dp),
//                                    text = "زمان: $time دقیقه",
//                                    style = MaterialTheme.typography.subtitle2,
//                                    color = MaterialTheme.colors.onSurface
//                                )
//                            }
//                        }
//                    }
//                }
//            }
//
//        }
//    }
//}