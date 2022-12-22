package com.example.akinms.util.navigationGraph

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navigation
import com.example.akinms.ui.settings.SettingsScreen
import com.example.akinms.util.BottomBarScreen
import com.example.akinms.util.CustomNavHost
import com.example.akinms.util.NavigationIntent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

/*@Composable
fun HomeNavGraph(
    homeViewModel: HomeViewModel,
    navController: NavHostController
) {
    /*NavigationEffects(
        navigationChannel = homeViewModel.navigationChannel,
        navHostController = navController
    )*/
    CustomNavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        route = Graph.HOME
    ){
        composable(route = BottomBarScreen.Home.route) {
            CoreScreen(navController=navController, homeViewModel = homeViewModel)
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen()
        }
        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen()
        }
        coreNavGraph(navController=navController,homeViewModel=homeViewModel)
        profileNavGraph(navController=navController)
    }
}*/
fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController
){
    navigation(
        route = Graph.HOME,
        startDestination = Graph.CORE
    ) {
        coreNavGraph(navController=navController)
        profileNavGraph(navController=navController)
        /*composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen()
            profileNavGraph(navController=navController)
        }*/
        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen(navController=navController)
        }
    }
}
/*@Composable
fun NavigationEffects(
    navigationChannel: Channel<NavigationIntent>,
    navHostController: NavHostController
) {
    val activity = (LocalContext.current as? Activity)
    LaunchedEffect(activity, navHostController, navigationChannel) {
        navigationChannel.receiveAsFlow().collect { intent ->
            if (activity?.isFinishing == true) {
                return@collect
            }
            when (intent) {
                is NavigationIntent.NavigateBack -> {
                    if (intent.route != null) {
                        navHostController.popBackStack(intent.route, intent.inclusive)
                    } else {
                        navHostController.popBackStack()
                    }
                }
                is NavigationIntent.NavigateTo -> {
                    navHostController.navigate(intent.route) {
                        println("ACAAAAAAAAAAAAAAAAAAAAAAaaaa22222222222222")
                        launchSingleTop = intent.isSingleTop
                        intent.popUpToRoute?.let { popUpToRoute ->
                            popUpTo(popUpToRoute) { inclusive = intent.inclusive }
                        }
                    }
                }
            }
        }
    }
}*/