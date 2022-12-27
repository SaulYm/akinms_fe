package com.example.akinms.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavHostController

import androidx.navigation.compose.rememberNavController
import com.example.akinms.R

import com.example.akinms.ui.theme.AkinmsTheme

import com.example.akinms.util.navigationGraph.CoreScreen


@Preview
@Composable
fun HomeScreenV2(
    navController: NavHostController = rememberNavController()
) = AkinmsTheme {

    val (value, onValueChange) = remember { mutableStateOf("") } //para el buscador inicial
    val (valueUbi, onValueUbiChange) = remember { mutableStateOf("") } //para el buscador ubicacion
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar() },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //BUSCADOR
                TextField(
                    value = value,
                    onValueChange = onValueChange,
                    //textStyle = TextStyle(fontSize = 17.sp),
                    leadingIcon = { Icon(Icons.Filled.Search, null, tint = Color.Gray) },
                    modifier = Modifier
                        .padding(10.dp)
                        .background(Color(0xFFE7F1F1), RoundedCornerShape(19.dp)),
                    placeholder = { Text(text = "Buscar promociones, tiendas, productos") },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        backgroundColor = Color.Transparent,
                        cursorColor = Color.DarkGray
                    )
                )

                //ROW TEMPLATE
                Row(verticalAlignment = Alignment.CenterVertically, modifier =  Modifier.padding(bottom = 30.dp)){
                    Text(text = "", textAlign = TextAlign.Center)
                }

                /*
                Text(
                    text = "Content of the page",
                    fontSize = 30.sp,
                    color = Color.Black
                )
                */

                //OFERTAS DESTACADAS
                Row(verticalAlignment = Alignment.CenterVertically, modifier =  Modifier.padding(bottom = 30.dp)){
                    Text(text = "Ofertas Destacadas",
                        textAlign = TextAlign.Left,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    //ACA VAN LAS IMAGENES
                }

                //DESCUBRE BODEGAS ALREDEDOR
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier =  Modifier.padding(bottom = 30.dp))
                {
                    Text(
                        text = "Descubre bodegas a tu alrededor",
                        textAlign = TextAlign.Left,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                //ACA VAN LAS OPCIONES
                //OPCIONES
                Column(modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(Color.White),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally)
                {
                    Column{
                        Row(horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.width(1000.dp)){

                            Column{
                                //Icon(imageVector = Icons.Filled.LocationOn, contentDescription = null)
                                Button(onClick = { }, modifier = Modifier.height(30.dp).width(40.dp)) {
                                    Text(text = "1")
                                }
                                Text(text = "Elija una ubicación")
                                Button(onClick = { navController.navigate(CoreScreen.Maps.route)},
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)) {
                                    Text(text = "GPS")
                                }
                                TextField(
                                    value = valueUbi,
                                    onValueChange = onValueUbiChange,
                                    //textStyle = TextStyle(fontSize = 17.sp),
                                    leadingIcon = { Icon(Icons.Filled.Search, null, tint = Color.Gray, modifier = Modifier.size(20.dp)) },
                                    modifier = Modifier
                                        .size(140.dp, 50.dp)
                                        .background(
                                            Color(0xFFE7F1F1),
                                            RoundedCornerShape(19.dp)
                                        ),
                                    placeholder = { Text(text = "Dirección", fontSize = 15.sp) },
                                    colors = TextFieldDefaults.textFieldColors(
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        backgroundColor = Color.Transparent,
                                        cursorColor = Color.DarkGray
                                    )
                                )

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier =  Modifier.padding(bottom = 30.dp))
                                {
                                    Text(
                                        text = ""
                                    )
                                }

                                //Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = null)
                                Button(onClick = { }, modifier = Modifier.height(30.dp).width(40.dp))  {
                                    Text(text = "2")
                                }

                                Text(text = buildAnnotatedString {
                                    //appendInlineContent("[Icons.Filled.PlayArrow]")
                                    append("Buscar Bodegas")
                                })
                                Button(onClick = { navController.navigate(CoreScreen.Maps.route)},
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)) {
                                    Text(text = "BUSCAR")
                                }
                            }

                            //Vista de mapa
                            Column{
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier =  Modifier.padding(bottom = 30.dp))
                                {
                                }
                                Image(painter = painterResource(id = R.drawable.mapa_provisional),
                                    contentDescription = null)
                                //Text(text = "Aqui debe ir el mapa")
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier =  Modifier.padding(bottom = 30.dp))
                                {
                                }
                            }
                        }

                    }

                }





            }

        },
        bottomBar = {BottomBar()}
    )

}

@Composable
private fun TopBar() {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Abrir menú desplegable")
            }
        },

        title = { Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = null)
            Arrangement.Center
            Alignment.CenterHorizontally
        },
        actions = {

            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "Más")
            }
        },
        backgroundColor = Color.White,
        elevation = 10.dp,

        )
}



@Composable
fun BottomBar() {
    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(elevation = 10.dp) {

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Home,"")
        },
            label = { Text(text = "Home") },
            selected = (selectedIndex.value == 0),
            onClick = {
                selectedIndex.value = 0
            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Search,"")
        },
            label = { Text(text = "Buscar") },
            selected = (selectedIndex.value == 1),
            onClick = {
                selectedIndex.value = 1
            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Close,"")
        },
            label = { Text(text = "Salir") },
            selected = (selectedIndex.value == 2),
            onClick = {
                selectedIndex.value = 2
            })
    }
}