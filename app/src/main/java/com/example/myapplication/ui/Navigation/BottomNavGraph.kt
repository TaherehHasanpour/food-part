package com.example.myapplication.ui.Navigation

import com.example.myapplication.R

sealed class BottomNavGraph(
    val name: Int,
    val route:String,
    val icon:Int){
    object Grouping: BottomNavGraph(R.string.Grouping,"Grouping", R.drawable.grouping)
    object Cook: BottomNavGraph(R.string.Cook,"Cook", R.drawable.cook)
    object Search: BottomNavGraph(R.string.Search,"Search", R.drawable.search)
    object Account: BottomNavGraph(R.string.Account,"Account", R.drawable.person)

}

