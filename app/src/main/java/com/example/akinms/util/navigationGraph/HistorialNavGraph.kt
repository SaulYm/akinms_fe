package com.example.akinms.util.navigationGraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.akinms.ui.profile.historial.DetailsScreen
import com.example.akinms.ui.profile.historial.HistorialScreen
import com.example.akinms.ui.profile.historial.StatusScreen

fun NavGraphBuilder.historialNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.HISTORIAL,
        startDestination = ProfileScreen.Historial.route
    ) {
        composable(route = ProfileScreen.Historial.route) {
            HistorialScreen(navController = navController)
        }
        composable(route = HistorialScreen.Status.route){
            StatusScreen(navController = navController)
        }
        composable(route = HistorialScreen.Details.route){
            DetailsScreen(navController = navController)
        }

    }
}

sealed class HistorialScreen(val route: String) {
    object Status: HistorialScreen(route = "STATUS")
    object Details: HistorialScreen(route = "DETAILS")
}