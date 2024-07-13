package com.example.myapplication.ui.Navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapplication.ui.compScreen.Account.AccountScreen
import com.example.myapplication.ui.compScreen.PagesGrouping.Detail.DetailScreen
import com.example.myapplication.ui.compScreen.Grouping.GroupingScreen
import com.example.myapplication.ui.compScreen.Common.More
import com.example.myapplication.ui.compScreen.Cook.CookResult
import com.example.myapplication.ui.compScreen.Cook.CookScreen
import com.example.myapplication.ui.compScreen.PagesGrouping.saveImage.saveImage
import com.example.myapplication.ui.compScreen.Search.SearchScreen
import com.example.myapplication.ui.compScreen.pagesAccountScreen.FoodSave
import com.example.myapplication.ui.compScreen.pagesAccountScreen.logein.Login
import com.example.myapplication.ui.compScreen.pagesAccountScreen.profile.Profile
import com.example.myapplication.ui.compScreen.pagesAccountScreen.singin.SingIn

@Composable
fun NavHostScreen(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "Grouping",
    ) {
        composable(Screen.Grouping.route) {
            GroupingScreen(viewModel = hiltViewModel(), onclick = {
                navController.navigate("Detail/$it")
            })
        }

        composable(Screen.Cook.route) {
            CookScreen(navController = navController, viewModel = hiltViewModel())
        }


        composable(Screen.Search.route) {
            SearchScreen(navController = navController, viewModel = hiltViewModel())
        }

        composable(Screen.Account.route) {
            AccountScreen(navController = navController, viewModel = hiltViewModel())
        }
        composable(route = Screen.Detail.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    this.type = NavType.StringType
                }
            )
        ) {
            DetailScreen(
                navController = navController,
                viewModel = hiltViewModel(),
                onImageClicked = {
                    navController.navigate(Screen.saveImage.route + "/$it")
                },
                deitailClick = {
                    navController.navigate("Detail/$it")
                },
                moreMealClick = {
                    navController.navigate(Screen.More.route + "/$it")
                })

        }
        composable(Screen.saveImage.route + "/{img}",
            arguments = listOf(
                navArgument("img") {
                    this.type = NavType.StringType
                }
            )) {
            saveImage(navController = navController, viewModel = hiltViewModel())
        }


        composable(route = Screen.CookResult.route) {
            CookResult(
                navController = navController,
                text1 = it.arguments?.getString("text1").toString(),
                text2 = it.arguments?.getString("text2").toString(),
                buttonOption = it.arguments?.getString("buttonOption").toString(),
                viewModel = hiltViewModel()
            )
        }


        composable(Screen.SingIn.route) {
            SingIn(navController = navController, viewModel = hiltViewModel())
        }
        composable(Screen.Login.route) {
            Login(
                navController = navController,
                viewModel = hiltViewModel(),
            )
             }
        composable(Screen.Profile.route + "?userName={userName}" + "?userId={userId}" + "?fileName={fileName}" + "?password={password}",
            arguments = listOf(
                navArgument("userName") {
                    type = NavType.StringType
                },
                navArgument("userId") {
                    type = NavType.StringType
                },
                navArgument("fileName") {
                    type = NavType.StringType
                },
                navArgument("password") {
                    type = NavType.StringType
                }
            )
        ) {

            Profile(navController = navController, viewModule = hiltViewModel(),
                userName = it.arguments?.getString("userName").toString(),
                userId = it.arguments?.getString("userId").toString(),
                fileName = it.arguments?.getString("fileName").toString(),
                password = it.arguments?.getString("password").toString())
        }
        composable(Screen.More.route + "/{idMeal}",
            arguments = listOf(
                navArgument("idMeal") {
                    this.type = NavType.StringType
                }
            )) {
            More(navController = navController, viewModel = hiltViewModel(), onclick = {
                navController.navigate("Detail/$it")
            })
        }
        composable(Screen.FoodSave.route) {
            FoodSave(navController = navController)
        }


    }
}
