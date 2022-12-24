package com.example.akinms.ui.bodega.pedido

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.akinms.data.source.remote.dto.pedido.*
import com.example.akinms.data.source.remote.dto.pedido.Producto
import com.example.akinms.domain.model.CartItem
import com.example.akinms.ui.bodega.cart.CartViewModel
import com.example.akinms.ui.theme.PrimaryColor
import com.example.akinms.util.githubCreditCardMasker.CardNumberMask
import com.example.akinms.util.githubCreditCardMasker.ExpirationDateMask
import com.example.akinms.util.navigationGraph.Graph
import okhttp3.internal.notify
import java.time.LocalDate
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CheckOutScreen(
    navController: NavHostController,
    viewModel: CartViewModel = hiltViewModel(),
    idBodega: Int,
    pedidoViewModel: PedidoViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel()
    //carrito: List<CartItem>
){
    var fechaActual = LocalDateTime.now().toString().substring(0,11)
    val cartItems by viewModel.items.collectAsState(initial = emptyList())
    var cartBodega = mutableListOf<CartItem>()
    for(item in cartItems){
        if(item.idBodega == idBodega)
            cartBodega.add(item)
    }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        val openStatePago: MutableState<Boolean> = remember { mutableStateOf(false) }
        val openStateDetallesPedido: MutableState<Boolean> = remember { mutableStateOf(false) }
        val openStateCliente: MutableState<Boolean> = remember { mutableStateOf(false) }
        val openStateEnrega: MutableState<Boolean> = remember { mutableStateOf(false) }
        var tarjeta by remember { mutableStateOf("") }
        var codigo by remember { mutableStateOf("") }
        var date by remember { mutableStateOf("") }
        var formaPago by remember { mutableStateOf("efectivo") }
        var formaEntrega by remember { mutableStateOf("Recojo en Tienda") }
        var costoEntrega by remember { mutableStateOf("0.0") }
        var monto: Double = 0.0
        val igv: Double = 0.0
        val delivery: Double = 3.0
        val recojo: Double = 0.0
        for(ite in cartBodega){
            monto = monto + (ite.precio * ite.cantidad)
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            text = "Confirmación de Pedido",
            color = Color.Green,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item{
                Column(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth(.95f)
                        .border(
                            width = .5.dp,
                            color = PrimaryColor,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth(1f)
                            .height(30.dp)
                            .padding(start = 10.dp, end = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Detalles del pedido",
                            color = PrimaryColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        IconButton(onClick = { openStateDetallesPedido.value = !openStateDetallesPedido.value }) {
                            Icon(
                                Icons.Rounded.ArrowDropDown,
                                contentDescription = "arrow down",
                                tint = PrimaryColor
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .fillMaxWidth(1f)
                            .height(45.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(.9f)
                                .padding(bottom = 1.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(text = "Cantidad de productos:", fontWeight = FontWeight.Bold)
                            Text(text = cartBodega.size.toString())
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(.9f)
                                .padding(bottom = 1.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Monto Total:", fontWeight = FontWeight.Bold)
                            Text(text = "S/. "+monto.toString())
                        }
                    }
                    if(openStateDetallesPedido.value){
                        Divider(
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                                .fillMaxWidth(.9f),
                            thickness = 1.dp, color = PrimaryColor
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(.9f),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Cant", fontWeight = FontWeight.Bold, color = PrimaryColor)
                            Text(text = "Producto", fontWeight = FontWeight.Bold, color = PrimaryColor)
                            Text(text = "Total", fontWeight = FontWeight.Bold, color = PrimaryColor)
                        }
                        for(pedido in cartBodega){
                            Row(
                                modifier = Modifier.fillMaxWidth(.9f),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = pedido.cantidad.toString())
                                Text(text = pedido.nombre)
                                Text(text = (pedido.cantidad*pedido.precio).toString())
                            }
                        }
                        Divider(
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                                .fillMaxWidth(.9f),
                            thickness = 1.dp, color = PrimaryColor
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(.9f),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "SubTotal:")
                            Text(text = "S/. "+monto.toString())
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(.9f),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "IGV:")
                            Text(text = "S/. "+igv.toString())
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(.9f),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Total:", fontWeight = FontWeight.Bold)
                            Text(text = "S/."+(monto+igv).toString(),fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
            item{
                Column(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth(.95f)
                        .border(
                            width = .5.dp,
                            color = PrimaryColor,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(start = 10.dp, end = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Detalles de la cuenta",
                            color = PrimaryColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        IconButton(onClick = { openStateCliente.value = !openStateCliente.value }) {
                            Icon(
                                Icons.Rounded.ArrowDropDown,
                                contentDescription = "arrow down",
                                tint = PrimaryColor
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .height(35.dp)
                            .padding(bottom = 10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(.9f)
                                .padding(bottom = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "NOMBRE DEL CLIENTE", fontWeight = FontWeight.Bold)
                            Text("")
                        }
                    }
                    if(openStateCliente.value){
                        Divider(
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                                .fillMaxWidth(.9f),
                            thickness = 1.dp, color = PrimaryColor
                        )
                        Text(text = "NOMBRE DEL CLIENTE")
                        Text(text = "NOMBRE DEL CLIENTE")
                        Text(text = "NOMBRE DEL CLIENTE")
                        Text(text = "NOMBRE DEL CLIENTE")
                    }
                }
            }
            item{
                Column(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth(.95f)
                        .border(
                            width = .5.dp,
                            color = PrimaryColor,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth(1f)
                            .height(30.dp)
                            .padding(start = 10.dp, end = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Modo de Entrega",
                            color = PrimaryColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        IconButton(onClick = { openStateEnrega.value = !openStateEnrega.value }) {
                            Icon(
                                Icons.Rounded.ArrowDropDown,
                                contentDescription = "arrow down",
                                tint = PrimaryColor
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .fillMaxWidth(1f)
                            .height(45.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(.9f)
                                .padding(bottom = 1.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = formaEntrega, fontWeight = FontWeight.Bold)
                            Text("")
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(.9f)
                                .padding(bottom = 1.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Costo:", fontWeight = FontWeight.Bold)
                            Text(text = costoEntrega)
                        }
                    }
                    if(openStateEnrega.value){
                        Divider(
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                                .fillMaxWidth(.9f),
                            thickness = 1.dp, color = PrimaryColor
                        )
                        Column(
                        ) {
                            Text(text = "Seleccione un modo de entrega",color = PrimaryColor, fontSize = 15.sp)
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(.9f)
                                    .padding(bottom = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    modifier = Modifier.padding(top = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(modifier = Modifier.height(20.dp),selected = formaEntrega == "Recojo en Tienda",
                                        onClick = {
                                            formaEntrega = "Recojo en Tienda"; costoEntrega = recojo.toString()
                                                  },
                                        colors = RadioButtonDefaults.colors(selectedColor = PrimaryColor))
                                    Text(text = "Recojo en Tienda")
                                }
                                Text(text = "S/. "+recojo.toString())
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(.9f)
                                    .padding(bottom = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    modifier = Modifier.padding(vertical = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(modifier = Modifier.height(20.dp),selected = formaEntrega == "Entrega a Domicilio",
                                        onClick = {
                                            formaEntrega = "Entrega a Domicilio"; costoEntrega = delivery.toString()
                                                  },
                                        colors = RadioButtonDefaults.colors(selectedColor = PrimaryColor))
                                    Text(text = "Entrega a Domicilio")
                                }
                                Text(text = "S/. "+delivery.toString())
                            }
                        }
                    }
                }
            }
            item {
                Column( modifier = Modifier
                    .fillMaxWidth(.95f)
                    .border(width = .5.dp, color = PrimaryColor, shape = RoundedCornerShape(5.dp)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(1f)
                        .height(30.dp)
                        .padding(start = 10.dp, end = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Metodo de Pago",
                            color = PrimaryColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        IconButton(onClick = { openStatePago.value = !openStatePago.value }) {
                            Icon(
                                Icons.Rounded.ArrowDropDown,
                                contentDescription = "arrow down",
                                tint = PrimaryColor
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .fillMaxWidth(1f)
                            .height(35.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(.9f),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            if (formaPago.equals("efectivo")) {
                                Text("Pago contraentrega", fontWeight = FontWeight.Bold)
                                Text("")
                            } else {
                                Text("Pago con tarjeta", fontWeight = FontWeight.Bold)
                                Text(tarjeta, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if(openStatePago.value){
                            Divider(
                                modifier = Modifier
                                    .padding(vertical = 5.dp)
                                    .fillMaxWidth(.9f),
                                thickness = 1.dp, color = PrimaryColor
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp),
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Column(
                                ) {
                                    Text(text = "Seleccione un metodo de pago",color = PrimaryColor, fontSize = 15.sp)
                                    Row(
                                        modifier = Modifier.padding(top = 8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        RadioButton(modifier = Modifier.height(20.dp),selected = formaPago == "efectivo", onClick = { formaPago = "efectivo" },colors = RadioButtonDefaults.colors(selectedColor = PrimaryColor))
                                        Text(text = "Pago contraentrega")
                                    }
                                    Row(
                                        modifier = Modifier.padding(vertical = 8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        RadioButton(modifier = Modifier.height(20.dp),selected = formaPago == "tarjeta", onClick = { formaPago = "tarjeta" },colors = RadioButtonDefaults.colors(selectedColor = PrimaryColor))
                                        Text(text = "Pago con tarjeta")
                                    }
                                }
                            }
                            if(formaPago.equals("tarjeta")){
                                Divider(
                                    modifier = Modifier
                                        .padding(vertical = 5.dp)
                                        .fillMaxWidth(.9f),
                                    thickness = 1.dp, color = PrimaryColor
                                )
                                OutlinedTextField(
                                    value = tarjeta,
                                    onValueChange = {
                                        if(it.length<=16) tarjeta = it
                                    },
                                    label = {Text("Número Tarjeta",fontSize = 15.sp)},
                                    visualTransformation = CardNumberMask("-"),
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        focusedBorderColor = PrimaryColor,
                                        unfocusedBorderColor = PrimaryColor,
                                        disabledBorderColor = Color.Gray,
                                        disabledTextColor = Color.Black,
                                        focusedLabelColor = Color.Gray
                                    ),
                                )
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 15.dp),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    OutlinedTextField(
                                        modifier = Modifier
                                            .width(140.dp)
                                            .padding(end = 10.dp),
                                        value = codigo,
                                        onValueChange = {
                                            if(it.length<=3) codigo = it
                                        },
                                        label = {Text("CVV", fontSize = 15.sp)},
                                        visualTransformation = PasswordVisualTransformation('*'),
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            focusedBorderColor = PrimaryColor,
                                            unfocusedBorderColor = PrimaryColor,
                                            disabledBorderColor = Color.Gray,
                                            disabledTextColor = Color.Black,
                                            focusedLabelColor = Color.Gray
                                        ),
                                    )
                                    OutlinedTextField(
                                        modifier = Modifier.width(140.dp),
                                        value = date,
                                        onValueChange = {
                                            if(it.length<=4) date = it
                                        },
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                        label = {Text("Expiracion",fontSize = 15.sp)},
                                        visualTransformation = ExpirationDateMask(),
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            focusedBorderColor = PrimaryColor,
                                            unfocusedBorderColor = PrimaryColor,
                                            disabledBorderColor = Color.Gray,
                                            disabledTextColor = Color.Black,
                                            focusedLabelColor = Color.Gray
                                        ),
                                    )

                                }
                            }
                        }
                    }
                }
            }
            item{
                Column(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp)
                        .height(50.dp)
                        .fillMaxWidth(.95f)
                        .border(
                            width = .5.dp,
                            color = PrimaryColor,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(start = 10.dp, end = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Total a pagar",
                            color = PrimaryColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(text = "s/. "+(monto+costoEntrega.toDouble()).toString())
                    }
                }
            }
            item {
                Row(modifier = Modifier.fillMaxWidth(1f), horizontalArrangement = Arrangement.Center) {
                    Button(
                        modifier = Modifier.width(250.dp),
                        onClick = {
                            var detalles: MutableList<DetallesPedido> = mutableListOf()
                            for(detalle in cartBodega){
                                println("PRODUCTO ELEGIDO:        "+detalle.nombre+" con ID: "+detalle.idproducto)
                                detalles.add(DetallesPedido(detalle.cantidad,Producto(detalle.idproducto)))
                            }
                            var pedido: Pedido = Pedido(
                                Bodega2(idBodega),
                                Cliente(1),
                                detalles,
                                "enviado",
                                fechaActual,
                                //0,
                                formaPago,
                                (monto+costoEntrega.toDouble()),
                                formaEntrega
                            )
                            pedidoViewModel.setPedido(pedido)
                            if(pedidoViewModel.state.pedidos?.idpedido!=0){
                                println("SE HA REGISTRADO EL PEDIGO GAAAAAAAAAAAAAAAAAAAAAA")
                                cartViewModel.deleteCartList(idBodega)
                                navController.navigate(Graph.BODEGA+"/"+idBodega)

                            }else{
                                println("TODO SE FUE AL DEMONIO GAAAAAAAAAAAAAAAAAAAAAAAAAAA")
                            }
                            /*(
                                Bodega2(idBodega);
                                Cliente(1);
                                detalles;
                                "enviado";
                                fechaActual;
                                formaPago;
                                (monto+costoEntrega.toDouble()).toString()
                                formaEntrega
                            )*/

                        }) {
                        Text(text = "Confirmar Pedido")
                    }
                }
            }
        }
    }
}
@Composable
fun formMetodoPago(){

}
/*
@Composable
fun Select(){
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("01","02","03","04","05","06","07","08","09","10","11","12")
    var selectedText by remember { mutableStateOf("") }

    //var textfieldSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp //it requires androidx.compose.material:material-icons-extended
    else
        Icons.Filled.ArrowDropDown


    Column() {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            readOnly = true,
            modifier = Modifier
                .width(84.dp),
                /*.onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },*/
            label = {Text("Mes")},
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(
                    84.dp
                )
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText = label
                    expanded = !expanded
                }) {
                    Text(
                        text = label
                    )
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyUI() {

    val contextForToast = LocalContext.current.applicationContext

    val listItems = arrayOf("Favorites", "Options", "Settings", "Share")

    var selectedItem by remember {
        mutableStateOf(listItems[0])
    }

    var expanded by remember {
        mutableStateOf(false)
    }
    println("OPCIONE SELECCIONADA: "+selectedItem)
    // the box
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {

        // text field
        TextField(
            value = selectedItem,
            onValueChange = {},
            readOnly = true,
            //label = { Text(text = "Label") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )

        // menu
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listItems.forEach { selectedOption ->
                // menu item
                DropdownMenuItem(onClick = {
                    selectedItem = selectedOption
                    //Toast.makeText(contextForToast, selectedOption, Toast.LENGTH_SHORT).show()
                    expanded = false
                }) {
                    Text(text = selectedOption)
                }
            }
        }
    }
}
/*@Composable
fun Test() {

    var mobileNumber by rememberSaveable { mutableStateOf("") }
    Column {
        Row(modifier = Modifier.padding(all = 10.dp)) {
            Text(
                text = "Cart number",
                fontSize = 14.sp,
                modifier = Modifier.weight(1f)
            )
            if(mobileNumber.length>16){
                mobileNumber = mobileNumber.substring(0,16)
                Toast
                    .makeText(
                        LocalContext.current,
                        "Solo se permiten 16 números",
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }
            BasicTextField(
                value = mobileNumber,
                onValueChange = { mobileNumber = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = { cardNumberFilter(it) }
            )
        }
        Box(
            modifier = Modifier
                .height(1.dp)
                .padding(start = 10.dp)
                .fillMaxWidth()
                .background(Color.Gray)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Actual value:\n$mobileNumber")
    }

}*/

const val maskcard = "xxxx xxxx xxxx xxxx"

fun cardNumberFilter(text: AnnotatedString): TransformedText {
    // change the length
    val trimmed =
        if (text.text.length >= 16) text.text.substring(0..15) else text.text

    val annotatedString = AnnotatedString.Builder().run {
        for (i in trimmed.indices) {
            append(trimmed[i])
            if (i == 3 || i == 7 || i == 11) {
                append(" ")
            }
        }
        pushStyle(SpanStyle(color = Color.LightGray))
        append(maskcard.takeLast(maskcard.length - length))
        toAnnotatedString()
    }

    val cardNumberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 7) return offset + 1
            if (offset <= 11) return offset + 2
            if (offset <= 16) return offset + 3
            return 12
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 7) return offset - 1
            if (offset <= 11) return offset - 2
            if (offset <= 16) return offset - 3
            return 9
        }
    }

    return TransformedText(annotatedString, cardNumberOffsetTranslator)
}
@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    text: String,
    topText: String = "",
    placeholder: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    onChange: (String) -> Unit,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType,
    keyBoardActions: KeyboardActions = KeyboardActions(),
    isEnabled: Boolean = true,
    readOnly: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    BasicTextField(
        value = text,
        onValueChange = onChange,
        textStyle = TextStyle(fontSize = 16.sp),
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
        keyboardActions = keyBoardActions,
        enabled = isEnabled,
        readOnly = readOnly,
        visualTransformation = visualTransformation,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier.height(65.dp)
            ){
                if(topText.isNotEmpty()){
                    Text(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(start = 10.dp, top = 1.dp)
                            .background(Color.White)
                            .padding(2.dp)
                            .zIndex(10f),
                        text = topText,
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .border(
                            width = .45.dp,
                            color = Color(0xFFFFC532),
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                ) {

                    if (text.isEmpty() && placeholder.isNotEmpty()) {
                        Text(
                            text = placeholder,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.LightGray
                        )
                    }
                    innerTextField()
                }
            }
        },
        modifier = modifier,
    )
}*/