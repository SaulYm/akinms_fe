package com.example.akinms.ui.profile.historial

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.akinms.R
import com.example.akinms.components.CartTotalResume
import com.example.akinms.data.source.remote.dto.pedido.PedidoX
import com.example.akinms.data.source.remote.dto.pedido.Producto
import com.example.akinms.ui.profile.DetalleState
import com.example.akinms.ui.profile.DetalleViewModel
import com.example.akinms.ui.profile.ProfileState
import com.example.akinms.util.navigationGraph.BodegaScreen

@Composable
fun DetailsScreen(
    navController: NavHostController,
    detalleViewModel: DetalleViewModel = hiltViewModel()
){
    var state: DetalleState = detalleViewModel.state
    var pedido = state.pedidos
    var tamanio = state.pedidos?.detallesPedido?.size
    var detalles = state.pedidos?.detallesPedido
    /*Column(
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
            Text(text = "DETALLES DEL PEDIDO")
        }
        Text(text = "AQUI IRÍAN LOS DETALLES DEL PEDIDO SI MIS COMPAS LO HUBIERAN HECHO")
        println("RESULTADO DEL PEDIO:        " + state.pedidos?.detallesPedido?.get(0)?.producto?.nombre)
        Text(text = tamanio.toString())
        if(tamanio!=null){
            for(a in 0..tamanio-1){
                Text(text = detalles?.get(a)?.producto?.nombre!!)
            }
        }

    }*/
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(bottom = 20.dp),
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
            println("SAUL SE LA COMEEEEEEEEEEEEEE: "+state.pedidos?.fecha)
            //var pedido: PedidoX = state.pedidos!!

            // sdsdsdsdsds
            if(tamanio!=null){
                if(tamanio > 0){
                    var total: Double = 0.0
                    var precio: Double?
                    var cantidad: Int
                    if(detalles!=null){
                        for(detalle in detalles) {
                            precio = detalle.producto.precio
                            cantidad = detalle.cantidad
                            total += precio!! * cantidad

                            item {
                                //CartProductItem(cantidad)
                                println(pedido!!.detallesPedido.size)
                                //CartList(cartList = cartItems)
                                productoDetalle(detalle.producto, cantidad)
                            }

                        }


                        item{
                            CartTotalResume(total = total)
                        }
                        item{
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                                Button(
                                    modifier = Modifier.width(200.dp),
                                    onClick = { navController.navigate(BodegaScreen.CheckOut.route) }
                                ) {
                                    Text(text = "Continuar CheckOut")
                                }
                            }

                        }
                    }

                }
                else{
                    item {
                        Text(
                            text = "Carrito Vacio"
                        )
                    }
                }
            }


        }
    )
    /*var state = detalleViewModel.state
    println("AFLKSADJFLKSADJFLKSDJLFKJSDA: "+state.pedidos?.fecha)
    Scaffold(
        topBar = {
            //NavBarTop(minimarket,navController)
        },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(bottom = 20.dp),
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
                println("SAUL SE LA COMEEEEEEEEEEEEEE: "+state.pedidos?.fecha)
                var pedido: PedidoX = state.pedidos!!

                // sdsdsdsdsds
                if(pedido?.detallesPedido?.size!! > 0){
                    var total: Double = 0.0
                    var precio: Double?
                    var cantidad: Int

                    for(detalle in pedido!!.detallesPedido) {
                        precio = detalle.producto.precio
                        cantidad = detalle.cantidad
                        total += precio!! * cantidad

                        item {
                            //CartProductItem(cantidad)
                            println(pedido!!.detallesPedido.size)
                            //CartList(cartList = cartItems)
                            productoDetalle(detalle.producto, cantidad)
                        }

                    }


                    item{
                        CartTotalResume(total = total)
                    }
                    item{
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            Button(
                                modifier = Modifier.width(200.dp),
                                onClick = { navController.navigate(BodegaScreen.CheckOut.route) }
                            ) {
                                Text(text = "Continuar CheckOut")
                            }
                        }

                    }
                }
                else{
                    item {
                        Text(
                            text = "Carrito Vacio"
                        )
                    }
                }

            }
        )
    }*/
}


@Composable
fun productoDetalle(
    //cantidad: MutableState<Int>
    item: Producto,
    cantidad: Int
    //viewModel: CartViewModel,
    //navController: NavHostController,
    //idBodega: Int,
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
                    modifier = Modifier.width(100.dp),
                    text = item.nombre!!,
                    fontSize = 16.sp
                )
                Text(
                    text = item.descripcion!!,
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
            //var cantidad = remember { mutableStateOf(cantidad) }
            //IncDecBtn(item = item, context = context, viewModel = viewModel)
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Cantidad: "+cantidad)
                /*Row(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFFD9D9D9),
                            shape = RoundedCornerShape(10.dp)
                        ),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        cantidad
                    )
                    /*Box(
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .background(Color(0xFFD9D9D9))
                            .clickable {
                                if (cantidad.value > 1) {
                                    cantidad.value--
                                    //cantidad = cantidad.value
                                    /*viewModel.updateCartItem(item)
                                    navController.popBackStack()
                                    navController.navigate(BodegaScreen.Cart.route+"/"+idBodega)*/
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
                        modifier = Modifier
                            .width(38.dp)
                            .height(30.dp)
                    ){
                        Text(modifier = Modifier.align(Alignment.Center), text = cantidad.value.toString())
                    }
                    Box(
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .background(Color(0xFFD9D9D9))
                            .clickable {
                                cantidad.value++
                                //cantidad = cantidad.value
                                /*viewModel.updateCartItem(item)
                                navController.popBackStack()
                                navController.navigate(BodegaScreen.Cart.route+"/"+idBodega)*/
                            }
                    ) {
                        Text(modifier = Modifier.align(Alignment.Center), text = "+")
                    }*/
                }*/
            }
            IconButton(
                modifier = Modifier
                    .background(Color(0xFFE5383B))
                    .border(
                        width = 1.dp,
                        color = Color(0xFFE5383B),
                        shape = RoundedCornerShape(5.dp)
                    )
                    .padding(0.dp),
                onClick = { /*viewModel.deleteCartItem(item)*/ }
            ) {
                Icon(Icons.Rounded.Delete, contentDescription = "", tint = Color.White)
            }
            /*Button(
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
            }*/
        }
    }
}
