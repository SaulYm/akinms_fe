package com.example.akinms.ui.Maps

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.akinms.R
import com.example.akinms.ui.bodega.products.ProductsViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MapsScreen(
    navController: NavHostController,
    mapsViewModel: MapsViewModel = hiltViewModel()
){
    val state = mapsViewModel.state
    val eventFlow = mapsViewModel.eventFlow
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true){
        eventFlow.collectLatest { event ->
            when(event) {
                is MapsViewModel.UIEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(painter = painterResource(R.drawable.ic_back), contentDescription = "")
            }
            Text(text = "Encuentra tu bodega cercana")
        }
        println("CANTIDAD DE BODEGAS: "+state.bodegas.size)
        MapContainer(
            navController = navController,
            isLoading = state.isLoading,
            bodegas = state.bodegas
        )
    }
}