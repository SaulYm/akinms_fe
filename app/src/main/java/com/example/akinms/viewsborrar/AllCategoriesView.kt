package com.example.akinms.viewsborrar

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.akinms.Minimarket
import com.example.akinms.components.NavBarBottom
import com.example.akinms.components.NavBarTop
import com.example.akinms.components.SearchBar
import com.example.akinms.ui.bodega.categories.AllCategories

@Composable
fun AllCategoriesView(navController: NavHostController){
    val minimarket:Minimarket = Minimarket()
    Scaffold(
        topBar = {
            //NavBarTop(minimarket,navController)
        },
        bottomBar = {
            NavBarBottom()
        }
    ) {
        Column() {
            //SearchBar(minimarket.nombre)
            //AllCategories(navController)
        }
    }
}