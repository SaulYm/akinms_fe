package com.example.akinms.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.akinms.ui.bodega.cart.CartScreen
import com.example.akinms.ui.bodega.products.ProductsScreen

@Composable
fun AkinmsNavGraph(
    modifier: Modifier = Modifier,
    //navigateToHome: () -> Unit,
    navigateToProducts: () -> Unit,
    navigateToCart: () -> Unit,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Products.route
){
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination,
    ){
        /*composable(route = Screen.Home.route){
            HomeScreen(navController = navController)
        } */
        composable(route = Screen.Products.route){
            //ProductsScreen(navController = navController)
        }
        composable(route = Screen.Cart.route){
            //CartScreen(navController = navController)
        }
    }
}