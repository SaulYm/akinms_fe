package com.example.akinms.viewsborrar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.akinms.Minimarket
import com.example.akinms.components.*

@Composable
fun CartView(navController: NavHostController,cant:Int = 1){
    var cantidad = remember { mutableStateOf(cant) }
    val minimarket: Minimarket = Minimarket()
    Scaffold(
        topBar = {
            //NavBarTop(minimarket, navController)
        },
        bottomBar = {
            NavBarBottom()
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                item{
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp),
                        text = "Carrito de Compras",
                        textAlign = TextAlign.Center,
                        color = Color(0xFF03AC00),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                item {
                    //CartProductItem(cantidad)
                }
                item{
                    //CartTotalResume()
                }
            }
        )
    }
}