package com.example.akinms.ui.home

import com.example.akinms.domain.model.Product
import com.example.akinms.domain.model.Products

data class HomeState(
    val productos: List<Products> = emptyList(),
    val isLoading: Boolean = false
)
