package com.example.akinms.ui.bodega.categories

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.akinms.Minimarket
import com.example.akinms.components.NavBarBottom
import com.example.akinms.components.NavBarTop
import com.example.akinms.components.SearchBar
import com.example.akinms.domain.model.Categoria

@Composable
fun CategoriesScreen(
    navController: NavHostController,
    bodegaNombre: String,
    listaCategorias: List<Categoria>,
    idBodega: Int,
    //minimarket: Minimarket
){
    //val minimarket: Minimarket = Minimarket()
    /*Scaffold(
        topBar = {
            //NavBarTop(minimarket,navController)
        }
    ) {
        Column() {
            SearchBar(minimarket.nombre)
            AllCategories(navController)
        }
    }*/
    Column() {
        SearchBar(
            navController = navController,
            name = bodegaNombre,
            idBodega = idBodega
        )
        AllCategories(navController, category = listaCategorias, idBodega = idBodega)
    }
}