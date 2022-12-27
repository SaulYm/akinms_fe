package com.example.akinms.ui.login



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment

import com.example.akinms.ui.theme.AkinmsTheme

import androidx.compose.foundation.layout.*

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image

import androidx.compose.foundation.shape.RoundedCornerShape


import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.runtime.Composable

import androidx.compose.ui.layout.ContentScale

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.draw.paint

import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.sp


import com.example.akinms.ui.theme.Shapes
import com.example.akinms.R


@Preview
@Composable
fun LoginView() = AkinmsTheme {
    val passwordFocusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current

    val botonAzul = Color(0xff100DB1)


    val showDialog = remember{ mutableStateOf(false) }
    if(showDialog.value){
        Alert(msg="Continuar",
            showDialog = showDialog.value,
            onDismiss = {showDialog.value = false})
    }

    // ProvideWindowInsets{

    Box(
        Modifier
            .paint(painterResource(id = R.drawable.login_bg), contentScale = ContentScale.FillWidth)
            .fillMaxSize(),
    )
    Column(
        Modifier
            //.navigationBarsWithImePadding()
            //.paint(painterResource(id = R.drawable.login_bg), contentScale = ContentScale.FillWidth)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally

    ){

        //Logo Aki-nms
        Image(
            painter = painterResource(R.drawable.logo),
            null,
            Modifier
                .size(200.dp)
            //.weight(2f)
            // tint = Color.White
        )

        //TITULO
        Text("INICIO DE SESION", color=Color.Black, textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold, fontSize = 25.sp)

        //Input email
        TextInput(InputType.Name, keyboardActions = KeyboardActions(onNext = {
            passwordFocusRequester.requestFocus()
        }))

        //Input contraseña
        TextInput(InputType.Password, keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
        }), focusRequester = passwordFocusRequester)



        //Boton Ingresar
        Button(onClick = {showDialog.value = true}, modifier = Modifier
            .height(50.dp)
            .width(250.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = botonAzul,
                contentColor = Color.White)){
            Text("Ingresar", Modifier.padding(vertical = 8.dp))
        }

        //Recuperar contraseña
        TextButton(onClick = {}) {
            Text("¿No recuerda su contraseña?")
        }

        //Crear cuenta
        Row(verticalAlignment = Alignment.CenterVertically){
            Text("¿No tiene una cuenta?", color = Color.Black)
            TextButton(onClick = {}) {
                Text("Crear Cuenta")
            }
        }


        Divider(
            color = Color.White.copy(alpha = 0.3f),
            thickness = 1.dp,
            modifier = Modifier.padding(top = 45.dp)
        )

        Row(verticalAlignment = Alignment.CenterVertically, modifier =  Modifier.padding(bottom = 30.dp)) {
            Text("2022. Squad Armonia 10", color = Color.Black)
        }
    }
    //}
}


sealed class InputType(
    val label: String,
    val icon: ImageVector,
    val keyboardOptions: KeyboardOptions,
    val visualTransformation: VisualTransformation
){
    object Name : InputType(
        label = "Email",
        icon = Icons.Default.Person,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        visualTransformation = VisualTransformation.None
    )

    object Password : InputType(
        label = "Contraseña",
        icon = Icons.Default.Lock,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation()
    )
}



@Composable
fun TextInput(
    inputType: InputType,
    focusRequester: FocusRequester? = null,
    keyboardActions: KeyboardActions
){
    var value by remember { mutableStateOf("") }

    OutlinedTextField(
        value = value,
        onValueChange = { value = it },
        modifier = Modifier
            .height(65.dp)
            .width(300.dp)
            .focusOrder(focusRequester ?: FocusRequester()),
        leadingIcon = { Icon(imageVector = inputType.icon, null, tint = Color.Black)},
        label = { Text(text = inputType.label)},
        shape = Shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Black,
            disabledIndicatorColor = Color.Black,
            placeholderColor = Color.Black
        ),
        singleLine = true,
        keyboardOptions = inputType.keyboardOptions,
        visualTransformation = inputType.visualTransformation,
        keyboardActions = keyboardActions,

        )
}

@Composable
fun Alert(msg : String,
          showDialog: Boolean,
          onDismiss: () -> Unit) {

    if (showDialog) {
        val colorAlerta = Color(0xff52CA73)
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = onDismiss ) {
                    Text(msg, color = Color.Black, textAlign = TextAlign.Right)
                }
            },
            title={
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ){
                    //Icon(Modifier.size(26.dp),
                    //painterResource(R.drawable.check_icon))
                    Image(
                        painterResource(id = R.drawable.check_icon),
                        contentDescription = null,
                        Modifier
                            .align(Alignment.Center)
                            .size(100.dp,100.dp)
                    )
                }
            },
            text = {
                Text(text = "Inicio de Sesion Exitoso",
                    textAlign = TextAlign.Center,
                    color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 20.sp,
                    modifier = Modifier.fillMaxWidth())
            },
            backgroundColor = colorAlerta,
            shape = RoundedCornerShape(25.dp)


        )
    }



}