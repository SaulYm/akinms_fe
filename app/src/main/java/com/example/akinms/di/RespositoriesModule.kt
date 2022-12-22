package com.example.akinms.di

import com.example.akinms.data.repositories.BodegaRepositoryImpl
import com.example.akinms.data.repositories.CategoriaRepositoryImpl
import com.example.akinms.data.repositories.PedidoRepositoryImpl
import com.example.akinms.data.repositories.ProductRepositoryImpl
import com.example.akinms.domain.repositories.BodegaRepository
import com.example.akinms.domain.repositories.CategoriaRepository
import com.example.akinms.domain.repositories.PedidoRepository
import com.example.akinms.domain.repositories.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RespositoriesModule {

    @Binds
    abstract fun bindProductsRepository(impl: ProductRepositoryImpl) : ProductRepository
    @Binds
    abstract fun bindBodegaRepository(impl: BodegaRepositoryImpl) : BodegaRepository
    @Binds
    abstract fun bindCategoriaRepository(impl: CategoriaRepositoryImpl) : CategoriaRepository
    @Binds
    abstract fun bindPedidoRepository(impl: PedidoRepositoryImpl) : PedidoRepository
}