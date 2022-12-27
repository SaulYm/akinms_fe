package com.example.akinms.ui.home

import com.example.akinms.domain.model.Bodega
import com.example.akinms.domain.model.Product
import com.example.akinms.domain.model.Products

data class HomeState(
    val bodegas: List<Bodega> = emptyList(),
    val isLoading: Boolean = false
)
