package com.example.akinms.ui.bodega.cart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.akinms.domain.model.CartItem
import com.example.akinms.domain.repositories.CartItemRepository
import com.example.akinms.domain.repositories.CartList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repo: CartItemRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var cart by mutableStateOf(CartItem(0,"","",0.0,0, "",0))
        private set

    val items = repo.getCartListFromRoom()
    //var idBodega: Int = savedStateHandle.get<Long>("id")?.toInt()
    //val items = repo.getCartListFromRoom(idBodega)

    /*fun getList(id:Int): Flow<CartList> {
        return repo.getCartListFromRoom()
    }*/


    init {
        /*savedStateHandle.get<Long>("id").let {
            if (it != null) {
                idBodega = it.toInt()
            }
        }*/

    }
    fun addCartItem(cartItem: CartItem) = viewModelScope.launch(Dispatchers.IO) {
        println("Producto recibido")
        println(cartItem.toString())
        repo.addItemToRoom(cartItem)
    }
    fun updateCartItem(cartItem: CartItem) = viewModelScope.launch(Dispatchers.IO) {
        repo.updateItemInRoom(cartItem)
    }
    fun deleteCartItem(cartItem: CartItem) = viewModelScope.launch(Dispatchers.IO) {
        repo.deleteItemFromRoom(cartItem)
    }
}