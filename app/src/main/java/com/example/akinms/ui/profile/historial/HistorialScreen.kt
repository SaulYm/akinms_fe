package com.example.akinms.ui.profile.historial

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.akinms.R
import com.example.akinms.ui.profile.ProfileViewModel
import com.example.akinms.util.navigationGraph.HistorialScreen

@Composable
fun HistorialScreen(
    navController: NavHostController,
    profileViewModel: ProfileViewModel = hiltViewModel()
    //onBackClick: Boolean,
){
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
            Text(text = "Historial de Compras")
        }
        Text(text = "AQUI IRÍA EL HISTORIAL SI MIS COMPAS LO HUBIERAN HECHO")
        Button(onClick = { navController.navigate(HistorialScreen.Status.route) }) {
            Text(text = "Ver estado del pedido")
        }
        Button(onClick = { navController.navigate(HistorialScreen.Details.route) }) {
            Text(text = "Ver detalles del pedido")
        }
    }
}