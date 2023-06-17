package com.hads.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hads.digikala.data.model.basket.CartItem
import com.hads.digikala.data.model.basket.CartStatus
import com.hads.digikala.data.model.home.AmazingItem
import com.hads.digikala.data.remote.NetworkResult
import com.hads.digikala.repository.BasketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(private val repository: BasketRepository) : ViewModel() {

    val suggestedList = MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
    val currentCartItem: Flow<List<CartItem>> = repository.currentCartItem

    suspend fun getAllProducts(){
        viewModelScope.launch {
            launch {
                suggestedList.emit(repository.getAllProducts())
            }
        }
    }

    fun insertCartItem(item: CartItem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCartItem(item)
        }
    }

    fun removeFromCart(item: CartItem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeFromCart(item)
        }
    }

    fun changeCountCartItem(id: String, newCount: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.changeCountCartItem(id,newCount)
        }
    }

    fun changeStatusCartItem(id: String, newStatus: CartStatus){
        viewModelScope.launch(Dispatchers.IO) {
            repository.changeStatusCartItem(id,newStatus)
        }
    }
}