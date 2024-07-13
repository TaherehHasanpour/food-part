package com.example.myapplication.ui.compScreen.PagesGrouping.Detail

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.ui.Navigation.Screen
import com.example.myapplication.ui.theme.ProgrammingfoodPartTheme
import ir.partsoftware.programmingquote.ui.common.Result
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.Locale

@OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterialApi::class,
)
@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: DetailViewModul,
    onImageClicked: (String) -> Unit,
    deitailClick: (id: String) -> Unit,
    moreMealClick: (id: String) -> Unit,
) {
    val data = viewModel.DetailResponse.collectAsState()
    val getDetailByIdResponse by viewModel.getDetailByIdResponse.collectAsState()
    val massage by viewModel.massage.collectAsState()
    val reportResult by viewModel.reportResult.collectAsState(Result.Idle)
    val mContext = LocalContext.current
    val scope = rememberCoroutineScope()
    var isShowMenu by remember {
        mutableStateOf(false)
    }
    val snackbarHostState = remember { SnackbarHostState() }
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, data.value?.data?.ingredients)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    val context = LocalContext.current

    val sheetstate =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val tabs: List<String>
    val statetabRow: PagerState
    if (data.value?.data?.point != null) {
        tabs = listOf("مواد اولیه", "طرز تهیه", "اطلاعات بیشتر")
        statetabRow = rememberPagerState { 3 }
    } else {
        tabs = listOf("مواد اولیه", "طرز تهیه")
        statetabRow = rememberPagerState { 2 }
    }

    ProgrammingfoodPartTheme {
        ModalBottomSheetLayout(
            sheetBackgroundColor = MaterialTheme.colors.secondary,
            sheetState = sheetstate,
            modifier = Modifier,
            scrimColor = Color.Transparent,
            sheetContent =
            {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                        .background(MaterialTheme.colors.secondary)

                ) {
                    Text(
                        text = stringResource(R.string.report), style = MaterialTheme.typography.h3,
                        color = MaterialTheme.colors.onBackground
                    )


                    OutlinedTextField(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth()
                            .height(88.dp)

                            .background(
                                color = MaterialTheme.colors.surface,
                                shape = MaterialTheme.shapes.medium
                            ),

                        value = massage,
                        placeholder = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    stringResource(R.string.istype),
                                    style = MaterialTheme.typography.body1
                                )

                            }
                        },
                        shape = MaterialTheme.shapes.medium,
                        onValueChange = {
                                        viewModel.setMassage(it)
                        },

                    )



                    Button(
                        onClick = {
                            viewModel.reportFoodDetail()
                            if(reportResult is Result.Success){
                                Toast.makeText(
                                    mContext,
                                    "گزارش با موفقیت ارسال شد",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        }, modifier = Modifier
                            .padding(top = 16.dp, bottom = 37.dp)
                            .height(48.dp)
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.medium)


                    ) {
                        Text(
                            text = stringResource(id = R.string.registration),
                            style = MaterialTheme.typography.button,
                            color = MaterialTheme.colors.onBackground
                        )

                    }
                }
            }
        ) {

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
                                    Icons.Default.KeyboardArrowRight, contentDescription = " ",
                                    tint = MaterialTheme.colors.onBackground
                                )
                            }
                            Text(
                                text = stringResource(R.string.informationFood),
                                color = MaterialTheme.colors.onBackground,
                                style = MaterialTheme.typography.h2
                            )
                            Spacer(modifier = Modifier.weight(1f))

                            IconButton(onClick = {
                                isShowMenu = !isShowMenu
                            }) {
                                Box(
                                    modifier = Modifier
                                        .padding(6.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.icon),
                                        contentDescription = " "
                                    )
                                    DropdownMenu(
                                        modifier = Modifier
                                            .background(
                                                MaterialTheme.colors.surface
                                            ),
                                        expanded = isShowMenu,
                                        onDismissRequest = { isShowMenu = false },
                                    ) {
                                   if(viewModel.checkLogin()!=null) {
                                       DropdownMenuItem(
                                           modifier = Modifier,
                                           onClick = {
                                               scope.launch {
                                                   isShowMenu = !isShowMenu
                                                   sheetstate.show()

                                               }
                                           }) {
                                           Icon(
                                               painter = painterResource(id = R.drawable.report),
                                               contentDescription = " "
                                           )
                                           Spacer(modifier = Modifier.width(5.dp))
                                           Text(text = stringResource(id = R.string.report2))
                                       }
                                   }


                                        DropdownMenuItem(onClick = {
                                            context.startActivity(shareIntent)
                                        }) {

                                            Icon(
                                                painter = painterResource(id = R.drawable.share),
                                                contentDescription = " "
                                            )
                                            Spacer(modifier = Modifier.width(5.dp))
                                            Text(text = stringResource(id = R.string.send))
                                        }

                                        DropdownMenuItem(onClick = {
                                            scope.launch {
                                                val result = snackbarHostState.showSnackbar(
                                                    message = "دستور به علاقه مندی ها اضافه شد",
                                                    actionLabel = "زخیره شده ها",
                                                    withDismissAction = true,
                                                    duration = SnackbarDuration.Short
                                                )
                                                when (result) {
                                                    SnackbarResult.ActionPerformed -> {
                                                        navController.navigate(Screen.FoodSave.route)
                                                    }

                                                    SnackbarResult.Dismissed -> {
                                                        //Do Something
                                                    }
                                                }
                                            }
                                        }) {

                                            Icon(
                                                painter = painterResource(id = R.drawable.bookmark),
                                                contentDescription = " "
                                            )
                                            Spacer(modifier = Modifier.width(5.dp))
                                            Text(text = stringResource(id = R.string.save))
                                        }
                                    }
                                }
                            }
                        },
                    )
                }
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                )
                {
                    AsyncImage(
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop,
                        model = data.value?.data?.image,
                        contentDescription = "author's image",
                        error = painterResource(id = R.drawable.bb),
                        modifier = Modifier
                            .padding(start = 12.dp, end = 12.dp)
                            .clip(shape = MaterialTheme.shapes.medium)
                            .fillMaxWidth()
                            .height(200.dp)
                            .clickable {
                                val urlImg = data.value?.data?.image ?: ""
                                val encodedUrl =
                                    URLEncoder.encode(urlImg, StandardCharsets.UTF_8.toString())
                                onImageClicked(encodedUrl)

                            }
                    )
                    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Start,
                                text = data.value?.data?.name ?: "ابگوشت",
                                style = MaterialTheme.typography.h1
                            )

                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)


                            ) {
                                Text(
                                    text = data.value?.data?.count ?: " ",
                                    style = MaterialTheme.typography.caption,
                                    modifier = Modifier.padding(vertical = 16.dp)
                                )
                                val time = (data.value?.data?.readyTime
                                    ?: 0) + (data.value?.data?.readyTime ?: 0)
                                Row(
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier


                                ) {
                                    if (time != 0) {
                                        Row(
                                            horizontalArrangement = Arrangement.Center,
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier
                                                .clip(MaterialTheme.shapes.medium)
                                                .background(
                                                    Color(0xFF5A3F3F),
                                                    MaterialTheme.shapes.medium
                                                )
                                        )
                                        {
                                            Image(
                                                painter = painterResource(id = R.drawable.frame),
                                                contentDescription = "",
                                                modifier = Modifier
                                                    .padding(
                                                        top = 8.dp,
                                                        start = 8.dp,
                                                        bottom = 8.dp
                                                    )
                                            )
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Text(
                                                text = "${time} دقیقه",
                                                style = MaterialTheme.typography.caption,
                                                modifier = Modifier
                                                    .padding(top = 8.dp, end = 8.dp, bottom = 8.dp),
                                                textAlign = TextAlign.Center,
                                            )
                                        }

                                    }
                                }
                            }

                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxSize()

                        ) {
                            val ListChip = data.value?.additionalInfo?.meals
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(ListChip ?: emptyList()) {
                                    Text(
                                        modifier = Modifier
                                            .clickable {
                                                Log.d("tag","${it.id}")

                                                moreMealClick(it.id)
                                            }
                                            .clip(MaterialTheme.shapes.medium)
                                            .background(
                                                Color(0xFF555555),
                                                MaterialTheme.shapes.medium
                                            )
                                            .padding(
                                                start = 30.dp,
                                                end = 30.dp,
                                                top = 8.dp,
                                                bottom = 8.dp
                                            ),
                                        text = it.name,
                                        style = MaterialTheme.typography.caption,
                                        textAlign = TextAlign.Center,
                                    )
                                }
                            }

                            var border = Color(0xFF00FF67)
                            var background = Color(0xFF00FF67)
                            var image = painterResource(id = R.drawable.green)
                            var text = "اسان"
                            when (data.value?.additionalInfo?.difficulty?.name ?: "0") {
                                "آسان" -> {
                                    border = Color(0xFF00FF67)
                                    background = Color(0x1A00FF67)
                                    image = painterResource(id = R.drawable.green)
                                    text = "آسان"
                                }

                                "متوسط" -> {
                                    border = Color(0xFFFFE100)
                                    background = Color(0x26FFE100)
                                    image = painterResource(id = R.drawable.yellow)
                                    text = "متوسط"
                                }

                                "دشوار" -> {
                                    border = Color(0xFFFF4444)
                                    background = Color(0x1AFF4444)
                                    image = painterResource(id = R.drawable.red)
                                    text = "سخت"
                                }
                            }
                            hardnesschip(background, border, image, text)

                        }
                        Spacer(
                            modifier = Modifier
                                .height(36.dp)
                        )
                        Row(
                            horizontalArrangement = Arrangement.Start
                        ) {

                            TabRow(
                                backgroundColor = MaterialTheme.colors.background,
                                indicator = {
                                    Box(
                                        modifier = Modifier
                                            .tabIndicatorOffset(it[statetabRow.currentPage])
                                            .height(2.dp)
                                            .background(MaterialTheme.colors.primary)
                                    )
                                },
                                divider = { },
                                modifier = if (tabs.size == 2) Modifier
                                    .height(50.dp)
                                    .fillMaxWidth(0.5f) else Modifier
                                    .height(50.dp)
                                    .fillMaxWidth(0.7f),
                                selectedTabIndex = statetabRow.currentPage
                            ) {
                                tabs.forEachIndexed { index, item ->
                                    Tab(
                                        unselectedContentColor = MaterialTheme.colors.onBackground,
                                        selectedContentColor = Color.Red,
                                        modifier = Modifier

                                            .background(MaterialTheme.colors.background)
                                            .wrapContentWidth(
                                                Alignment.CenterHorizontally,
                                                true
                                            ),
                                        selected = statetabRow.currentPage == index,
                                        onClick = {
                                            scope.launch {
                                                statetabRow.animateScrollToPage(index)
                                            }
                                        }) {
                                        Text(
                                            text = item,
                                            style = MaterialTheme.typography.h3,
                                            color = if (index == statetabRow.currentPage) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        HorizontalPager(state = statetabRow) {
                            Column(
                                modifier = Modifier
                                    .clip(MaterialTheme.shapes.medium)
                                    .background(MaterialTheme.colors.surface)
                                    .fillMaxWidth()
                                    .padding(start = 16.dp, end = 16.dp)
                                    .height(387.dp)
                                    .verticalScroll(rememberScrollState())
                            ) {
                                var text = ""
                                when (it) {
                                    0 -> text = data.value?.data?.ingredients ?: " "
                                    1 -> text = data.value?.data?.recipe ?: " "
                                    2 -> text = data.value?.data?.point ?: " "
                                }
                                Text(
                                    text = text,
                                    modifier = Modifier.padding(
                                        top = 21.dp,
                                        start = 11.dp,
                                        end = 5.dp,
                                        bottom = 5.dp
                                    )
                                )
                            }
                        }
                        Column {


                            Spacer(modifier = Modifier.height(11.dp))
                            Text(
                                text = stringResource(id = R.string.moreCategury),
                                style = MaterialTheme.typography.h3,
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            LazyRow(
                                modifier = Modifier.padding(bottom = 23.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                            ) {
                                items(getDetailByIdResponse?.data ?: emptyList()) {
                                    Column(
                                        modifier = Modifier
                                            .background(color = MaterialTheme.colors.background)
                                            .clickable {
                                                deitailClick(it.id)
                                            }) {
                                        AsyncImage(
                                            alignment = Alignment.Center,
                                            contentScale = ContentScale.Crop,
                                            model = it.image,
                                            contentDescription = "author's image",
                                            error = painterResource(id = R.drawable.f2),
                                            modifier = Modifier
                                                .width(100.dp)
                                                .height(84.dp)
                                                .clip(MaterialTheme.shapes.medium)
                                        )
                                        Spacer(modifier = Modifier.padding(start = 8.dp))
                                        Text(
                                            modifier = Modifier.padding(start = 8.dp),
                                            text = it.name,
                                            style = MaterialTheme.typography.body1
                                        )
                                        Spacer(modifier = Modifier.height(3.dp))
                                        val time = it.readyTime ?: 0 + (it.cookTime ?: 0)
                                        if (time != 0) {
                                            Text(
                                                modifier = Modifier.padding(
                                                    top = 3.dp,
                                                    start = 8.dp,
                                                    bottom = 9.dp
                                                ),
                                                text = "زمان: $time دقیقه",
                                                style = MaterialTheme.typography.subtitle1,
                                                color = MaterialTheme.colors.onSurface
                                            )
                                        }
                                    }
                                }
                                item {
                                    Column(
                                        modifier = Modifier
                                            .background(color = MaterialTheme.colors.background)
                                            .clickable {
                                                moreMealClick(data.value?.data?.categoryId?:"")
                                            }) {
                                        Box(
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Row {
                                                Text(
                                                    text = stringResource(id = R.string.more),
                                                    style = MaterialTheme.typography.body1
                                                )

                                                Icon(
                                                    Icons.Default.KeyboardArrowLeft,
                                                    contentDescription = " "
                                                )
                                            }

                                            Image(
                                                contentScale = ContentScale.Crop,
                                                painter = painterResource(id = R.drawable.more),
                                                contentDescription = "",
                                                modifier = Modifier
                                                    .width(100.dp)
                                                    .height(84.dp)
                                                    .clip(MaterialTheme.shapes.medium)
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(15.dp))
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(22.dp))
                        }
                    }
                }
            }
        }
        SnackbarHost(
            modifier = Modifier.padding(top = 670.dp),
            hostState = snackbarHostState,
            snackbar = { CustomSnackbar(it) }
        )
    }
}


@Composable
fun CustomSnackbar(snackbarData: SnackbarData) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = Color(0xFF393939),

        modifier = Modifier
            .padding(16.dp)
            .clip(MaterialTheme.shapes.small)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .background(Color(0xFF393939))
        ) {
            androidx.compose.material3.Text(
                text = snackbarData.visuals.message,
                color = Color.White
            )


            Spacer(Modifier.weight(1f))

            snackbarData.visuals.actionLabel?.let { actionLabel ->
                TextButton(onClick = { snackbarData.performAction() }) {
                    androidx.compose.material3.Text(
                        text = actionLabel.uppercase(Locale.ROOT),
                        color = MaterialTheme.colors.primary
                    )
                }
            }
        }
    }
}

@Composable
fun hardnesschip(background: Color, border: Color, image: Painter, text: String) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .background(
                background,
                MaterialTheme.shapes.medium
            )
            .border(
                width = 2.dp,
                color = border,
                shape = MaterialTheme.shapes.medium
            )
            .padding(
                start = 16.dp, end = 16.dp, top = 8.dp,
                bottom = 8.dp
            )
    ) {

        Image(
            painter = image,
            contentDescription = "",
            modifier = Modifier
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.caption,
            modifier = Modifier
        )

    }
}