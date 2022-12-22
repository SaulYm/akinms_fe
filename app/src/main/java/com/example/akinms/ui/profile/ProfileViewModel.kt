package com.example.akinms.ui.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.akinms.domain.use_case.GetPedidosClienteUseCase
import com.example.akinms.data.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getPedidosClienteUseCase: GetPedidosClienteUseCase
) : ViewModel() {
    var state by mutableStateOf(ProfileState())
        private set
    val idcliente: Long = 1
    init{
        getPedidos()
    }
    fun getPedidos() {
        viewModelScope.launch {
            getPedidosClienteUseCase(idcliente).onEach { result ->
                when(result){
                    is Result.Success -> {
                        state = state.copy(
                            pedidos = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                    is Result.Error -> {
                        state = state.copy(
                            isLoading = false
                        )
                    }
                    is Result.Loading -> {
                        state = state.copy(
                            isLoading = true
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}