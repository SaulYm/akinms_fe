package com.example.akinms.borrar

const val ROOT_GRAPH_ROUTE = "root"
const val AUTH_GRAPH_ROUTE = "auth"
const val HOME_GRAPH_ROUTE = "home"

sealed class Destination(
    protected val route: String,
    vararg params: String
){
    val fullRoute: String = if (params.isEmpty()) route else {
        val builder = StringBuilder(route)
        params.forEach { builder.append("/{${it}}") }
        builder.toString()
    }

    sealed class NoArgumentsDestination(route: String) : Destination(route) {
        operator fun invoke(): String = route
    }
    object HomeScreen : NoArgumentsDestination("home")

    object ProfileScreen : NoArgumentsDestination("profile")

    object SettingsScreen : NoArgumentsDestination("settings")

    object MapsScreen: NoArgumentsDestination("maps")

    object BodegaScreen: NoArgumentsDestination("bodega")

    object CategoriesScreen: NoArgumentsDestination("categories")

    object ProductsScreen: NoArgumentsDestination("products")

    object CartScreen: NoArgumentsDestination("cart")


    /*object UserDetailsScreen : Destination("user_details", "firstName", "lastName") {
        const val FIST_NAME_KEY = "firstName"
        const val LAST_NAME_KEY = "lastName"

        operator fun invoke(fistName: String, lastName: String): String = route.appendParams(
            FIST_NAME_KEY to fistName,
            LAST_NAME_KEY to lastName
        )
    }*/
}
internal fun String.appendParams(vararg params: Pair<String, Any?>): String {
    val builder = StringBuilder(this)
    params.forEach {
        it.second?.toString()?.let { arg ->
            builder.append("/$arg")
        }
    }
    return builder.toString()
}