package com.hads.digicom.repository

import com.hads.digicom.data.model.address.UserAddress
import com.hads.digicom.data.remote.AddressApiInterface
import com.hads.digicom.data.remote.BaseApiResponse
import com.hads.digicom.data.remote.NetworkResult
import javax.inject.Inject

class AddressRepository @Inject constructor(private val api: AddressApiInterface) : BaseApiResponse() {

    suspend fun getUserAddressList(token: String): NetworkResult<List<UserAddress>> =
        safeApiCall {
            api.getUserAddressList(token)
        }


}