package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.Navigation.NavHostScreen
import com.example.myapplication.ui.Navigation.Screen
import com.example.myapplication.ui.Navigation.screens
import com.example.myapplication.ui.theme.ProgrammingfoodPartTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()


        setContent {

            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val current = navBackStackEntry?.destination
            ProgrammingfoodPartTheme {
                val listScreenMain = listOf(
                    Screen.Grouping.route,
                    Screen.Cook.route,
                    Screen.Search.route,
                    Screen.Account.route,
                )
                Scaffold(
                    modifier = Modifier.background(color = MaterialTheme.colors.background),
                    bottomBar = {
                        if (current?.route in listScreenMain) {
                            BottomNavigation(
                                backgroundColor = MaterialTheme.colors.secondary,
                                modifier = Modifier
                                    .height(80.dp)
                                    .clip(
                                        shape = RoundedCornerShape(
                                            topStart = 28.dp,
                                            topEnd = 28.dp
                                        )

                                    )
                                    .fillMaxWidth()
                                    .background(color = MaterialTheme.colors.secondary)

                            ) {
                                screens.forEach {
                                    BottomNavigationItem(
                                        modifier = Modifier
                                            .padding(vertical = 16.dp)
                                            .background(color = MaterialTheme.colors.secondary)
                                            .align(Alignment.CenterVertically),
                                        selected = current?.route == it.route,
                                        onClick = {
                                            navController.navigate(it.route) {
                                                popUpTo("Grouping") {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        },
                                        selectedContentColor = MaterialTheme.colors.primary,
                                        unselectedContentColor = MaterialTheme.colors.onBackground,
                                        icon = {
                                            Icon(
                                                painter = painterResource(id = it.icon),
                                                contentDescription = "Grouping"
                                            )
                                        },
                                        label = {
                                            Text(
                                                text = stringResource(id = it.name),
                                                style = MaterialTheme.typography.subtitle1
                                            )
                                        })
                                }
                            }
                        }

                    }) {
                    Box(modifier = Modifier.padding(it)) {
                        NavHostScreen(navController)
                    }
                }
            }
        }
    }
}



