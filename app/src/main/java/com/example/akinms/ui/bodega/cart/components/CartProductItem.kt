package com.example.akinms.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.akinms.R
import com.example.akinms.domain.model.CartItem
import com.example.akinms.ui.Screen
import com.example.akinms.ui.bodega.cart.CartViewModel

@Composable
fun CartProductItem(
    //cantidad: MutableState<Int>
    item: CartItem,
    viewModel: CartViewModel,
    navController: NavHostController,
){
    //var cantidad = remember { mutableStateOf(1) }
    val context: Context = LocalContext.current
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(0.9f)
            .height(105.dp)
            .border(width = 1.dp, color = Color(0xFFFFC532), shape = RoundedCornerShape(10.dp)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(

        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(Color(0xFFFFC532))

            ){

                Image(
                    modifier = Modifier.align(Alignment.Center),
                    painter = rememberAsyncImagePainter(item.img),
                    contentDescription = null,
                    //modifier = Modifier.size(100.dp)
                )
            }
            Column(
                modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = item.nombre,
                    fontSize = 16.sp
                )
                Text(
                    text = item.descripcion,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "S/. "+item.precio.toString(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Column(
            modifier = Modifier.padding(end = 10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            var cantidad = remember { mutableStateOf(item.cantidad) }
            //IncDecBtn(item = item, context = context, viewModel = viewModel)
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Cantidad:")
                Row(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .border(width = 1.dp, color = Color(0xFFD9D9D9), shape = RoundedCornerShape(10.dp)),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    /*Button(
                        onClick = {
                            if (cantidad.value > 1) {
                                cantidad.value--
                                item.cantidad = cantidad.value
                                viewModel.updateCartItem(item)
                                navController.popBackStack()
                                navController.navigate(Screen.Cart.route)
                            } else {
                                Toast
                                    .makeText(
                                        context,
                                        "Cantidad minima es 1",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                        },
                        modifier = Modifier.width(30.dp),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFFD9D9D9),
                            contentColor = Color(0x00000000)
                        ),
                    ) {
                        Text(text = "-", fontSize = 12.sp)
                    }
                    Text(text = cantidad.value.toString())
                    Button(
                        onClick = {
                            cantidad.value++
                            item.cantidad = cantidad.value
                            viewModel.updateCartItem(item)
                            navController.popBackStack()
                            navController.navigate(Screen.Cart.route)
                        },
                        modifier = Modifier.width(30.dp),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFFD9D9D9),
                            contentColor = Color(0x00000000)
                        ),
                    ) {
                        Text(text = "+", fontSize = 12.sp)
                    }*/
                    Box(
                        modifier = Modifier.width(30.dp).height(30.dp).background(Color(0xFFD9D9D9))
                            .clickable {
                                if (cantidad.value > 1) {
                                    cantidad.value--
                                    item.cantidad = cantidad.value
                                    viewModel.updateCartItem(item)
                                    navController.popBackStack()
                                    navController.navigate(Screen.Cart.route)
                                } else {
                                    Toast
                                        .makeText(
                                            context,
                                            "Cantidad minima es 1",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                }
                            }
                    ){
                        Text(modifier = Modifier.align(Alignment.Center), text = "-")
                    }
                    Box(
                        modifier = Modifier.width(38.dp).height(30.dp)
                    ){
                        Text(modifier = Modifier.align(Alignment.Center), text = cantidad.value.toString())
                    }
                    Box(
                        modifier = Modifier.width(30.dp).height(30.dp).background(Color(0xFFD9D9D9))
                            .clickable {
                                cantidad.value++
                                item.cantidad = cantidad.value
                                viewModel.updateCartItem(item)
                                navController.popBackStack()
                                navController.navigate(Screen.Cart.route)
                            }
                    ) {
                        Text(modifier = Modifier.align(Alignment.Center), text = "+")
                    }
                }
            }
            Button(
                onClick = {
                    viewModel.deleteCartItem(item)
                },
                modifier = Modifier.width(90.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFE5383B),
                    contentColor = Color(0xFFFFFFFF)
                ),
            ) {
                Text(text = "Eliminar", fontSize = 12.sp)
            }
        }
    }
}