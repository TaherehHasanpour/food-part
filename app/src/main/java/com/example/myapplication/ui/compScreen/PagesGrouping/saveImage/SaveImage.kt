package com.example.myapplication.ui.compScreen.PagesGrouping.saveImage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.ui.Navigation.Screen

@Composable
fun saveImage(navController: NavController,viewModel: SaveViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                content = {
                    IconButton(onClick = {
                        navController.navigate(Screen.Grouping.route) {
                            popUpTo(Screen.Grouping.route)

                        }

                    }) {
                        Icon(
                            Icons.Default.KeyboardArrowRight,
                            contentDescription = " ",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                    Text(
                        text = stringResource(R.string.image),
                        style = MaterialTheme.typography.h2,
                        modifier = Modifier,
                        color = MaterialTheme.colors.onBackground,
                        textAlign = TextAlign.Justify

                    )
                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(onClick = {
                        navController.navigate("Detail") {
                            popUpTo("Detail")
                        }

                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.save),
                            contentDescription = " ",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                }
            )
        }
    ) {

        Column(
            modifier = Modifier
                .padding(it)
        )
        {
            Column (modifier = Modifier.background(color = MaterialTheme.colors.background)){
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .fillMaxWidth()

                ) {
                    AsyncImage(
                        alignment = Alignment.Center,
                        contentScale = ContentScale.FillBounds,
                        model = viewModel.img,
                        contentDescription = "author's image",
                        error = painterResource(id = R.drawable.f2),
                        modifier = Modifier.padding(top=64.dp, bottom = 150.dp)
                            .fillMaxSize()
                    )
                }
            }
        }
    }


}