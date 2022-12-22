package com.example.akinms.ui.bodega.cart.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.akinms.components.CartProductItem
import com.example.akinms.domain.model.CartItem
import com.example.akinms.domain.repositories.CartList

@Composable
fun CartList(
    cartList: List<CartItem>
){
    Column(
        modifier = Modifier.padding(4.dp),
    ){
        cartList.forEach {
            //CartProductItem(item = it)
        }
    }
}