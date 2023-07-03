package com.hads.digicom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hads.digicom.data.model.basket.CartDetails
import com.hads.digicom.data.model.basket.CartItem
import com.hads.digicom.data.model.basket.CartStatus
import com.hads.digicom.data.model.home.AmazingItem
import com.hads.digicom.data.model.home.StoreProduct
import com.hads.digicom.data.remote.NetworkResult
import com.hads.digicom.repository.BasketRepository
import com.hads.digicom.ui.screens.basket.BasketScreenState
import com.hads.digicom.utils.DigitHelper.applyDiscount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(private val repository: BasketRepository) : ViewModel() {


    val suggestedList = MutableStateFlow<NetworkResult<List<StoreProduct>>>(NetworkResult.Loading())
    val cartDetail = MutableStateFlow(CartDetails(0, 0, 0, 0))


    private val _currentCartItems: MutableStateFlow<BasketScreenState<List<CartItem>>> =
        MutableStateFlow(BasketScreenState.Loading)
    val currentCartItems: StateFlow<BasketScreenState<List<CartItem>>> = _currentCartItems

    val ourCartItems: MutableStateFlow<List<CartItem>> =  MutableStateFlow(emptyList())

    private val _nextCartItems: MutableStateFlow<BasketScreenState<List<CartItem>>> =
        MutableStateFlow(BasketScreenState.Loading)
    val nextCartItems: StateFlow<BasketScreenState<List<CartItem>>> = _nextCartItems


    val currentCartItemsCount = repository.currentCartItemsCount
    val nextCartItemsCount = repository.nextCartItemsCount

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                repository.currentCartItems.collectLatest { cartItems ->
                    _currentCartItems.emit(BasketScreenState.Success(cartItems))
                    ourCartItems.emit(cartItems)
                }
            }
            launch {
                repository.currentCartItems.collectLatest { cartItems ->
                    calculateCartDetails(cartItems)

                }
            }
            launch {
                repository.nextCartItems.collectLatest { nextCartItems ->
                    _nextCartItems.emit(BasketScreenState.Success(nextCartItems))
                }
            }
        }
    }


    private fun calculateCartDetails(items: List<CartItem>) {
        var totalCount = 0
        var totalPrice = 0L
        var totalDiscount = 0L
        var payablePrice = 0L
        items.forEach { item ->
            totalPrice += item.price * item.count
            payablePrice += applyDiscount(item.price, item.discountPercent) * item.count
            totalCount += item.count
        }
        totalDiscount = totalPrice - payablePrice
        cartDetail.value = CartDetails(totalCount, totalPrice, totalDiscount, payablePrice)
    }


    fun getSuggestedItems() {
        viewModelScope.launch {
            suggestedList.emit(repository.getSuggestedItems())
        }
    }


    fun insertCartItem(item: CartItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCartItem(item)
        }
    }


    fun removeCartItem(item: CartItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeFromCart(item)
        }
    }

    fun deleteAllItems() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllItems()
        }
    }

    fun changeCartItemCount(id: String, newCount: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.changeCartItemCount(id, newCount)
        }
    }

    fun changeCartItemStatus(id: String, newStatus: CartStatus) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.changeCartItemStatus(id, newStatus)
        }
    }


}