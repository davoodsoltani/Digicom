package com.hads.digikala.data.remote

import com.hads.digikala.data.model.ResponseResult
import com.hads.digikala.data.model.home.AmazingItem
import retrofit2.Response
import retrofit2.http.GET


interface BasketApiInterface {

    @GET("v1/getAllProducts")
    suspend fun getSuggestedItems(): Response<ResponseResult<List<AmazingItem>>>
}
