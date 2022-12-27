package com.example.akinms.viewsborrar

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.akinms.Minimarket
import com.example.akinms.components.*

@Composable
fun HomeView(navController: NavHostController){
    val minimarket:Minimarket = Minimarket()
    Scaffold(
        topBar = {
            //NavBarTop(minimarket,navController)
        },
        bottomBar = {
            NavBarBottom()
        }
    ) {
        LazyColumn(
            content = {
                item {
                    //SearchBar(minimarket.nombre)
                }
                item {
                    //SomeCategories(navController = navController)
                }
                item {
                    //Other(navController)
                }
            }
        )
    }
}