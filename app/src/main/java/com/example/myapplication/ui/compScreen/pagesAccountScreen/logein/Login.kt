package com.example.myapplication.ui.compScreen.pagesAccountScreen.logein


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.ui.Navigation.Screen
import ir.partsoftware.programmingquote.ui.common.Result

@Composable
fun Login(
    navController: NavController,
    viewModel: LoginViewModel,
) {
    val dataLogin by viewModel.loginResponse.collectAsState()
    val userName by viewModel.userName.collectAsState()
    val password by viewModel.password.collectAsState()
    val loginResult by viewModel.loginResult.collectAsState(Result.Idle)

    Scaffold(
        modifier = Modifier.background(color = MaterialTheme.colors.background),
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                content = {
                    IconButton(onClick = {
                        navController.navigate(Screen.SingIn.route) {
                            popUpTo("SingIn")
                        }

                    }) {
                        Icon(
                            Icons.Default.KeyboardArrowRight,
                            contentDescription = " ",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                    Text(
                        text = stringResource(R.string.Login),
                        style = MaterialTheme.typography.h2,
                        modifier = Modifier,
                        color = MaterialTheme.colors.onBackground,
                        textAlign = TextAlign.Justify

                    )
                    Spacer(modifier = Modifier.weight(1f))

                }
            )
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {

            Box(
                modifier = Modifier
                    .padding(top = 65.dp, start = 156.dp, end = 156.dp)
                    .aspectRatio(1f)
                    .background(MaterialTheme.colors.primary, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_dark_1__3_),
                    contentDescription = " ",
                    modifier = Modifier
                        .width(64.dp)
                        .height(64.dp)

                        .clip(CircleShape)
                )
            }

            Column(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 88.dp)

            ) {
                Text(
                    text = "خوش آمدید",
                    style = MaterialTheme.typography.h1
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "برای ورود اطلاعات حساب خود را وارد کنید",
                    style = MaterialTheme.typography.body1
                )
                val customTextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent
                )

                OutlinedTextField(
                    modifier = Modifier
                        .padding(top = 43.dp)
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(
                            color = MaterialTheme.colors.surface,
                            shape = MaterialTheme.shapes.medium
                        ),
                    value = userName,
                    placeholder = {
                        Text(
                            stringResource(R.string.nameacount),
                            style = MaterialTheme.typography.body1
                        )
                    },
                    shape = MaterialTheme.shapes.medium,
                    onValueChange = {
                        viewModel.setUserName(it)
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
                    value = password,
                    placeholder = {
                        Text(
                            stringResource(R.string.password),
                            style = MaterialTheme.typography.body1
                        )
                    },
                    shape = MaterialTheme.shapes.medium,
                    onValueChange = {
                        viewModel.setPassword(it)
                    },
                    colors = customTextFieldColors
                )
                Button(modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colors.primary, MaterialTheme.shapes.medium),
                    onClick = {
                        viewModel.fetchLoginUser(userName, password)
                        if (loginResult is Result.Success) {
                            navController.navigate(Screen.Profile.route + "?userName=${dataLogin?.data?.user?.username ?: ""}?userId=${dataLogin?.data?.user?.id ?: ""}?fileName=${dataLogin?.data?.user?.avatar ?: ""}?password=$password")
                        }

                    }) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "تایید", style = MaterialTheme.typography.button,
                        color = MaterialTheme.colors.onBackground
                    )
                }

                Row(modifier = Modifier.padding(top = 8.dp, end = 24.dp)) {
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "حساب کاربری ندارید؟", style = MaterialTheme.typography.subtitle2,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        modifier = Modifier.clickable {
                            navController.navigate(Screen.SingIn.route) {
                                popUpTo("SingIn")
                            }


                        },
                        style = MaterialTheme.typography.subtitle2,
                        text = " ثبت نام ",
                        color = Color.Blue,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        modifier = Modifier.clickable {


                            navController.navigate(Screen.Login.route)
                        },
                        style = MaterialTheme.typography.subtitle2,
                        text = "کنید",
                        color = MaterialTheme.colors.onBackground,
                        textAlign = TextAlign.Center,
                    )


                }
            }

        }


    }

}