package com.example.myapplication.ui.Navigation

sealed class Screen(val route:String){

    object Grouping: Screen("Grouping")
    object Cook: Screen("Cook")
    object Search: Screen("Search")
    object Account: Screen("Account")
    object Detail: Screen("Detail")
    object saveImage: Screen("saveImage")
    object CookResult: Screen("Cook_Result"){
        fun createRoute(text1: String,text2:Int,buttonOption:String): String {
            return "Cook_Result?text1=$text1&text2=$text2&buttonOption=$buttonOption"
        }
    }
    object SingIn: Screen("SingIn")
    object Login: Screen("Login")
    object Profile: Screen("Profile")
    object More: Screen("More")
    object FoodSave: Screen("FoodSave")
}
val screens = listOf(
    BottomNavGraph.Grouping,
    BottomNavGraph.Cook,
    BottomNavGraph.Search,
    BottomNavGraph.Account
)
