package com.example.akinms.components


import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.akinms.domain.model.Products


@Composable
fun ProductList(
    navController: NavHostController,
    list: List<Products>,
    idBodega: Int,
) {
    /*LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(20) {
            ProductItem()
        }
    }
    */
    /*var lista: List<Producto> = listOf(
        Producto("Cerveza pilsen","1 unid | 355ml",6.50,"pilsen355"),
        Producto("Cerveza pilsen2","1 unid | 355ml",6.50,"pilsen355"),
        Producto("Cerveza pilsen3","1 unid | 355ml",6.50,"pilsen355"),
        Producto("Cerveza pilsen4","1 unid | 355ml",6.50,"pilsen355"),
        Producto("Cerveza pilsen5","1 unid | 355ml",6.50,"pilsen355"),
        Producto("Cerveza pilsen6","1 unid | 355ml",6.50,"pilsen355"),
        Producto("Cerveza pilsen7","1 unid | 355ml",6.50,"pilsen355"),
        Producto("Cerveza pilsen8","1 unid | 355ml",6.50,"pilsen355"),
        Producto("Cerveza pilsen9","1 unid | 355ml",6.50,"pilsen355"),
        Producto("Cerveza pilsen10","1 unid | 355ml",6.50,"pilsen355"),
        Producto("Cerveza pilsen11","1 unid | 355ml",6.50,"pilsen355"),
    )*/

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(list.size>0){
            for(prod in list.chunked(2)){
                Row(
                    modifier = Modifier.height(245.dp)
                ) {
                    prod.forEach{
                        ProductItem(
                            producto = it,
                            navController = navController,
                            idBodega = idBodega
                        )
                    }
                }
            }
        }
        else{
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = "No se encontraron resultados"
            )
        }

    }
}
