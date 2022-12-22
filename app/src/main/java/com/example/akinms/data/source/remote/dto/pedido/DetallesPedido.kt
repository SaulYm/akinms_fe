package com.example.akinms.data.source.remote.dto.pedido

import com.example.akinms.data.source.remote.dto.producto.Producto

data class DetallesPedido(
    val cantidad: Int,
    val producto: Producto
)