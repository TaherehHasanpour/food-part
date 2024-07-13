package com.example.myapplication.ui.compScreen.Grouping

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.ui.theme.ProgrammingfoodPartTheme
import ir.partsoftware.programmingquote.ui.common.Result

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GroupingScreen(
    viewModel: GroupingViewModel,
    onclick: (id: String) -> Unit
) {
    var selectedfood by remember { mutableIntStateOf(0) }
    var selectedItemId by remember { mutableIntStateOf(0) }
    var statDiv by remember { mutableStateOf(false) }
    val categoryResponse by viewModel.categoryResponse.collectAsState()
    val subCategoryViewResponse by viewModel.subcategoryResponse.collectAsState()
    val pageResultCategory by viewModel.categoryResult.collectAsState(initial = Result.Idle)
    val subPageResultCategory by viewModel.subCategoryResult.collectAsState(initial = Result.Idle)


    ProgrammingfoodPartTheme {
        Scaffold(
            backgroundColor = MaterialTheme.colors.background,
            modifier = Modifier.background(MaterialTheme.colors.background),
            topBar = {
                TopAppBar(
                    backgroundColor = MaterialTheme.colors.background,
                    content = {
                        Text(
                            text = stringResource(R.string.foodpart),
                            style = MaterialTheme.typography.h1,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 17.dp),
                            color = MaterialTheme.colors.onBackground,
                            textAlign = TextAlign.Justify
                        )
                    }
                )
            },
        ) {
            var nnn = ""
            Column {
                if (pageResultCategory is Result.Loading) LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth()
                )
                if (pageResultCategory is Result.Error) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "خطا در برقراری ارتباط با سرور",
                            style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.onBackground,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                viewModel.fetchCategory()
                            },
                            Modifier
                                .width(128.dp)
                                .height(48.dp)
                                .background(
                                    color = Color(0xFFFF6262),
                                    shape = MaterialTheme.shapes.medium
                                )
                        ) {
                            Text(
                                text = "تلاش مجدد",
                                style = MaterialTheme.typography.body1,
                                color = MaterialTheme.colors.onBackground,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
                if (pageResultCategory is Result.Success) {
                    Column {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier
                                .padding(top = 16.dp, start = 16.dp)
                                .height(96.dp)
                        ) {
                            itemsIndexed(categoryResponse?.data ?: emptyList()) { index, it ->
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier =
                                    Modifier
                                        .height(96.dp)
                                        .clickable {
                                            nnn = it.id
                                            viewModel.getCategoryById(it.id)
                                            selectedfood = index
                                        }
                                ) {
                                    AsyncImage(
                                        alignment = Alignment.Center,
                                        contentScale = ContentScale.Crop,
                                        model = it.image,
                                        contentDescription = "author's image",
                                        error = painterResource(R.drawable.imagedifalt),
                                        modifier = Modifier
                                            .clip(MaterialTheme.shapes.medium)
                                            .width(64.dp)
                                            .height(64.dp)
                                            .border(
                                                width = 2.dp,
                                                color = if (selectedfood == index) MaterialTheme.colors.primary else MaterialTheme.colors.background,
                                                shape = MaterialTheme.shapes.medium
                                            )
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = it.name,
                                        style = MaterialTheme.typography.body1,
                                        modifier = Modifier
                                            .height(64.dp)
                                            .fillMaxWidth(),
                                        color = if (selectedfood == index) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                        if (pageResultCategory is Result.Success) Divider(
                            Modifier
                                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                                .background(MaterialTheme.colors.surface)
                                .height(2.dp)
                        )
                        val subCategory = categoryResponse?.data?.get(selectedfood)
                        if (categoryResponse?.data?.get(3) != null) {
                            statDiv = true
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 8.dp)

                            ) {
                                itemsIndexed(
                                    subCategory?.subCategories ?: emptyList()
                                ) { index, it ->
                                    Column(
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(MaterialTheme.shapes.medium)
                                            .border(
                                                width = 2.dp,
                                                color = if (selectedItemId == index) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
                                                shape = MaterialTheme.shapes.medium
                                            )
                                            .background(
                                                color = if (selectedItemId == index) Color(
                                                    0x1AFF6262
                                                ) else MaterialTheme.colors.surface,
                                                shape = MaterialTheme.shapes.medium
                                            )
                                            .clickable {
                                                viewModel.getCategoryById(it.id)
                                                selectedItemId = index
                                            }
                                    ) {
                                        Text(
                                            text = it.name,
                                            style = MaterialTheme.typography.subtitle2,
                                            modifier = Modifier
                                                .padding(vertical = 8.dp, horizontal = 15.dp),
                                            textAlign = TextAlign.Center,
                                            color = if (selectedItemId == index) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground
                                        )
                                    }
                                }
                            }
                        }
                        if (subCategory?.subCategories != null) Divider(
                            Modifier
                                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                                .background(MaterialTheme.colors.surface)
                                .height(2.dp)
                        )
                        if (subPageResultCategory is Result.Loading) LinearProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp, top = 5.dp)
                        )
                        if (subPageResultCategory is Result.Error) {
                            Column(
                                modifier = Modifier
                                    .padding(
                                        start = 40.dp,
                                        end = 40.dp,
                                        top = 16.dp
                                    )
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(
                                    text = "غذایی برای نمایش پیدا نشد",
                                    style = MaterialTheme.typography.caption,
                                    color = MaterialTheme.colors.onBackground,
                                    textAlign = TextAlign.Center,
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(
                                    onClick = {
                                        viewModel.getCategoryById(nnn)
                                    },
                                    Modifier
                                        .width(128.dp)
                                        .height(48.dp)
                                        .background(
                                            color = Color(0xFFFF6262),
                                            shape = MaterialTheme.shapes.medium
                                        )
                                ) {
                                    Text(
                                        text = "تلاش مجدد",
                                        style = MaterialTheme.typography.body1,
                                        color = MaterialTheme.colors.onBackground,
                                        textAlign = TextAlign.Center,
                                    )
                                }
                            }
                        }

                        if (subPageResultCategory is Result.Success) {
                            LazyVerticalGrid(
                                contentPadding = PaddingValues(
                                    start = 40.dp,
                                    end = 40.dp,
                                    top = 16.dp
                                ),
                                verticalArrangement = Arrangement.spacedBy(24.dp),
                                horizontalArrangement = Arrangement.spacedBy(24.dp),
                                columns = GridCells.Fixed(2)
                            ) {
                                items(subCategoryViewResponse?.data ?: emptyList()) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                onclick(it.id)
                                            }
                                    ) {
                                        AsyncImage(
                                            alignment = Alignment.Center,
                                            contentScale = ContentScale.Crop,
                                            model = it.image,
                                            contentDescription = "author's image",
                                            error = painterResource(id = R.drawable.f2),
                                            modifier = Modifier
                                                .clip(MaterialTheme.shapes.medium)
                                                .fillMaxWidth()
                                                .height(84.dp)
                                        )
                                        Column(modifier = Modifier.padding(start = 8.dp)) {
                                            Text(
                                                text = it.name,
                                                style = MaterialTheme.typography.body1,
                                                color = MaterialTheme.colors.onBackground
                                            )
                                            val time = it.readyTime ?: (0 + (it.cookTime ?: 0))
                                            if (time != 0) {
                                                Text(
                                                    modifier = Modifier.padding(top = 3.dp),
                                                    text = "زمان: $time دقیقه",
                                                    style = MaterialTheme.typography.subtitle2,
                                                    color = MaterialTheme.colors.onSurface
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

