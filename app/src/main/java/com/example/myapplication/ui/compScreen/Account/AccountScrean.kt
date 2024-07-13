package com.example.myapplication.ui.compScreen.Account

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.ui.Navigation.Screen


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable

fun AccountScreen(navController: NavHostController,viewModel: AccountViewModel ) {
val checkLogin = viewModel.checkLogin()
    if(checkLogin != null){
        navController.navigate(Screen.Profile.route + "?userName=${""}?userId=${""}?fileName=${""}?password=$")
    }
    else{
        Scaffold(
            topBar = {
                TopAppBar (
                    backgroundColor = MaterialTheme.colors.background,
                    content= {
                        Text(
                            text = stringResource(R.string.Account),
                            style = MaterialTheme.typography.h1,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 17.dp),
                            color = MaterialTheme.colors.onBackground,
                            textAlign = TextAlign.Justify

                        )
                    }
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
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

                        Image(
                            painter = painterResource(id = R.drawable.acount),
                            contentDescription = " ",
                            modifier = Modifier
                                .size(70.dp)
                                .clip(CircleShape)
                        )
                        Text(
                            color = MaterialTheme.colors.onBackground,
                            text = stringResource(id = R.string.Guest),
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier.padding(start = 24.dp)
                        )
                    }
                }

                Button(modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, top = 17.dp)
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colors.primary, MaterialTheme.shapes.medium),
                    onClick = {
                        navController.navigate(Screen.SingIn.route)
                    }) {
                    Text(textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.LoginAccont), style = MaterialTheme.typography.button,
                        color = MaterialTheme.colors.onBackground)
                }
            }
        }


    }

}

