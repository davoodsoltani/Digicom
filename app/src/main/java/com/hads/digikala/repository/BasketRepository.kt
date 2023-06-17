package com.hads.digikala.repository

import com.hads.digikala.data.db.CartDao
import com.hads.digikala.data.model.basket.CartItem
import com.hads.digikala.data.model.basket.CartStatus
import com.hads.digikala.data.model.home.AmazingItem
import com.hads.digikala.data.remote.BaseApiResponse
import com.hads.digikala.data.remote.BasketApiInterface
import com.hads.digikala.data.remote.NetworkResult
import javax.inject.Inject

class BasketRepository @Inject constructor(
    private val api: BasketApiInterface,
    private val dao : CartDao
) :
    BaseApiResponse() {

    suspend fun getAllProducts(): NetworkResult<List<AmazingItem>> =
        safeApiCall {
            api.getAllProducts()
        }

    suspend fun insertCartItem(item: CartItem){
        dao.insertCartItem(item)
    }

    val currentCartItem = dao.getAllItems(CartStatus.CURRENT_CART)

    suspend fun removeFromCart(item: CartItem){
        dao.removeFromCart(item)
    }

    suspend fun changeCountCartItem(id: String, newCount: Int){
        dao.changeCountCartItem(id, newCount)
    }

    suspend fun changeStatusCartItem(id: String, newStatus: CartStatus){
        dao.changeStatusCartItem(id, newStatus)
    }
}
