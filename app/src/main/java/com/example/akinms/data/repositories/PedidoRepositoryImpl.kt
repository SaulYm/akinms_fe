package com.example.akinms.data.repositories

import com.example.akinms.data.source.remote.ApiRest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.akinms.data.Result
import com.example.akinms.data.source.remote.dto.pedido.Pedido
import com.example.akinms.data.source.remote.dto.pedido.PedidoX
import com.example.akinms.data.source.remote.dto.pedido.toPedidos
import com.example.akinms.domain.repositories.PedidoRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class PedidoRepositoryImpl @Inject constructor(
    private val api: ApiRest
) : PedidoRepository {
    override fun getPedidosCliente(id: Long): Flow<Result<List<PedidoX>>>  = flow{
        emit(Result.Loading())
        try{
            val response = api.getPedidoByClient(id).toPedidos()
            emit(Result.Success(response))

        } catch (e: HttpException){
            emit(Result.Error(
                message = "Oops, something went wrong",
                data = null
            ))
        } catch (e: IOException){
            emit(Result.Error(
                message = "Couldn't reach server, check your internet connection",
                data = null
            ))
        }
    }

}