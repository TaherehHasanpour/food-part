package com.example.myapplication.ui.compScreen.pagesAccountScreen.profile


import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.categories
import com.example.myapplication.ui.Navigation.Screen
import ir.partsoftware.programmingquote.ui.common.Result

@Composable
fun Profile(
    navController: NavController,
    viewModule: ProfileViewModule,
    userName: String,
    userId: String,
    fileName: String,
    password: String
) {
    val fileImageProfile = viewModule.fileProfile.collectAsState()
    val inputUserName by viewModule.inputUserName.collectAsState()
    val passwordCurrent by viewModule.passwordCurrent.collectAsState()
    val passwordNew by viewModule.passwordNew.collectAsState()
    val passwordRepeat by viewModule.passwordRepeat.collectAsState()

    val passwordResult by viewModule.passwordResult.collectAsState(initial = Result.Idle)
    val editPasAndUNameResult by viewModule.editPasAndUNameResult.collectAsState(initial = Result.Idle)
    val mContext = LocalContext.current
    val hasUpperCase = remember { mutableStateOf(false) }
    val hasLowerCase = remember { mutableStateOf(false) }
    val userNameResult by viewModule.userNameResult.collectAsState(initial = Result.Idle)
    val userLogoutResult by viewModule.userLogoutResult.collectAsState(initial = Result.Idle)
    val state = remember {
        mutableStateOf(false)
    }

    val customTextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = Color.Transparent,
        unfocusedBorderColor = Color.Transparent,
        disabledBorderColor = Color.Transparent
    )
    BackHandler {
        navController.navigate(Screen.Grouping.route) {
            popUpTo("Grouping")
        }
    }
    Scaffold(
        modifier = Modifier.background(color = MaterialTheme.colors.background),
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                content = {
                    IconButton(onClick = {
                        navController.navigate(Screen.Grouping.route) {
                            popUpTo("Grouping")
                        }

                    }) {
                        Icon(
                            Icons.Default.KeyboardArrowRight,
                            contentDescription = " ",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                    Text(
                        text = stringResource(R.string.profile),
                        style = MaterialTheme.typography.h2,
                        modifier = Modifier,
                        color = MaterialTheme.colors.onBackground,
                        textAlign = TextAlign.Justify

                    )
                    Spacer(modifier = Modifier.weight(1f))

                }
            )
        }
    )
    {
        Column(Modifier.padding(it)) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(top = 40.dp, start = 24.dp)
                    .fillMaxWidth(),
            ) {


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,


                    ) {

                    AsyncImage(
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop,
                        model = fileImageProfile,
                        contentDescription = "author's image",
                        error = painterResource(id = R.drawable.acount),

                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape).
                        clickable {
                          viewModule.peref.edit().remove("token").commit()
                        }
                    )
                    Text(
                        color = MaterialTheme.colors.onBackground,
                        text = userName,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier
                            .padding(start = 16.dp)
                    )


                }
                IconButton(onClick = {
                    Log.d("tag", " filename$fileImageProfile")
                    state.value = true
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.logout_1),
                        contentDescription = " ",
                        modifier = Modifier.padding(end = 24.dp)
                    )
                }


            }
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier.padding(start = 22.dp, top = 24.dp)
            ) {
                items(categories) {
                    Column(
                        modifier = Modifier
                            .background(color = MaterialTheme.colors.background)
                            .clickable {
                                navController.navigate(Screen.Detail.route)
                            }) {
                        Image(
                            contentScale = ContentScale.Crop,
                            painter = painterResource(id = it.image),
                            contentDescription = "",
                            modifier = Modifier
                                .width(100.dp)
                                .height(84.dp)
                                .clip(MaterialTheme.shapes.medium)
                        )
                        Spacer(modifier = Modifier.height(8.3.dp))
                        Text(
                            text = it.titel,
                            style = MaterialTheme.typography.subtitle1
                        )
                        Spacer(modifier = Modifier.height(8.3.dp))
                        Text(
                            text = it.caption,
                            style = MaterialTheme.typography.subtitle2
                        )
                    }
                }
                item {
                    Column(
                        modifier = Modifier
                            .background(color = MaterialTheme.colors.background)
                            .clickable {
                                navController.navigate(Screen.FoodSave.route) {
                                    popUpTo("FoodSave")
                                }
                            }) {
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            Row {
                                Text(
                                    text = "بیشتر",
                                    style = MaterialTheme.typography.body1
                                )

                                Icon(
                                    Icons.Default.KeyboardArrowLeft,
                                    contentDescription = "more"
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

            var click1 by remember {
                mutableStateOf(false)
            }
            var click2 by remember {
                mutableStateOf(false)
            }
            Column(
                modifier = Modifier
                    .padding(start = 32.dp, end = 25.dp, top = 8.dp, bottom = 23.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = "تغییر نام کاربری", style = MaterialTheme.typography.h3
                        )
                        IconToggleButton(
                            checked = click1,
                            onCheckedChange = { _checked ->
                                click1 = _checked
                            }
                        ) {
                            Icon(
                                modifier = Modifier.padding(top = 4.dp),
                                imageVector = if (click1) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowLeft,
                                contentDescription = "Favorite Item",
                            )
                        }

                    }

                    if (click1) Column(modifier = Modifier.fillMaxWidth()) {


                        OutlinedTextField(
                            modifier = Modifier
                                .padding(top = 12.dp)
                                .fillMaxWidth()
                                .height(56.dp)
                                .background(
                                    color = MaterialTheme.colors.surface,
                                    shape = MaterialTheme.shapes.medium
                                ),
                            value = inputUserName,
                            placeholder = {
                                Text(
                                    stringResource(R.string.nameacountnew),
                                    style = MaterialTheme.typography.body1
                                )
                            },
                            shape = MaterialTheme.shapes.medium,
                            onValueChange = {
                                viewModule.setUserName(it)
                            },
                            colors = customTextFieldColors
                        )
                    }

                }

                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = "تغییر رمز عبور", style = MaterialTheme.typography.h3
                        )
                        IconToggleButton(
                            checked = click2,
                            onCheckedChange = { _checked ->
                                click2 = _checked
                            }
                        ) {
                            Icon(
                                modifier = Modifier.padding(top = 4.dp),
                                imageVector = if (click2) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowLeft,
                                contentDescription = "Favorite Item",
                            )
                        }

                    }

                    if (click2) Column(modifier = Modifier.fillMaxWidth()) {


                        OutlinedTextField(
                            modifier = Modifier
                                .padding(top = 12.dp)
                                .fillMaxWidth()
                                .height(56.dp)
                                .background(
                                    color = MaterialTheme.colors.surface,
                                    shape = MaterialTheme.shapes.medium
                                ),
                            value = passwordCurrent,
                            placeholder = {
                                Text(
                                    stringResource(R.string.passwordcurrent),
                                    style = MaterialTheme.typography.body1
                                )
                            },
                            shape = MaterialTheme.shapes.medium,
                            onValueChange = {
                                viewModule.setPasswordCurrent(it)
                            },
                            colors = customTextFieldColors
                        )
                        OutlinedTextField(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .fillMaxWidth()
                                .height(56.dp)
                                .background(
                                    color = MaterialTheme.colors.surface,
                                    shape = MaterialTheme.shapes.medium
                                ),
                            value = passwordNew,
                            placeholder = {
                                Text(
                                    stringResource(R.string.newpassword),
                                    style = MaterialTheme.typography.body1
                                )
                            },
                            shape = MaterialTheme.shapes.medium,
                            onValueChange = {
                                viewModule.setNewPassword(it)
                            },
                            colors = customTextFieldColors
                        )
                        OutlinedTextField(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .fillMaxWidth()
                                .height(56.dp)
                                .background(
                                    color = MaterialTheme.colors.surface,
                                    shape = MaterialTheme.shapes.medium
                                ),
                            value = passwordRepeat,
                            placeholder = {
                                Text(
                                    stringResource(R.string.repeatpassword),
                                    style = MaterialTheme.typography.body1
                                )
                            },
                            shape = MaterialTheme.shapes.medium,
                            onValueChange = {
                                viewModule.setPasswordRepeat(it)
                            },
                            colors = customTextFieldColors
                        )
                    }


                }
                Button(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                        .height(48.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colors.primary, MaterialTheme.shapes.medium),
                    enabled = inputUserName != "" || passwordCurrent != "" || passwordNew != "" || passwordRepeat != "",
                    onClick = {
                        if (inputUserName != "" && passwordCurrent == "" && passwordNew == "" && passwordRepeat == "" && inputUserName.length > 4) {
                            viewModule.editUserName()
                            if (userNameResult is Result.Error) {
                                Toast.makeText(
                                    mContext,
                                    "نام کاربری فرمت مناسبی ندارد",
                                    Toast.LENGTH_SHORT
                                ).show()
                                viewModule.setUserName("")
                            } else {
                                Toast.makeText(
                                    mContext,
                                    "نام کاربری با موفقیت تغییر کرد",
                                    Toast.LENGTH_SHORT
                                ).show()
                                viewModule.setUserName("")
                            }
                        }
                        if (inputUserName == "" && passwordCurrent != "" && passwordNew == passwordRepeat && passwordNew.length == 8) {
                            checkInputPassword(passwordNew, hasUpperCase, hasLowerCase)
                            if (!hasUpperCase.value || !hasLowerCase.value) {

                                Toast.makeText(
                                    mContext,
                                    "از حروف کوچک و بزرگ استفاده کنید",
                                    Toast.LENGTH_SHORT
                                ).show()


                            } else {
                                viewModule.editPassword()
                                if (passwordResult is Result.Error) {
                                    Toast.makeText(
                                        mContext,
                                        "رمز با موفقیت تغییر کرد",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    viewModule.setPasswordCurrent("")
                                    viewModule.setPasswordRepeat("")
                                    viewModule.setNewPassword("")
                                }

                            }
                        }
                        if (inputUserName.length > 4 && passwordCurrent != "" && passwordNew == passwordRepeat && passwordNew.length == 8) {
                            viewModule.editPasAndUName()
                            if (editPasAndUNameResult is Result.Error) {
                                Toast.makeText(
                                    mContext,
                                    "نام کاربری و رمز عبور با موفقیت تغییر کردند",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    mContext,
                                    "در وارد کردن اطلاعات خود دقت کنید",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                    }) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "تایید", style = MaterialTheme.typography.button,
                        color = MaterialTheme.colors.onBackground
                    )
                }


            }


        }
    }



    if (state.value) {


        Dialog(onDismissRequest = { state.value = false }) {
            Column(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colors.surface)
                    .width(294.dp)
                    .height(179.dp)
            ) {
                Text(
                    modifier = Modifier.padding(start = 40.dp, end = 40.dp, top = 36.dp),
                    text = "آیا تمایل به خروج از حساب کاربری خود را دارید؟",
                    style = MaterialTheme.typography.body1
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(
                        top = 24.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .width(180.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .padding(bottom = 16.dp)
                            .background(
                                MaterialTheme.colors.primary,
                                MaterialTheme.shapes.medium
                            )
                            .fillMaxHeight()
                            .clickable {
                                viewModule.userLogout()
                                if (userLogoutResult is Result.Error){
                                    Log.d("tag","Logout $userLogoutResult")
                                }else{

                                    navController.navigate(Screen.Grouping.route)
                                }
                            }
                    ) {
                        Text(
                            text = "خروج", style = MaterialTheme.typography.button,
                            textAlign = TextAlign.Center
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(bottom = 16.dp, start = 16.dp)
                            .fillMaxHeight()
                            .width(66.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .border(
                                3.dp,
                                MaterialTheme.colors.primary,
                                MaterialTheme.shapes.medium
                            )
                    ) {
                        Text(
                            text = "انصراف", style = MaterialTheme.typography.button,
                            textAlign = TextAlign.Center
                        )
                    }

                }

            }

        }

    }


}


fun checkInputPassword(
    text: String,
    hasUpperCase: MutableState<Boolean>,
    hasLowerCase: MutableState<Boolean>
) {
    hasUpperCase.value = false
    hasLowerCase.value = false

    for (char in text) {
        if (char.isUpperCase()) {
            hasUpperCase.value = true
        } else if (char.isLowerCase()) {
            hasLowerCase.value = true
        }
    }
}

