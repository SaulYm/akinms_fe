package com.example.akinms.ui.profile

import com.example.akinms.data.source.remote.dto.pedido.Pedido
import com.example.akinms.data.source.remote.dto.pedido.PedidoX

data class ProfileState(
    val pedidos: List<PedidoX> = emptyList(),
    val isLoading: Boolean = false
)
