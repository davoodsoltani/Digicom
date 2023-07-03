package com.hads.digicom.data.remote

import com.hads.digicom.data.model.ResponseResult
import com.hads.digicom.data.model.home.AmazingItem
import com.hads.digicom.data.model.home.StoreProduct
import retrofit2.Response
import retrofit2.http.GET


interface BasketApiInterface {

    @GET("v1/getAllProducts")
    suspend fun getSuggestedItems(): Response<ResponseResult<List<StoreProduct>>>
}
