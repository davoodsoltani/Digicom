package com.hads.digicom.repository

import com.hads.digicom.data.db.CartDao
import com.hads.digicom.data.model.basket.CartItem
import com.hads.digicom.data.model.basket.CartStatus
import com.hads.digicom.data.model.home.AmazingItem
import com.hads.digicom.data.model.home.StoreProduct
import com.hads.digicom.data.remote.BaseApiResponse
import com.hads.digicom.data.remote.BasketApiInterface
import com.hads.digicom.data.remote.NetworkResult
import javax.inject.Inject

class BasketRepository @Inject constructor(
    private val api: BasketApiInterface,
    private val dao: CartDao
) : BaseApiResponse() {

    val currentCartItems = dao.getAllItems(CartStatus.CURRENT_CART)
    val nextCartItems = dao.getAllItems(CartStatus.NEXT_CART)

    val currentCartItemsCount = dao.getCartItemsCount(CartStatus.CURRENT_CART)
    val nextCartItemsCount = dao.getCartItemsCount(CartStatus.NEXT_CART)


    suspend fun getSuggestedItems(): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getSuggestedItems()
        }


    suspend fun insertCartItem(cart: CartItem) {
        dao.insertCartItem(cart)
    }

    suspend fun removeFromCart(cart: CartItem) {
        dao.removeFromCart(cart)
    }


    suspend fun deleteAllItems() {
        dao.deleteAllItems(CartStatus.CURRENT_CART)
    }

    suspend fun changeCartItemStatus(id: String, newStatus: CartStatus) {
        dao.changeStatusCart(id, newStatus)
    }

    suspend fun changeCartItemCount(id: String, newCount: Int) {
        dao.changeCountCartItem(id, newCount)
    }


}