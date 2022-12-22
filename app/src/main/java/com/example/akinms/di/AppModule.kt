package com.example.akinms.di

import android.content.Context
import androidx.room.Room
import com.example.akinms.data.repositories.CartRepositoryImpl
import com.example.akinms.data.source.local.CartDao
import com.example.akinms.data.source.local.CartDb
import com.example.akinms.domain.repositories.CartItemRepository
import com.example.akinms.util.AppNavigator
import com.example.akinms.borrar.AppNavigatorImpl
import com.example.akinms.util.CART_TABLE
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideCartDb(
        @ApplicationContext
        context: Context
    ) = Room.databaseBuilder(
        context,
        CartDb::class.java,
        CART_TABLE
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideCartDao(
        cartDb: CartDb
    ) = cartDb.cartDao()

    @Provides
    fun provideCartItemRepository(
        cartDao: CartDao
    ) : CartItemRepository = CartRepositoryImpl(
        cartDao = cartDao
    )
}

@Module
@InstallIn(SingletonComponent::class)
interface AppModule2 {

    @Singleton
    @Binds
    fun bindAppNavigator(appNavigatorImpl: AppNavigatorImpl): AppNavigator
}