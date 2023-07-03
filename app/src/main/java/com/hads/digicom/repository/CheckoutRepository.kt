package com.hads.digicom.repository

import com.hads.digicom.data.model.checkout.ConfirmPurchase
import com.hads.digicom.data.model.checkout.OrderDetail
import com.hads.digicom.data.remote.BaseApiResponse
import com.hads.digicom.data.remote.CheckoutApiInterface
import com.hads.digicom.data.remote.NetworkResult
import javax.inject.Inject

class CheckoutRepository @Inject constructor(private val api: CheckoutApiInterface) : BaseApiResponse() {

    suspend fun getShippingCost(address: String): NetworkResult<Int> =
        safeApiCall {
            api.getShippingCost(address)
        }


    suspend fun setNewOrder(cartOrderDetail: OrderDetail): NetworkResult<String> =
        safeApiCall {
            api.setNewOrder(cartOrderDetail)
        }

    suspend fun confirmPurchase(confirmPurchase: ConfirmPurchase): NetworkResult<String> =
        safeApiCall {
            api.confirmPurchase(confirmPurchase)
        }

}
