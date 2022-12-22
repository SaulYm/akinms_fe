package com.example.akinms.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.akinms.ui.Maps.MapsScreen
import com.example.akinms.ui.bodega.BodegaScreen
import com.example.akinms.ui.settings.SettingsScreen
import com.example.akinms.util.BottomBarScreen
import com.example.akinms.util.navigationGraph.CoreScreen
import com.example.akinms.util.navigationGraph.Graph
import com.example.akinms.util.navigationGraph.coreNavGraph
import com.example.akinms.util.navigationGraph.profileNavGraph

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    Scaffold(
        //bottomBar = { /*BottomBar(navController = navController)*/ }
    ) {
        Column() {
            Text(text = "Parte superior")
            Button(onClick = { navController.navigate(CoreScreen.Maps.route) }) {
                Text(text = "Buscar bodega")
            }
            Button(onClick = { navController.navigate(Graph.BODEGA)}) {
                Text(text = "Mostrar bodega")
            }
        }
    }
}
