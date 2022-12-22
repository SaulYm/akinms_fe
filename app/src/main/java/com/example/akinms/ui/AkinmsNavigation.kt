package com.example.akinms.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

sealed class  Screen(val route: String){
    object Home: Screen("home")
    object Detail: Screen("detail?id={id}"){
        fun passId(id: Int): String{
            return "detail?id=$id"
        }
    }
    object Products: Screen("products")
    object Cart: Screen("cart")
}


class AkinmsActions(navController: NavController) {
    /*val navigateToHome: () -> Unit = {
        navController.navigate(Screen.Home.route){
            popUpTo(navController.graph.findStartDestination().id){
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    } */
    val navigateToProducts: () -> Unit = {
        navController.navigate(Screen.Products.route){
            popUpTo(navController.graph.findStartDestination().id){
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    /*val navigateToDetail = {id:Int ->
        navController.navigate(Screen.Detail.passId(id)){
            popUpTo(navController.graph.findStartDestination().id){
                saveState = true
            }
            launchSingleTop = true
        }
    } */
    val navigateToCart: () -> Unit = {
        navController.navigate(Screen.Cart.route){
            popUpTo(navController.graph.findStartDestination().id){
                saveState = true
            }
            launchSingleTop = true
        }
    }
}