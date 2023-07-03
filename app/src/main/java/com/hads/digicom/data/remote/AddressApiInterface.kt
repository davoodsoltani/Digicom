package com.hads.digicom.data.remote

import com.hads.digicom.data.model.ResponseResult
import com.hads.digicom.data.model.address.UserAddress
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AddressApiInterface {

    @GET("v1/getUserAddress")
    suspend fun getUserAddressList(
        @Query("token") token: String
    ) : Response<ResponseResult<List<UserAddress>>>


}