package com.example.akinms.util.navigationGraph

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.akinms.ui.home.HomeScreen
import com.example.akinms.ui.main.MainViewModel
import com.example.akinms.util.NavigationIntent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun RootNavigationGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(navController = navController)
        /*composable(route = Graph.HOME) {
            HomeScreen()
        }*/
        homeNavGraph(navController = navController)
    }

}
object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val CORE = "core_graph"
    const val PROFILE = "profile_graph"
    const val BODEGA = "bodega_graph"
    const val HISTORIAL = "historial_graph"
}
