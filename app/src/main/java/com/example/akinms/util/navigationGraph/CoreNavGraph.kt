package com.example.akinms.util.navigationGraph

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.akinms.ui.Maps.MapsScreen
import com.example.akinms.ui.bodega.BodegaScreen
import com.example.akinms.ui.home.HomeScreen
import com.example.akinms.ui.home.HomeViewModel
import com.example.akinms.util.BottomBarScreen

fun NavGraphBuilder.coreNavGraph(
    navController: NavHostController
) {
    navigation(
        route = Graph.CORE,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {

            //CoreScreen(navController=navController)
            HomeScreen(navController = navController)
        }
        composable(route = CoreScreen.Ofertas.route){
            Column() {
                Text(text = "All oferts Screen")
            }
        }
        composable(route = CoreScreen.Maps.route) {
            MapsScreen(navController = navController)
        }
        composable(route = Graph.BODEGA+"/{id}",
            arguments = listOf(navArgument("id"){type= NavType.LongType})) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id")
            requireNotNull(id)
            BodegaScreen(coreNavController = navController, id = id)
        }
        //bodegaNavGraph(navController=navController)
    }
}
sealed class CoreScreen(val route: String) {
    object Ofertas: CoreScreen(route = "OFERTAS")
    object Maps : CoreScreen(route = "MAPS")
}